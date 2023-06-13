/*#include <iostream>
#include <thread>
#include <mutex>
#include <sstream>
#include <vector>


static void to_cout(int id) {
    std::cout << "Finished thread: " << id << '\n';
}

struct pcout : public std::stringstream {
  static inline std::mutex cout_mutex;
  ~pcout() {
    std::lock_guard<std::mutex> l{cout_mutex};
    std::cout << rdbuf();
    std::cout.flush();
  }
};

static void to_pcout(int id) {
    pcout{} << "Finished thread: " << id << '\n';
}

void race(std::vector<std::thread>& vector) {
    std::cout << "______ WITH THREAD RACE STATE: _____\n";
    vector.clear();
    for (size_t i{0}; i < 10; ++i) {
        vector.emplace_back(to_cout, i);
    }
    for (auto &t: vector) { t.join(); }
}

void no_race(std::vector<std::thread>& vector) {
    std::cout << "______ WITHOUT THREAD RACE STATE: _____\n";
    vector.clear();
    for (size_t i{0}; i < 10; ++i) {
        vector.emplace_back(to_pcout, i);
    }
    for (auto &t: vector) { t.join(); }
}

int main() {
    std::vector<std::thread> vector;
    race(vector);
    no_race(vector);
}
*/


#include <iostream>
#include <thread>
#include <mutex>
#include <sstream>
#include <vector>
#include <fstream>

std::ofstream out_("test.txt");

void to_cout(int id) {
    out_ << "Thread: " << id << std::endl;
}

struct pcout : public std::stringstream {
    std::mutex mutex;
    ~pcout() {
        std::lock_guard<std::mutex> l{mutex};
        out_ << rdbuf();
        out_.flush();
    }
};

void to_pcout(int id) {
    pcout{} << "Thread: " << id << std::endl;
}

void race() {
    out_ << "______ WITH THREAD RACE STATE: _____\n";
    std::vector<std::thread> vector(0);
    for (size_t i{0}; i < 10; ++i) {
        vector.emplace_back(to_cout, i);
    }
    for (auto &t: vector) { t.join(); }
}

void no_race() {
    out_ << "______ WITHOUT THREAD RACE STATE: _____\n";
    std::vector<std::thread> vector(0);
    for (size_t i{0}; i < 10; ++i) {
        vector.emplace_back(to_pcout, i);
    }
    for (auto &t: vector) { t.join(); }
}

int main() {
    race();
    no_race();
    return 0;
}
