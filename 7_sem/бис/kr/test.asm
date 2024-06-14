.model small
.data

message db 'DS: $'

.code
org 100h

start:
    mov ax, @data
    ; mov ds, ax

    lea dx, message
    mov ah, 09h
    int 21h

    mov ax, ds
    call print_hex

    mov ah, 4Ch
    int 21h

print_hex proc
    mov cx, 4

    print_next_digit:
        mov ax, ds
        shr ax, 12

        push ax
        and al, 0Fh
        add al, '0'
        cmp al, '9'
        jbe print_digit
        add al, 7
    print_digit:
        mov ah, 0Eh
        int 10h

        pop ax

        loop print_next_digit

    ret
print_hex endp

end start
