.model tiny

MoveFPos macro F_Handle, FPos
    mov ax, 4200h
    mov bx, F_Handle
    xor cx, cx
    mov dx, FPos
    int 21h
    endm

PutStr macro Text
	mov ah,09h
	mov dx,offset Text
	int 21h
endm

seg000 segment byte public 'CODE'
    assume cs:seg000
    org 100h
    assume es:nothing, ss:nothing, ds:seg000
    public start
start:
    mov ah, 1Ah
    mov dx, offset DTA
    mov cx, 27h
    int 21h
    mov ah, 4Eh
    mov dx, offset a_MaskForVir
    int 21h
BegScan:
    push ds
    pop es
    mov si,FN_Ofs
    mov di, offset FName
    mov cx, 13
    rep movsb
    mov ah, 43h
    mov al, 0
    mov dx, offset FName
    int 21h
    mov ah, 43h
    mov al, 1
    mov dx, offset FName
    mov cl, 0
    int 21h
    mov ax, 3D10h
    mov dx, offset FName
    int 21h
    mov FHandle, ax
    mov ah, 40h
    mov cl, 44h
    nop
    nop
    ; PutStr FName
    MOV BX,OFFSET FName
    call InfoAboutFile
    ; je NextFile
 	; CALL COPY_FILE_NAME
 	; MOV BX,OFFSET FName
	PutStr FName
    ret

a_MaskForVir    db  '*.*', 0
DTA db 43 dup (0)
FN_Ofs  equ offset DTA+1Eh
FName   db 256 dup(?)
IName db 128 dup(0)
SignatureFound db 0
SignatureArray db 14 dup(0)
VirSignature    db 0CDh, 21h, 0B4h, 43h, 0B0h, 01h, 0BAh
                db 9Eh, 00h, 0B1h, 0h, 0CDh, 21h, 0B8h
Int20cmd    db 0CDh, 20h
FHandle dw 0

InfoAboutFile proc near
    PUSH ES
 	MOV AX,ES:[2CH]
 	MOV ES,AX
 	MOV SI,-1
    push ds
    ; pop es
    ; mov si, offset FName
    mov di, offset IName

NextChar:
    lodsb
    stosb
    cmp al, 0
    jne NextChar
    dec di
    mov byte ptr [di], '$'
    PutStr IName

SEARCH_01:
	INC SI
	MOV AL,ES:[SI]
	CMP AL,0
	JNE SEARCH_01
	MOV AL,ES:[SI+1]
	CMP AL,1
	JNE SEARCH_01

	ADD SI,2

COPY_NAME:
	INC SI
	MOV AL,ES:[SI]
	MOV [BX],AL
	INC BX
	CMP AL,0
	JNE COPY_NAME

	POP ES
    ret
InfoAboutFile endp

seg000 ends
end start
