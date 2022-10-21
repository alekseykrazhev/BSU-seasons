#!/bin/bash
#USAGE: ./script dir file min_size max_size [create_date_min [create_date_max [max_procs]]]
#outputs to FILE <PID FULL_PATH FILE_NAME FILE_SIZE TOTAL_FILES> for each subdirectory
#+in DIR by separate processes with total amout of its do not exeed MAX_PROC
 
max_procs=${7:-1}
before_date=$(date +%s -d ${6:-today})
from_date=$(date +%s -d ${5:-19700101})
 
find "$1" -type f -size +$3 -size -$4 -newermt @$from_date ! -newermt @$before_date \
    -execdir readlink -ne . \; \
    -printf " %f %s %C+ total " \
    -execdir bash -c 'ls -AF . | grep -cv "/$" ' \; |
tee "$2"
