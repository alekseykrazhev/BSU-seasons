#!/bin/bash
printf "Which Linux distribution do you know?\n\n"

read DISTR

case $DISTR
	in ubuntu) echo "I know ubuntu." ;;
	centos|rhel) echo "I know it too.";;
	windows) echo "Very funny." ;;
	*) echo "I don't know it." ;;
esac
