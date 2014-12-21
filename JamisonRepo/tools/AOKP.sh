#!/bin/bash

echo "##############################################"
echo "#                                            #"
echo "#               Building AOKP                #"
echo "#                                            #"
echo "#                                            #"
echo "##############################################"

# Starting Timer
START=$(date +%s)
DEVICE="$1"
ADDITIONAL="$2"
THREADS=`cat /proc/cpuinfo | grep processor | wc -l`

echo "Making directory"
mkdir AOKP
cd AOKP

echo "Initializing Repo"
repo init -u https://github.com/AOKP/platform_manifest.git -b jb

echo "Repo Syncing"
repo sync

END=$(date +%s)
ELAPSED=$((END - START))
E_MIN=$((ELAPSED / 60))
E_SEC=$((ELAPSED - E_MIN * 60))
printf "Elapsed: "
[ $E_MIN != 0 ] && printf "%d min(s) " $E_MIN
printf "%d sec(s)\n" $E_SEC
