import numpy as np
from scipy import integrate


a = 0.8
b = 1.8


def func(x: float) -> float:
    return 0.8 * np.exp(x) + 0.2 * np.sin(x)


def second_derivative(x: float) -> float:
    return 0.8 * np.exp(x) - 0.2 * np.sin(x)


# part 1 functions

def find_R(N: int) -> float:
    eta = 1.8
    return np.abs(((-np.power(b-a, 3)) * second_derivative(eta)) / (12 * (np.power(N, 2))))


def find_N(eps):
    N = 1
    cur = find_R(N)
    while cur >= eps:
        N += 1
        cur = find_R(N)
    return N


def get_h(N):
    return (b - a) / N


def trap_kf(h, n, list_x):
    ans = 0
    for i in range(1, n):
        ans += func(list_x[i])
    ans += (0.5 * (func(list_x[0]) + func(list_x[n])))
    return ans * h


def part1():
    print('_____________________PART_1_____________________')
    n = find_N(0.00001)
    print(f'N = {n}')
    print(f'h = {get_h(n)}')
    print(f'R_n(x) <= {find_R(n)}')

    list_x = np.linspace(a, b, n+1)
    # print(list_x)
    integration1 = trap_kf(get_h(n), n, list_x)
    print(f'Integration result: {integration1}')

    integration2, err = integrate.quad(func, a, b)
    print(f'Real integral res: {integration2}')
    print(f'Real error: {np.abs(integration1 - integration2)}')


# end of part 1 functions


# part 2 functions

def eighth_derivative(x):
    return 0.8 * np.exp(x) + 0.2 * np.sin(x)


def max_der_6():
    xi = 1.8
    return eighth_derivative(xi)


def error(n):
    err1 = (np.power(2, 2*n+3) / ((2*n+3) * np.math.factorial(2*n+2))) * (np.power(np.power(np.math.factorial(n+1), 2) / np.math.factorial(2*n+2), 2)) * max_der_6()
    return err1 * np.power((b-a)/2, 2*n+3)
    # return (max_der_6() * np.power(np.math.factorial(n), 4)) / ((2*n+1) * np.power(np.math.factorial(2*n), 3))


def p4_der(x):
    return (5/2) * x * (7 * x * x - 3)


def a_k(x):
    return 2 / ((1 - (x * x)) * np.power(p4_der(x), 2))


def gaussian_func(n, list_a, list_x):
    ans = 0
    for i in range(n+1):
        ans += list_a[i] * func(1.3 + (0.5 * list_x[i]))
    return 0.5 * ans


def part2():
    print('_____________________PART_2_____________________')
    n = 3
    values_x = [-0.339981043584856, 0.339981043584856, -0.861136311594053, 0.861136311594053]
    values_a = []
    for x in values_x:
        values_a.append(a_k(x))
    print(f'Roots of Legendre: {values_x}')
    print(f'A_k: {values_a}')

    integration1 = gaussian_func(n, values_a, values_x)
    print(f'Integration result: {integration1}')

    print(f'R_n <= {error(n)}')

    integration2, err = integrate.quad(func, a, b)
    print(f'Real integral res: {integration2}')
    print(f'Real error: {np.abs(integration1 - integration2)}')

# end of part 2 functions


if __name__ == '__main__':
    part1()
    part2()
