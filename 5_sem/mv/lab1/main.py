import numpy as np

# common part start

alpha = 0.1 + 0.05 * 14
n_ = 10
h = 1 / n_


def get_recovery_points_and_values():
    first_x = x_i(0) + 2 / 3 * h
    second_x = x_i(int(n_ / 2)) + 1 / 2 * h
    third_x = x_i(n_) - 1 / 3 * h
    x1 = np.array([first_x, second_x, third_x])
    y1 = np.array([func(first_x), func(second_x), func(third_x)])
    return x1, y1


def x_i(indx: int) -> float:
    return alpha + (indx * h)


def func(x: float) -> float:
    """
    Function to interpolate.
    """
    return (alpha * np.power(np.e, x)) + (1 - alpha) * np.sin(x)


def der_11_func(x: float) -> float:
    return alpha * np.power(np.e, x) + (alpha - 1) * np.cos(x)

# end of common part


# part 1 functions start

def divided_diff(x: np.ndarray, y: np.ndarray) -> np.ndarray:
    """
    function to calculate the divided differences table.
    """
    n = len(y)
    coef = np.zeros([n, n])
    # the first column is y
    coef[:, 0] = y

    for j in range(1, n):
        for i in range(n - j):
            coef[i][j] = (coef[i + 1][j - 1] - coef[i][j - 1]) / (x[i + j] - x[i])

    return coef


def newton_poly(coef: np.ndarray, x_data: np.ndarray, x: np.ndarray) -> np.ndarray:
    """
    Evaluate the newton polynomial at x.
    """
    n = len(x_data) - 1
    p = coef[n]
    for k in range(1, n + 1):
        p = coef[n - k] + (x - x_data[n - k]) * p
    return p


def w(x: float, x_arr: np.ndarray, n: int) -> float:
    ans = 1
    for i in range(n):
        ans *= x - x_arr[i]
    return ans


def lagrange_diff(x: float, x_arr: np.ndarray, n: int) -> float:
    """
    Function to count difference in Lagrange's form
    """
    xi = 1.3
    return np.abs((w(x, x_arr, n) * der_11_func(xi)) / np.math.factorial(n + 1))


def part1():
    """
    Main method to interpolate the function.
    """
    import matplotlib.pyplot as plt
    x1, y1 = get_recovery_points_and_values()

    print(x1)

    points = np.zeros(11)
    values = np.zeros(11)
    for i in range(0, 11):
        points[i] = x_i(i)
        values[i] = func(points[i])

    a_s = divided_diff(points, values)[0, :]
    inter = newton_poly(a_s, points, x1)

    print(f'Interpolating from {alpha} to {1 + alpha}')

    print(f'Points to interpolate: {points}', end='\n')
    print(f'Values of points: {values}', end='\n')
    print()

    print(f'Interpolated in recovery points: {inter}')
    print(f'Real values in recovery points: {y1}')
    print()

    print(f'Real difference: {[np.abs(y1[i] - inter[i]) for i in range(len(y1))]}')
    print(f'Lagrange difference in x*: {lagrange_diff(x1[0], points, n_)}')
    print(f'Lagrange difference in x**: {lagrange_diff(x1[1], points, n_)}')
    print(f'Lagrange difference in x***: {lagrange_diff(x1[2], points, n_)}')

    '''
    plt.plot(points, values, 'g', label='Функция по реальным значениям')
    plt.plot(x1, inter, 'r+', label='Точки восстановления')
    plt.legend()
    plt.show()
    '''

# end of first part functions


# part2 functions start

k = 3


def diff_table(n, values):
    table = np.zeros([n, n])
    table[:, 0] = values[6:]
    for i in range(1, n):
        for j in range(0, n - i):
            table[j, i] = table[j + 1, i - 1] - table[j, i - 1]
    return table


def numerator(t, n):
    ans = 1
    for i in range(n):
        ans *= t + i
    return ans


def newton_table_end(x, n, h_, x_n, table):
    t = np.abs(x_n - x) / h_
    ans = 0
    k_ = 0
    for i in range(n - 1, -1, -1):
        ans += (numerator(t, k_) * table[i, k_]) / np.math.factorial(k_)
        k_ += 1
    return ans


def der_func(x):
    return alpha * np.power(np.e, x) - alpha * np.sin(x) + np.sin(x)


def max_der_(a, b, n):
    return max(der_func(x) for x in np.linspace(a, b, n))


def r_k(n, h_, t, a, b):
    return abs((np.power(h_, n+1) * numerator(t, n+1) * max_der_(a, b, 10)) / np.math.factorial(n+1))


def part2():
    points = np.zeros(10)
    values = np.zeros(10)
    for i in range(0, 10):
        points[i] = x_i(i)
        values[i] = func(points[i])

    third_x = x_i(n_) - 1 / 3 * h

    table = diff_table(k + 1, values)
    print(f'Table: {table}')
    inter = newton_table_end(third_x, k+1, 0.1, points[-1], table)
    print(f'Interpolated in x***: {inter}')
    t = (points[-1] - third_x) / 0.1
    print(f'r_k = {r_k(k, 0.1, t, points[-1] - (t*0.1), points[-1])}')

# end of part 2 functions


# part 3 functions start

def chebyshev_points(a: float, b: float) -> np.ndarray:
    """
    Method to get chebyshev points to interpolate.
    """
    points = np.zeros(n_+1)
    for k in range(0, n_+1):
        xk = np.cos(((2 * k + 1) * np.pi) / (2 * n_ + 2))
        points[k] = ((a + b) / 2) + (((b - a) / 2) * xk)

    return points[::-1]


def max_der(n, a, b):
    ans = max(der_11_func(x) for x in np.linspace(a, b, n))
    return ans


def r_n(n, a, b):
    return (max_der(n, a, b) / np.math.factorial(n + 1)) * (np.power(b - a, n + 1) / np.power(2, 2*n + 1))


def part3():
    """
    Using Chebyshev points to interpolate function.
    """
    points = chebyshev_points(alpha, 1 + alpha)
    print(f'Chebyshev points: {points}')

    values = np.zeros(len(points))
    # print(points.shape, values.shape)
    for i in range(len(points)):
        values[i] = func(points[i])

    print(f'Values in points: {values}')

    x1, y1 = get_recovery_points_and_values()
    print(f'Recovery points: {x1}')
    # print(x1.shape, y1.shape)
    a_s = divided_diff(points, values)[0, :]
    inter = newton_poly(a_s, points, x1)

    print(f'Interpolated in recovery points: {inter}')
    print(f'Real values in recovery points: {y1}')
    print()

    print(f'Real difference: {[np.abs(y1[i] - inter[i]) for i in range(len(y1))]}')
    '''
    print(f'Lagrange difference in x*: {lagrange_diff(x1[0], points, n_)}')
    print(f'Lagrange difference in x**: {lagrange_diff(x1[1], points, n_)}')
    print(f'Lagrange difference in x***: {lagrange_diff(x1[2], points, n_)}')
    '''
    print(f'R_n(x) <= {r_n(n_, alpha, 1 + alpha)}')

# end of part 3 functions


# part 4 functions start


def phi_i(i, x):
    return np.power(x, i)


def get_value_x(i, j, points, m):
    ans = 0
    for r in range(m):
        ans += phi_i(i, points[r]) * phi_i(j, points[r])
    return ans


def get_matrix_a(n, m, points):
    a = np.zeros([n+1, n+1])
    for i in range(n+1):
        for j in range(n+1):
            a[i][j] = get_value_x(i, j, points, m)
    return a


def get_value_y(i, values, points, m):
    ans = 0
    for r in range(m):
        ans += values[r] * phi_i(i, points[r])
    return ans


def get_vector_f(n, m, values, points):
    ans = np.zeros(n+1)
    for i in range(n+1):
        ans[i] = get_value_y(i, values, points, m)
    return ans


def phi(x, n, coeffs):
    ans = 0
    for i in range(n+1):
        ans += coeffs[i] * np.power(x, i)
    return ans


def delta(m, inter, values):
    sum_ = 0
    for i in range(m):
        sum_ += np.power(inter[i] - values[i], 2)
    return np.sqrt(sum_)


def part4():
    import matplotlib.pyplot as plt

    points = np.zeros(10)
    values = np.zeros(10)
    for i in range(0, 10):
        points[i] = x_i(i)
        values[i] = func(points[i])

    x1, y1 = get_recovery_points_and_values()

    m = 10
    n = 5

    a = get_matrix_a(n, m, points)
    print('Matrix A:')
    for i in range(len(a)):
        for j in range(len(a[i])):
            print('%.5f' % a[i][j], end=' ')
        print()

    f = get_vector_f(n, m, values, points)
    print('Vector F:')
    for i in f:
        print('%.5f' % i, end=' ')
    print()

    coeffs = np.linalg.solve(a, f)
    print(f'Vector alpha:')
    for i in coeffs:
        print('%.5f' % i, end=' ')
    print()

    for r in range(n+1):
        print(f'{coeffs[r]}*x^{r}', end=' + ')
    print()

    inter = []
    for point in points:
        inter.append(phi(point, n, coeffs))

    y1_inter = []
    for point in x1:
        y1_inter.append(phi(point, n, coeffs))

    print('Approximate values in recovery points:')
    for i in y1_inter:
        print('%.5f' % i, end=' ')
    print()

    print('Real error:')
    for i in [abs(y1[i] - y1_inter[i]) for i in range(len(y1))]:
        print('%.5e' % i, end=' ')
    print()

    delta_ = delta(m, inter, values)
    print('Delta:')
    print('%.5e' % delta_)

    '''
    plt.plot(points, inter, 'r')
    plt.show()
    '''


# end of part 4 functions


if __name__ == '__main__':
    print('\n_______________________ PART 1 ___________________________\n')
    part1()

    print('\n_______________________ PART 2 ___________________________\n')
    part2()

    print('\n_______________________ PART 3 ___________________________\n')
    part3()

    print('\n_______________________ PART 4 ___________________________\n')
    part4()
