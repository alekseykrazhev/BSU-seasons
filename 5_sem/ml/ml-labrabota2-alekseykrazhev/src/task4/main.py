from Function import check_triangle


if __name__ == '__main__':
    a = int(input('Enter first side: '))
    b = int(input('Enter second side: '))
    c = int(input('Enter third side: '))

    if check_triangle(a, b, c):
        print('Triangle exists!')
    else:
        print("Triangle doesn't exist...")

