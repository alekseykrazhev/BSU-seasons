#include <iostream>
#include <future>
#include <vector>
#import <math.h>

bool is_num_prime(int num) {
    if (num % 2 == 0) {
        return false;
    }

    for (int i = 3; i < sqrt(num); ++i) {
        if (num % i == 0) {
            return false;
        }
    }

    return true;
}

std::vector<int> from_a_to_b(int a, int b) {
    std::vector<int> ans;

    for (int i = a; i <= b; ++i) {
        if (is_num_prime(i)) {
            ans.push_back(i);
        }
    }

    return ans;
}

int main() {
    int a, b;

    std::cout << "Enter [a, b]:\na: ";
    std::cin >> a;
    std::cout << "b: ";
    std::cin >> b;

    // sequence program
    auto start1 = std::chrono::system_clock::now();
    auto seq = from_a_to_b(a, b);
    auto stop1 = std::chrono::system_clock::now();

    std::cout << "Sequence: " << std::chrono::duration<double>(stop1 - start1).count() << " sec. \n";

    //parallel program
    int num_of_threads = 4;
    int a_ = a;
    int step = (b - a) / num_of_threads;
    int b_ = a + step;
    std::future<std::vector<int>> vec;

    auto start2 = std::chrono::system_clock::now();
    for (int i = 0; i < num_of_threads; ++i) {
        vec = std::async(std::launch::async, from_a_to_b, a_, b_);
        a_ = b_;
        b_ = a_ + step;
    }
    auto stop2 = std::chrono::system_clock::now();
    std::cout << "Parallel: " << std::chrono::duration<double>(stop2 - stop1).count() << " sec. \n";

    return 0;
}
