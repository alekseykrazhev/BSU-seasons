#include <iostream>
#include <shared_mutex>
#include <thread>
#include <vector>
#include <mutex>

using namespace std;
using namespace chrono_literals;

shared_mutex shared_mut;

using shrd_lck = shared_lock<shared_mutex>;
using uniq_lck = unique_lock<shared_mutex>;

static void print_exclusive()
{
    uniq_lck l {shared_mut, defer_lock};
    if (l.try_lock()) {
        cout << "Got exclusive lock.\n";
    } else {
        cout << "Unable to lock exclusively.\n";
    }
}

static void exclusive_throw()
{
    uniq_lck l {shared_mut};
    throw 123;
}

int main()
{
    {
        shrd_lck sl1 {shared_mut};
        cout << "shared lock once.\n";
        {
            shrd_lck sl2 {shared_mut};
            cout << "shared lock twice.\n";
            print_exclusive();
        }
        cout << "shared lock once again.\n";
        print_exclusive();
    }
    cout << "lock is free.\n";
    try {
        exclusive_throw();
    } catch (int e) {
        cout << "Got exception " << e << '\n';
    }
    print_exclusive();
}


/*
#include <iostream>
#include <shared_mutex>
#include <thread>
#include <vector>
#include <mutex>

using namespace std;
using namespace chrono_literals;

mutex mut_a;
mutex mut_b;

static void deadlock_func_1()
{
    cout << "bad f1 acquiring mutex A..." << endl;
    lock_guard<mutex> la {mut_a};
    this_thread::sleep_for(100ms);
    cout << "bad f1 acquiring mutex B..." << endl;
    lock_guard<mutex> lb {mut_b};
    cout << "bad f1 got both mutexes." << endl;
}

static void deadlock_func_2()
{
    cout << "bad f2 acquiring mutex B..." << endl;
    lock_guard<mutex> lb {mut_b};
    this_thread::sleep_for(100ms);
    cout << "bad f2 acquiring mutex A..." << endl;
    lock_guard<mutex> la {mut_a};
    cout << "bad f2 got both mutexes." << endl;
}

\
static void sane_func_1()
{
    scoped_lock l {mut_a, mut_b};
    cout << "sane f1 got both mutexes." << endl;
}
static void sane_func_2()
{
    scoped_lock l {mut_b, mut_a};
    cout << "sane f2 got both mutexes." << endl;
}

int main()
{
    {
        thread t1 {sane_func_1};
        thread t2 {sane_func_2};
        t1.join();
        t2.join();
    }
    {
        thread t1 {deadlock_func_1};
        thread t2 {deadlock_func_2};
        t1.join();
        t2.join();
    }
}
*/