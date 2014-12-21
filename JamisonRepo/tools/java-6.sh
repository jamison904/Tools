#!/bin/bash

echo "##############################################"
echo "#                                            #"
echo "#               Installing java              #"
echo "#                                            #"
echo "#                                            #"
echo "##############################################"

# Starting Timer
START=$(date +%s)
DEVICE="$1"
ADDITIONAL="$2"
THREADS=`cat /proc/cpuinfo | grep processor | wc -l`

echo "Downloading the source..."
cd ~/
wget https://github.com/flexiondotorg/oab-java6/raw/0.2.6/oab-java.sh -O oab-java.sh
chmod +x oab-java.sh
echo "Building Java"
sudo ./oab-java.sh

echo "Opening Tail Terminal..."
echo
tail -f ./oab-java.sh.log
echo "Finished..."
echo 
echo "Installing Java..."
echo
sudo apt-get install sun-java6-jdk
echo
echo "All done....Enjoy!!"

END=$(date +%s)
ELAPSED=$((END - START))
E_MIN=$((ELAPSED / 60))
E_SEC=$((ELAPSED - E_MIN * 60))
printf "Elapsed: "
[ $E_MIN != 0 ] && printf "%d min(s) " $E_MIN
printf "%d sec(s)\n" $E_SEC
