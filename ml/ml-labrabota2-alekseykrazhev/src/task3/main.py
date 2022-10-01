
if __name__ == '__main__':
    tickets_sum = 0

    while True:
        age = input('Enter age of a visitor: ')

        if age == '':
            print('Calculation stopped.')
            break

        age = int(age)

        if age < 3:
            print('You are too young!')
            continue

        if 3 <= age <= 12:
            tickets_sum += 4.5
            continue

        if age > 65:
            tickets_sum += 8.25
            continue

        tickets_sum += 12.75

    print('*' + 'Total cost: %5.2f '.center(40) % tickets_sum + '*')
