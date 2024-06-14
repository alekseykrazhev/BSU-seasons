.model small
.data
    buffer db 10 dup(?)    ; Буфер для хранения символов
    msg db "Press a key: $"

.code
main proc
    mov ax, @data
    mov ds, ax

    ; Выводим приглашение
    mov ah, 09h
    lea dx, msg
    int 21h

    ; Читаем клавишу в буфер
    mov ah, 10h
    lea dx, buffer
    int 21h

    ; Выводим скан-код клавиши
    mov ah, 02h
    mov dl, buffer
    int 21h

    ; Завершаем программу
    mov ah, 4Ch
    int 21h
main endp

end main
