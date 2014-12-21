#!/bin/bash

echo "##############################################"
echo "#                                            #"
echo "#               Build Packages               #"
echo "#        Follw me on twitter Jamison904      #"
echo "#                                            #"
echo "##############################################"

# Starting Timer
START=$(date +%s)
DEVICE="$1"
ADDITIONAL="$2"
THREADS=`cat /proc/cpuinfo | grep processor | wc -l`

echo "Downloading Packages"
sudo apt-get install git-core gnupg flex bison gperf build-essential \
  zip curl libc6-dev libncurses5-dev:i386 x11proto-core-dev \
  libx11-dev:i386 libreadline6-dev:i386 libgl1-mesa-glx:i386 \
  libgl1-mesa-dev g++-multilib mingw32 openjdk-6-jdk tofrodos \
  python-markdown libxml2-utils xsltproc zlib1g-dev:i386
sudo ln -s /usr/lib/i386-linux-gnu/mesa/libGL.so.1  /usr/lib/i386-linux-gnu/libGL.so
echo
echo "Downloading Bin/Repo"
mkdir ~/bin
PATH=~/bin:$PATH
curl https://dl-ssl.google.com/dl/googlesource/git-repo/repo > ~/bin/repo
chmod a+x ~/bin/repo
echo
echo
echo
echo "Finished!"
echo
echo "Now Choose your Repo Sync from the main menu!"

END=$(date +%s)
ELAPSED=$((END - START))
E_MIN=$((ELAPSED / 60))
E_SEC=$((ELAPSED - E_MIN * 60))
printf "Elapsed: "
[ $E_MIN != 0 ] && printf "%d min(s) " $E_MIN
printf "%d sec(s)\n" $E_SEC
