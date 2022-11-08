//
// Created by alex on 11/4/22.
//

#include <atomic>
#include <vector>
#include <chrono>
#include <iostream>
#include <random>

void sumUp(
        std::atomic<unsigned long long>& sum,
        const std::vector<int>& val,
        unsigned long long beg,
        unsigned long long end)
{
    for (auto it = beg; it < end; ++it) {
        sum += val[it];
    }
}

void sumUp2(
        std::atomic<unsigned long long>& sum,
        const std::vector<int>& val,
        unsigned long long beg,
        unsigned long long end)
{
    for (auto it = beg; it < end; ++it) {
        sum.fetch_add(val[it], std::memory_order_relaxed);
    }
}

void sumUp1(
        std::atomic<unsigned long long>& sum,
        const std::vector<int>& val,
        unsigned long long beg,
        unsigned long long end)
{
    for (auto it = beg; it < end; ++it) {
        sum.fetch_add(val[it]);
    }
}

constexpr long long size = 1000000000;

int main() {
    std::cout << std::endl;

    std::vector<int> randValues;
    randValues.reserve(size);

    // random values
    std::random_device seed;
    std::mt19937 engine(seed());
    std::uniform_int_distribution<> uniformDist(1, 10);
    for (long long i = 0; i < size; ++i)
        randValues.push_back(uniformDist(engine));

    std::atomic<unsigned long long> sum = {};

    auto sta = std::chrono::steady_clock::now();

    sumUp1(sum, randValues, 0, size);

    const auto end1 = std::chrono::steady_clock::now();
    const std::chrono::duration<double> dur1 = end1 - sta;

    std::cout << "Time for addition " << dur1.count() << " seconds" << std::endl;
    std::cout << "Result: " << sum << std::endl;

    return 0;
}