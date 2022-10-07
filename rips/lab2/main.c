#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>
#include <pthread.h>

const int n = 1500;

double cos_(double x) {
    return cos(x*x + 5);
}

double cube(double x) {
    return x*x*x;
}

double exp_atan(double x) {
    return exp(x) * atan(x);
}

const char* get_function(int a) {
    if (a == 0) {
        return "cos_";
    }
    if (a == 1) {
        return "cube";
    }
    if (a == 2) {
        return "exp_atan";
    }
}

double randomize(double first, double second) {
    return first + (second - first) * rand() / RAND_MAX;
}

void write_to_file(const char* filename, int l) {
    FILE* out = fopen(filename, "w");

    for (int i = 0; i < l; ++i)
        fprintf(out, "%s %lf %lf\n", get_function(rand() % 3), randomize(0, 5), randomize(randomize(0, 5), 5));
    fclose(out);
}

void select_function(double (**function)(double), const char* func_name) {
    if (strcmp(func_name, "cos_") == 0) {
        *function = cos_;
    }
    else if (strcmp(func_name, "cube") == 0) {
        *function = cube;
    }
    else {
        *function = exp_atan;
    }
}

typedef struct calculation {
    double a;
    double b;
    double (*func)(double);
    char func_name[10];
} calculation;

double integrate(double a, double b, double (*func)(double)) {
    const int k = 1000000;
    const double h = (b - a) / k;

    double res = 0;
    while (a <= b) {
        res += func(a) * h;
        a += h;
    }

    return res;
}

typedef struct info_thread {
    FILE* file;
    int n_records;
} info_thread;

void start_producer(info_thread* t_info) {
    calculation calc_info;
    for (int i = 0; i < t_info->n_records; ++i) {
        fscanf(t_info->file, "%s %lf %lf", calc_info.func_name, &calc_info.a, &calc_info.b);
        select_function(&calc_info.func, calc_info.func_name);
    }
}

void start_consumer(void* param) {
    info_thread* t_info = (info_thread*)param;
    calculation calc_info;
    for (int i = 0; i < t_info->n_records; ++i){
        fprintf(t_info->file, "integrate %s from %lf to %lf = %lf\n", calc_info.func_name, calc_info.a, calc_info.b,
                integrate(calc_info.a, calc_info.b, calc_info.func));
    }
}

void parallel(const char* in_filename, const char* out_filename) {
    FILE* in = fopen(in_filename, "r");
    FILE* out = fopen(out_filename, "w");

    int n_records;
    fscanf(in, "%d", &n_records);

    info_thread producer_info = {in, n_records};
    info_thread consumer_info = {out, n_records};

    pthread_t tid;
    pthread_create(&tid, NULL, start_consumer, (void *) &consumer_info);
    start_producer(&producer_info);
    pthread_join(tid, NULL);

    fclose(in);
    fclose(out);
}

double count_time_elapsed(void (*func)(const char*, const char*), const char* param1, const char* param2) {
    clock_t start = clock();
    func(param1, param2);
    return (double)(clock() - start) / CLOCKS_PER_SEC;
}

void sequence(const char* in_filename, const char* out_filename) {
    calculation calc_info;

    FILE* in = fopen(in_filename, "r");
    FILE* out = fopen(out_filename, "w");

    int n_records = 0;
    fscanf(in, "%d", &n_records);

    for (int i = 0; i < n_records; ++i) {
        fscanf(in, "%s %lf %lf", calc_info.func_name, &calc_info.a, &calc_info.b);
        select_function(&calc_info.func, calc_info.func_name);
        fprintf(out,"%s from %lf to %lf = %lf\n", calc_info.func_name, calc_info.a, calc_info.b, integrate(calc_info.a, calc_info.b, calc_info.func));
    }

    fclose(in);
    fclose(out);
}


int main() {
    write_to_file("/home/alex/Documents/BSU-season-5/rips/lab2/file.txt", n);
    printf("Sequence time elapsed:  %lf\n",
           count_time_elapsed(sequence, "/home/alex/Documents/BSU-season-5/rips/lab2/file.txt",
                              "/home/alex/Documents/BSU-season-5/rips/lab2/sequence.txt"));
    printf("Parallel time elapsed:   %lf\n",
           count_time_elapsed(parallel, "/home/alex/Documents/BSU-season-5/rips/lab2/file.txt",
                              "/home/alex/Documents/BSU-season-5/rips/lab2/parallel.txt"));
    return 0;
}