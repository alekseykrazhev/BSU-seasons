Ideal
Model tiny
Dataseg
iter 	db 0
tempNum db (?),
newLine db 13, 10, '$'
num		db (?), '$'
Codeseg
org 100h
proc main

 BEGIN:
 mov	ah, 01h
 int	21h
 lea dx, newLine
 mov ah, 9
 int 21h
 mov tempNum, al
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
 mov ah, 4ch
 int 21h
endp main
end main
