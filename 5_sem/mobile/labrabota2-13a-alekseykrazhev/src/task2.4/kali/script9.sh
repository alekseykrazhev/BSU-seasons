#!/bin/bash
for myloop in 1 2 3 4 5
do

if [ "$myloop" -eq 3 ]; then
echo -e "To skip iteration No. 3\n"
continue
# Skip rest of this iteration

fi

echo -n -e "Iteration #$myloop \n"

done
