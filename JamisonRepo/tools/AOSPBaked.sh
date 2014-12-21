#!/bin/bash

echo "##############################################"
echo "#                                            #"
echo "#               Baked AOSP                   #"
echo "#                                            #"
echo "#                                            #"
echo "##############################################"

# Starting Timer
START=$(date +%s)
DEVICE="$1"
ADDITIONAL="$2"
THREADS=`cat /proc/cpuinfo | grep processor | wc -l`

echo "Making directory"
mkdir AOSP
cd AOSP

echo "Initializing Repo"
repo init -u https://github.com/TeamBAKED/platform_manifest.git -b jb-4.2

echo "Repo Syncing"
repo sync

END=$(date +%s)
ELAPSED=$((END - START))
E_MIN=$((ELAPSED / 60))
E_SEC=$((ELAPSED - E_MIN * 60))
printf "Elapsed: "
[ $E_MIN != 0 ] && printf "%d min(s) " $E_MIN
printf "%d sec(s)\n" $E_SEC
