#!/usr/bin/awk -f
#
# Print lines with num without header
#

NR > 1 {
printf "%d: %s\n",NR,$0
}
