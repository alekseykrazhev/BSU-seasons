.MODEL SMALL
.STACK 100H

.DATA
    filename DB 'example.exe', 0
    offset   DD 1234  ; смещение, где вы хотите изменить байт
    newByte  DB 0FFh  ; новое значение байта

.CODE
MAIN PROC
    MOV AX, @DATA
    MOV DS, AX

    ; Открыть файл
    MOV AH, 3DH
    LEA DX, filename
    MOV AL, 2   ; открыть для записи
    INT 21H
    MOV BX, AX  ; сохранить дескриптор файла

    ; Установить указатель файла на нужное место
    MOV AH, 42H
    MOV AL, 0
    MOV CX, offset
    MOV DX, offset >> 16
    INT 21H

    ; Записать новое значение байта
    MOV AH, 40H
    MOV BX, AX  ; использовать дескриптор файла
    LEA DX, newByte
    MOV CX, 1   ; записать один байт
    INT 21H

    ; Закрыть файл
    MOV AH, 3EH
    INT 21H

    ; Завершение программы
    MOV AH, 4CH
    INT 21H
MAIN ENDP

END MAIN

