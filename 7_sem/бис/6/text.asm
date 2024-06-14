.MODEL SMALL
.STACK 100H

.DATA
    inputChar DB ?
    outputMsg DB "Hexadecimal code: $"

.CODE
MAIN PROC
    MOV AX, @DATA
    MOV DS, AX

    ; Ввод символа с клавиатуры
    MOV AH, 01H
    INT 21H
    MOV inputChar, AL

    ; Вывод заголовка сообщения
    MOV AH, 09H
    LEA DX, outputMsg
    INT 21H

    ; Вывод кода символа в шестнадцатеричной форме
    MOV AH, 02H
    MOV DL, inputChar
    INT 21H

    ; Завершение программы
    MOV AH, 4CH
    INT 21H
MAIN ENDP

END MAIN
