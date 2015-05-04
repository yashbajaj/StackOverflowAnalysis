gcc CorePeriphery.c  -I/usr/local/include/igraph  -L/usr/local/lib -ligraph -lm -o Core
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/lib
./Core < 2013_2_new.txt
