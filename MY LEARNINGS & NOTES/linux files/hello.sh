#! /bin/bash
echo "hello world"
# trying to write commands in script

# SYSTEM VARIABLE OF UNIX/LINUX
echo $BASH
echo $BASH_VERSION
echo $HOME
echo current working dir is  $PWD

# user defined variable 
 name=neha
echo "my name is $name"

val=10
echo value is $val


# reading input from terminal using read command
echo Enter name
read name # name is the var here to hold the value passed on terminal by user
echo Entered name is $name


echo "enter multiple names"
read name1 name2 name3
echo names entered are $name1 $name2 $name3


# read line from terminal 
echo enter line
read -p linevar 
echo  $linevar
