#include <queue>
#include <memory>
#include <mutex>
#include <condition_variable>
#include <iostream>
#include <fstream>

template<typename T>
class threadsafe_queue
{
private:
    mutable std::mutex mut;
    std::queue<T> data_queue;
    std::condition_variable data_cond;
public:
    threadsafe_queue()= default;

    threadsafe_queue(threadsafe_queue const& other) {
        std::lock_guard<std::mutex> lk(other.mut);
        data_queue = other.data_queue;
    }

    void push(T new_value) {
        std::lock_guard<std::mutex> lk(mut);
        data_queue.push(new_value);
        data_cond.notify_one();
    }

    void wait_and_pop(T& value) {
        std::unique_lock<std::mutex> lk(mut);
        data_cond.wait(lk, [this]{return !data_queue.empty();});
        value = data_queue.pop();
    }
    std::shared_ptr<T> wait_and_pop(){
        std::unique_lock<std::mutex> lk(mut);
        data_cond.wait(lk,[this]{return !data_queue.empty();});
        std::shared_ptr<T> res(std::make_shared<T>(data_queue.front()));
        data_queue.pop();
        return res;
    }

    bool try_pop(T& value){
        std::lock_guard<std::mutex> lk(mut);
        if(data_queue.empty()){
            return false;
        }
        value=data_queue.front();
        data_queue.pop();
        return true;
    }

    std::shared_ptr<T> try_pop(){
        std::lock_guard<std::mutex> lk(mut);
        if(data_queue.empty()){
            return std::shared_ptr<T>();
        }
        std::shared_ptr<T> res(std::make_shared<T>(data_queue.front()));
        data_queue.pop();
        return res;
    }
    bool empty() const{
        std::lock_guard<std::mutex> lk(mut);
        return data_queue.empty();
    }
};


using namespace std::chrono_literals;

class Consumer {
private:
    static std::ofstream fout;
public:
    void operator()(threadsafe_queue<std::string>& request_queue, std::mutex& mutex, bool& finished) {
        while (!finished) {
            fout << request_queue.try_pop() << " ";
        }
    }
};

class Producer {
private:
    static std::ifstream fin;
    std::string buffer_;
public:
    void operator()(threadsafe_queue<std::string>& request_queue, std::mutex& mutex, bool& finished) {
        while(!fin.eof()){
            {
                fin >> buffer_;
                request_queue.push(buffer_);
            }
        }
        {
            finished = true;
        }
    }
};

std::ifstream Producer::fin = std::ifstream("input.txt");
std::ofstream Consumer::fout = std::ofstream("output.txt");

int main() {
    /*std::ofstream out_ = std::ofstream("input.txt");
    for (int i = 0; i < 1000000; ++i) {
        out_ << "sdfvdfvfdvdfvdfdv\n";
    }
    out_.close();*/
    auto start = std::chrono::high_resolution_clock::now();
    std::mutex mut;
    threadsafe_queue<std::string> request_queue;
    bool finished = false;
    std::thread t1(Consumer(), std::ref(request_queue), std::ref(mut), std::ref(finished));
    std::thread t2(Producer(), std::ref(request_queue), std::ref(mut), std::ref(finished));
    t1.join();
    t2.join();
    std::cout << "finished, time is " << std::chrono::duration_cast<std::chrono::microseconds>(std::chrono::high_resolution_clock::now()- start).count() << " mcs\n";
}
