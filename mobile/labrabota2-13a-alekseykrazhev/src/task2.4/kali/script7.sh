# ex1.sh
#!/bin/bash

for (( abc=1; abc<=3;
abc++ ))
do echo "$abc 
	iteration"
done

# ex2.sh
#!/bin/bash
# Loop through strings

for m in 'Apple Sony Panasonic "Hewlett Packard" Nokia' 
do echo echo "Manufacturer 
	is:" $m
done

# ex3.sh
#!/bin/bash

for (( ; ; )) 
do echo "infinite loops [CTRL+C to 
	stop]"
done
