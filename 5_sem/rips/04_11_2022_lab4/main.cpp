#include <iostream>
#include <algorithm>
#include <execution>
#include <string>
#include <string_view>
#include <chrono>

std::string generate_string(int len) {
    static const char alphanum[] =
            "0123456789"
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            "abcdefghijklmnopqrstuvwxyz";

    std::string tmp_s;
    tmp_s.reserve(len);

    for (int i = 0; i < len; ++i) {
        tmp_s += alphanum[rand() % (sizeof(alphanum) - 1)];
    }

    return tmp_s;
}

void do_task() {
    static std::string alph = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // std::string str = "Afa37JncCHryDsbzayy4cBWDxS22pathMaiRrV41textYvKWrO72tKLKe1zLOZ2nOXpPIhMFSv8kP7U2oJ9x";
    std::string str = generate_string(1000000);

    std::chrono::steady_clock::time_point begin = std::chrono::steady_clock::now();
    auto noSpaceEnd = std::remove_if(std::execution::par_unseq, str.begin(), str.end(), [](char x){return alph.contains(x);});
    std::chrono::steady_clock::time_point end = std::chrono::steady_clock::now();

    std::cout << "Time elapsed = " << std::chrono::duration_cast<std::chrono::milliseconds>(end - begin).count() << "[ms]" << std::endl;
    // std::cout << std::string_view(str.begin(), noSpaceEnd) << " size: " << str.size() << std::endl;
}

int main() {
    do_task();
    return 0;
}
