#! /bin/bash
trap 'echo hello' SIGALRM
(while true; do echo A; sleep 5; done)
