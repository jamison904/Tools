#!/bin/sh

version=1.0

chmod -R +x tool
PATH=tool:$PATH

while :
do

  clear
  echo 
  echo "##########################################################"
  echo "#               Jamison904 re-sync repo                  #"
  echo "#========================================================#"  
  echo "#                  1 - Repo sync                         #"
  echo "##########################################################"
  echo "#                  x - Exit                              #"
  echo "##########################################################"
  echo 
  echo -n "Enter option: "
  read opt
  
  if [ "$?" != "1" ]
  then
    case $opt in
     1) sh tool/repo.sh; echo "Done.";;
      x) clear; echo; echo "Goodbye."; echo; exit 1;;
      *) echo "Invalid option"; continue;;
    esac
  fi

  tools/press_enter

done

