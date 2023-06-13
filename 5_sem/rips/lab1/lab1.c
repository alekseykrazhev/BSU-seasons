#include <windows.h>
#include <process.h>
#include <stdio.h>

#define p 4 // threads count
double pi[p];
int n = 1000000000;

double f1(double x) {
	return cos(x * x + 3); // checked
}

double f2(double x) {
	return x*x*x + 5*x; // checked
}

double f3(double x) {
	return tan(cos(6*x*x + x + 5)); // checked
}

DWORD WINAPI Integrate(LPVOID pvParam)
{
	int nParam = (int)pvParam;
	int i, start;
	double h, sum, x;

	h = 3. / n; // upper limit - lower limit
	sum = 0.;
	start = nParam;

	for (i = start; i < n; i += p)
	{
		x = -2 + h * i; // + lower limit
		sum += f3(x);
	}

	pi[nParam] = h * sum;

	return 0;
}

int main()
{
	HANDLE hThreads[p];
	int k;
	double sum;

	LARGE_INTEGER liFrequency, liStartTime, liFinishTime;
	// some magic
	QueryPerformanceFrequency(&liFrequency);
	// start time
	QueryPerformanceCounter(&liStartTime);

	for (k = 0; k < p; ++k)
	{
		hThreads[k] = (HANDLE)_beginthreadex(NULL, 0,
			Integrate, (LPVOID)k, 0, NULL);

		if (hThreads[k] == NULL) // error handler
		{
			printf("Create Thread %d Error=%d\n", k, GetLastError());
			return -1;
		}
	}

	WaitForMultipleObjects(p, hThreads, TRUE, INFINITE);

	for (k = 0; k < p; ++k) {
		CloseHandle(hThreads[k]);
	}

	sum = 0.;

	for (k = 0; k < p; ++k) {
		sum += pi[k];
	}

	// stop time
	QueryPerformanceCounter(&liFinishTime);
	// count time
	double dElapsedTime = 1000. * (liFinishTime.QuadPart - liStartTime.QuadPart) / liFrequency.QuadPart;

	printf("Integration result: %.16f\n", sum);
	printf("Time: %.16f\n", dElapsedTime);

	return 0;
}

