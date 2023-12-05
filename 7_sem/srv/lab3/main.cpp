#include <iostream>
#include <thread>
#include <mutex>
#include <condition_variable>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <chrono>

class Train {
public:
    int id;
    double position;
    double speed;

    Train(int id, double speed) : id(id), position(0.0), speed(speed) {}
};

class RailSwitch {
public:
    int id;
    bool isUp;

    explicit RailSwitch(int id) : id(id), isUp(false) {}
};

class Semaphore {
public:
    int id;
    bool isGreen;

    explicit Semaphore(int id) : id(id), isGreen(false) {}
};

class DispatchSystem {
public:
    DispatchSystem() : isRunning(true) {}

    void StartSimulation() {
        InitializeRailroadObjects();
        std:: cout << "Starting dispatching station simulation\n";

        std::thread trainThread(&DispatchSystem::TrainSimulation, this);
        std::thread sensorThread(&DispatchSystem::SensorSimulation, this);

        trainThread.join();
        sensorThread.join();
    }

private:
    std::vector<Train> trains;
    std::vector<RailSwitch> switches;
    std::vector<Semaphore> semaphores;

    std::mutex trainMutex;
    std::mutex switchMutex;
    std::mutex semaphoreMutex;

    std::condition_variable trainCV;
    std::condition_variable switchCV;
    std::condition_variable semaphoreCV;

    bool isRunning;

    void InitializeRailroadObjects() {
        std::cout << "Initializing simulation objects\n";
        for (int i = 0; i < 3; ++i) {
            trains.emplace_back(i + 1, static_cast<double>(rand() % 10 + 1));
            switches.emplace_back(i + 1);
            semaphores.emplace_back(i + 1);
        }
    }

    void TrainSimulation() {
        while (isRunning) {
            {
                std::unique_lock<std::mutex> lock(trainMutex);
                for (auto& train : trains) {
                    train.position += train.speed;
                    std::cout << "Current train position: " << train.position << '\n';
                }
            }

            trainCV.notify_all();

            std::this_thread::sleep_for(std::chrono::milliseconds(5000));
        }
    }

    void SensorSimulation() {
        while (isRunning) {
            {
                std::unique_lock<std::mutex> lock(switchMutex);
                for (auto& railSwitch : switches) {
                    railSwitch.isUp = (rand() % 2 == 0);
                    std::cout << "Rail Switch is up: " << railSwitch.isUp << '\n';
                }
            }

            {
                std::unique_lock<std::mutex> lock(semaphoreMutex);
                for (auto& semaphore : semaphores) {
                    semaphore.isGreen = (rand() % 2 == 0);
                    std::cout << "Is semaphore green: " << semaphore.isGreen << '\n';
                }
            }

            switchCV.notify_all();
            semaphoreCV.notify_all();

            std::this_thread::sleep_for(std::chrono::milliseconds(5000));
        }
    }
};

int main() {
    std::srand(static_cast<unsigned>(std::time(nullptr)));

    std::cout << "Start program execution\n";
    DispatchSystem dispatchSystem;
    dispatchSystem.StartSimulation();

    return 0;
}