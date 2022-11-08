# Makefile task 1

Program: obj/main.o obj
	gcc -o bin/Program obj/main.o

obj/main.o: src/main.c
	gcc -o obj/main.o -c src/main.c `pkg-config --libs --cflags fuse`

clean:
	rm -rf obj/*.o bin/Program
