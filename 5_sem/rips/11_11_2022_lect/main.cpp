#include <iostream>
#include <queue>
#include <tuple>
#include <condition_variable>
#include <thread>
#include <fstream>
#include <algorithm>

using namespace std::chrono_literals;

class Consumer {
private:
    static std::ofstream fout;
    std::string buffer_;
    bool fileNotEmpty = false;
public:
    void operator()(std::queue<std::string>& request_queue, std::mutex& mutex, std::condition_variable& cond, bool& finished) {
        while (!finished) {
            {
                std::unique_lock<std::mutex> l{mutex};
                cond.wait(l, [&finished, &request_queue] { return !request_queue.empty() || finished; });
                if (!request_queue.empty()){
                    buffer_ = request_queue.front();
                    request_queue.pop();
                    fileNotEmpty = true;
                }
            }
            if (fileNotEmpty){
                std::reverse(buffer_.begin(), buffer_.end());
                fout << buffer_ << " ";
            }
        }
    }
};

class Producer {
private:
    static std::ifstream fin;
    std::string buffer_;
public:
    void operator()(std::queue<std::string>& request_queue, std::mutex& mutex, std::condition_variable& cond, bool& finished) {
        while(!fin.eof()){
            {
                fin >> buffer_;
                std::lock_guard<std::mutex> lk{mutex};
                request_queue.push(buffer_);
            }
            cond.notify_all();
        }
        {
            std::lock_guard<std::mutex> lk{mutex};
            finished = true;
        }
        cond.notify_all();
    }
};

std::ifstream Producer::fin = std::ifstream("input.txt");
std::ofstream Consumer::fout = std::ofstream("output.txt");

int main() {
    auto start = std::chrono::high_resolution_clock::now();
    std::mutex mut;
    std::condition_variable cv;
    std::queue<std::string> request_queue;
    bool finished = false;
    std::thread t1(Consumer(), std::ref(request_queue), std::ref(mut), std::ref(cv), std::ref(finished));
    std::thread t2(Producer(), std::ref(request_queue), std::ref(mut), std::ref(cv), std::ref(finished));
    t1.join();
    t2.join();
    std::cout << "finished, time is " << std::chrono::duration_cast<std::chrono::microseconds>(std::chrono::high_resolution_clock::now()- start).count() << " mcs\n";
}
