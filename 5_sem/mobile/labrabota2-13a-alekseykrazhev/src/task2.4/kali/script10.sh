#!/bin/bash
for myloop in 1 2 3 4 5
do
echo -n -e "Iteration #$myloop\n"
if [ "$myloop" -eq 3 ]; then
echo -e "\n To break loop after 3 iterations\n"
break

fi
done
