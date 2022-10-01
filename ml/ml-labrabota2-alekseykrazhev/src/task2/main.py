
if __name__ == '__main__':
    dec_level = int(input('Enter noise level (dcb): '))

    levels = {130: 'Отбойный молоток', 106: 'Газовая газонокосилка', 70: 'Будильник', 40: 'Тихая комната'}

    if dec_level in levels.keys():
        print(levels[dec_level])
        quit(0)

    if dec_level < 40:
        print('Меньше тихой комнаты...')
        quit(0)

    if dec_level > 130:
        print('Больше отбойного молотка!')
        quit(0)

    if 40 < dec_level < 70:
        print('Между', levels[40], 'и', levels[70])

    if 70 < dec_level < 106:
        print('Между', levels[70], 'и', levels[106])

    if 106 < dec_level < 130:
        print('Между', levels[106], 'и', levels[130])

