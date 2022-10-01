#!/bin/bash

for (( c=1; c<=10; c++ ))
do 
a=$(mcookie)
filename="file$c.$a"
touch "$filename"
echo "$filename"
done
