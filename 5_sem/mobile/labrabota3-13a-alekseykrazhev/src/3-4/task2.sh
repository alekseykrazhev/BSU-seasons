#! /bin/bash

(while true; do ls >> res.txt; sleep 5; done) &
(while true; do wc res.txt >> res.txt; sleep 5; done) &
