#!/bin/bash
# While
echo "While"
x=1

while [ $x -le 4 ]
do
echo "x = $x"
x=$(( $x + 1 ))
done

# Until
echo -e "\nUntil"
x=5
until [ $x -le 3 ]
do
echo "x = $x"
x=$(( $x - 1 ))
done
