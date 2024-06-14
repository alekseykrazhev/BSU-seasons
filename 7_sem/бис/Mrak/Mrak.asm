.model small
.data
OblVvoda	db	15,?,15 dup (?)
.code
UU:
	mov 	ax,@data
	mov	ds,ax
	mov	ah,10
	lea	dx,OblVvoda
	int	21h
	mov	OblVvoda+12,08Fh
	mov	OblVvoda+14,08Fh
	mov 	ah,9
	lea	DX,OblVvoda+2
	mov	OblVvoda+16,'$'
	int	21h
	mov	ah,4ch
	int	21h
	end 	UU
