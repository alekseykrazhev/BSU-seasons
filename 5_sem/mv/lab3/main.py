import numpy as np


x0 = 0.8
X = 1.8
u0 = 1.5391231687791029087912519577858707831051714
alpha0 = 0
alpha1 = 1
A1 = 0.5
A2 = 0.5
list_y_real = []


def real_solution(x):
    return x * (0.8 * np.exp(x) + 0.2 * np.sin(x) - 2.77556 * 10e-16)


def der_func(x):
    return 0.8 * np.exp(x) + 0.2 * np.cos(x)


def func(x, u):
    return (u / x) + (x * der_func(x))


def euler(x: np.ndarray) -> np.ndarray:
    y = np.ndarray([11])
    y[0] = u0
    h = 0.1
    for i in range(1, 11):
        y[i] = y[i-1] + h * func(x[i-1], y[i-1])
    return y


def mpppt(x: np.ndarray) -> np.ndarray:
    y = np.ndarray([11])
    y[0] = u0
    for i in range(1, 11):
        y1 = y[i-1] + (0.1 * func(x[i-1], y[i-1]))
        y[i] = y[i-1] + (0.05 * (func(x[i-1], y[i-1]) + func(x[i], y1)))
    return y


def part1():
    import matplotlib.pyplot as plt
    print('____________________PART_1____________________')
    list_x = np.linspace(x0, X, 11)
    print(f'Points: {list_x}')
    list_y = euler(list_x)
    print(f'Values by Euler: {list_y}')

    for i in list_x:
        list_y_real.append(real_solution(i))

    list_y1 = mpppt(list_x)
    print(f'Values by MPPPT: {list_y1}')

    # print(f'Real values: {list_y_real}')

    '''
    plt.plot(list_x, list_y, 'b', label='euler')
    plt.plot(list_x, list_y1, 'r', label='mpppt')
    plt.plot(list_x, list_y_real, 'g', label='real')
    plt.legend(loc='upper left')
    plt.show()
    '''

    return list_y, list_y1


def part2(y1, y2):
    print('____________________PART_2____________________')

    error1 = []
    error2 = []
    for i in range(11):
        error1.append(abs(y1[i] - list_y_real[i]))
        error2.append(abs(y2[i] - list_y_real[i]))

    print('Real errors using Euler:')
    for i in error1:
        print('%.5f' % i, end=' ')
    print()

    print('Real errors using MPPPT:')
    for i in error2:
        print('%.5f' % i, end=' ')
    print()


if __name__ == '__main__':
    euler_y, second_y = part1()

    part2(euler_y, second_y)
