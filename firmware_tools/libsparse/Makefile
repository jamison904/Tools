libsparse_src_files := \
        backed_block.c \
        output_file.c \
        sparse.c \
        sparse_crc32.c \
        sparse_err.c \
        sparse_read.c

libsparse_src_obj := $(libsparse_src_files:.c=.o)

simg2img_src_files := \
	simg2img.c 

simg2img_src_obj = $(simg2img_src_files:.c=.o)

all: libsparse.a simg2img

%.o: %.c
	gcc -I. -Iinclude -DANDROID -c $<

libsparse.a: $(libsparse_src_obj)
	ar rvs libsparse.a $(libsparse_src_obj) 

simg2img: $(simg2img_src_obj) libsparse.a
	gcc $^ -o $@ -L. -lsparse -lz

.PHONY: clean
clean:
	rm $(libsparse_src_obj) libsparse.a $(simg2img_src_obj) simg2img
