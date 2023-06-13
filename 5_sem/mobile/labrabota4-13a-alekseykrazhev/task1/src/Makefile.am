bin_PROGRAMS = task1
CFLAGS += `pkg-config --libs --cflags fuse`
task1_SOURCES = main.c

clean-local:
	@rm config.status configure config.log
	@rm Makefile
	@rm -r autom4te.cache/
	@rm aclocal.m4
	@rm compile install-sh missing Makefile.in
