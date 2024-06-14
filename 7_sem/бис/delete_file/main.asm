 .model small
.data
    filename db 100 dup(0)
    buffer   db 100 dup(0)
    status   dw ?

.code
main:
    mov ax, @data
    mov ds, ax

    ; Запрашиваем имя файла
    lea dx, filename
    mov ah, 0Ah
    int 21h

    ; Формируем команду удаления
    lea dx, buffer
    mov ah, 09h
    int 21h

    ; Вызываем команду удаления
    mov ah, 40h
    lea dx, buffer
    int 21h

    ; Проверяем статус операции
    mov ah, 4Dh
    lea dx, status
    int 21h

    jmp  success

success:
    mov ah, 4Ch
    int 21h

    mov ax, 4C01h
    int 21h
end main
