#!/bin/bash

if (umask $2 | touch $1) then
echo "access set successfully!"
else
echo "$1: error changing eccess: $2"
exit 1
fi
umask 123
