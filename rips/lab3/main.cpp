#include <windows.h>
#include <iostream>
#include <process.h>
#include <thread>
#include <mutex>

using namespace std;

const int n = 10000;
const int m = 700;
int nthreads = 40;
int period = m / nthreads;
double vec[n];
double mat[m][n];
double res[m];
mutex mtx;


DWORD WINAPI Multiply(LPVOID param)
{
    int i = (int)param;

    for (int j = i; j < i + period; j++)
    {
        //mtx.lock();
        //cout << "Thread at index " << j << endl;
        //mtx.unlock();
        for (int k = 0; k < n; k++)
        {
            res[j] += mat[j][k] * vec[k];
        }
    }
    return 0;
}


int main()
{
    cout << "STARTING FIRST MODEL..." << endl;
    for (int i = 0; i < n; i++)
    {
        vec[i] = (double)(rand() % 1000) / 100;
        for (int j = 0; j < m; j++)
        {
            res[j] = 0;
            mat[j][i] = (double)(rand() % 1000) / 100;
        }
    }
    HANDLE* hThreads = new HANDLE[nthreads];
    DWORD start = GetTickCount();
    for (int i = 0; i < nthreads; i++)
    {
        hThreads[i] = CreateThread(NULL, 0,
                                   Multiply, (LPVOID)(i*period), 0, NULL);
        if (hThreads[i] == NULL)
        {
            printf("Create Thread %d Error=%d\n", i, GetLastError());
            return -1;
        }
    }

    WaitForMultipleObjects(nthreads, hThreads, TRUE, INFINITE);
    DWORD end = GetTickCount() - start;

    for (int i = 0; i < nthreads; i++)
        CloseHandle(hThreads[i]);

    cout << "TIME FIRST: " << end << endl;
    cout << "STARTING SECOND MODEL..." << endl;

    for (int i = 0; i < n; i++)
    {
        vec[i] = (double)(rand() % 1000) / 100;
        for (int j = 0; j < m; j++)
        {
            res[j] = 0;
            mat[j][i] = (double)(rand() % 1000) / 100;
        }
    }
    HANDLE* hThreads = new HANDLE[nthreads];

    DWORD start = GetTickCount();
    for (int i = 0; i < nthreads; i++)
    {
        hThreads[i] = CreateThread(NULL, 0,
                                   Multiply, (LPVOID)(i * period), 0, NULL);
        if (hThreads[i] == NULL)
        {
            printf("Create Thread %d Error=%d\n", i, GetLastError());
            return -1;
        }
    }

    WaitForMultipleObjects(nthreads, hThreads, TRUE, INFINITE);
    DWORD end = GetTickCount() - start;

    for (int i = 0; i < nthreads; i++)
        CloseHandle(hThreads[i]);

    cout << "TIME SECOND: " << end << endl;
}
