#!/bin/bash
# 1-min_size, 2-max_size, 3-catalog

for i in $(find "$3" -size "+$1c" -size "-$2c" -type f)
do
 ls -lh "$i" | awk '{print $5, $9}'
done
