.model small

.data
bufjunk: db 0Dh,0Ah,0h,0h,0Dh,0Ah,24h

.code
org 100h

start:
    mov ah,ds
    and ah,0Fh   ; & Low 4 bits // reversed : LITTLE ENDIAN
    shr al,4      ; & High 4 bits
    add  ax,3030h ; Convert 0 -> ASCII "0"
    cmp al,3Ah
    jb fh          ; "b":below // OK, a number
    add   al, 7
@@:
    cmp   ah, 3Ah
    jb fh          ; "b":below // OK, a number
    add   ah, 7
@@:
    mov [bufjunk+2],ax
    mov ah,9
    mov dx,bufjunk
    int 21h
    mov ax,$4C00
    int 21h             ; Exit to DOS

end start
