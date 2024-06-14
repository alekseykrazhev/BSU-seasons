.Model Small
.data
iter 	db 	0
tempNum db (?)
num		db (?), '$'
newRow	db 10, 13, '$'
.code
UU:
	mov ax,@data
	mov	ds,ax

	mov tempNum, 21
theStart:
	cmp iter, 2
	je theEnd

	mov ah, tempNum
	shr ah, 4

	cmp ah, 9
	jg  theLetter
theNumber:
	add ah, 30h
	jmp print
theLetter:
	add ah, 37h
print:
	mov num, ah
	lea dx, num
	mov ah, 9
	int 21h

	add iter, 1
	mov ah, tempNum
	shl ah, 4
	mov tempNum, ah
	jmp theStart

theEnd:
	lea dx, newRow
	mov ah, 9
	int 21h

	MOV	AX, 40h
	MOV	ES, AX
	MOV	BH, ES: [ 62h ]
	MOV	BL, 0
	mov ah, 0ah
	mov al, 13
	mov cx, 1
	INT	10h

	MOV		AH, 4ch
	INT 	21h
	end UU
