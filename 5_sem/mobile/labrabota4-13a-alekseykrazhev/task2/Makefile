# Makefile task 2

Program: obj/main.o obj
	gcc -o bin/Program obj/main.o

obj/main.o: src/main.c
	gcc -o obj/main.o -c src/main.c

clean:
	rm -rf obj/*.o bin/Program
