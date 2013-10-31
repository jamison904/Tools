#!/bin/bash

# ---------------------------------------------------------
# >>> Init Vars
  HOMEDIR=${PWD}
  # JOBS=`cat /proc/cpuinfo | grep processor | wc -l`;
  # If you uncomment the "JOBS" var make sure you comment the 
  # "JOBS" var down below in the build config
# ---------------------------------------------------------

# ---------------------------------------------------------
# >>> AOSP 4.3 for S4 (i9505)
# >>> Copyright 2013 broodplank.net
# >>> REV3
# ---------------------------------------------------------

# ---------------------------------------------------------
# >>> Check for updates before starting?
#
  CHECKUPDATES=0        # 0 to disable, 1 for repo sync
# ---------------------------------------------------------

# ---------------------------------------------------------
#
# >>> BUILD CONFIG
#
# ---------------------------------------------------------
#
# >>> Main Configuration (intended for option 6, All-In-One) 
#
  JOBS=5                 # CPU Cores + 1 (also hyperthreading)
  MAKEPARAM=""           # Additional make parameter, for example
#                          '-k LOCAL_MODULE_TAGS:=optional' 
# ---------------------------------------------------------

export USEROLD=`whoami`;
export ULENGTH=`expr length ${USEROLD}`
if [[ ${ULENGTH} -gt 9 ]]; then
        clear
        echo
        echo
        echo
        echo "Your username seems to exceed the max of 9 characters (${ULENGTH} chars)"
        echo "Due to a temp issue with bionic the max amount of characters is limited."
        echo "If the limit is exceeded the camera refuses to take pictures"
        echo 
        echo "Do you want to pick a new username right now that's below 9 chars? ( y / n )"
        read choice
        echo
        if [[ ${choice} == "n" ]]; then
                echo "Taking pictures with camera won't work, you're warned!"
                echo
                echo "Continuing..."
        else
                echo "New username:"
                read username
                export USER=${username}
                echo
                echo "Replacing current username ${USEROLD} with new username ${choice}"
                echo        
        fi;
fi;

# Starting Timer
START=$(date +%s)
DEVICE="$1"
ADDITIONAL="$2"
THREADS=`cat /proc/cpuinfo | grep processor | wc -l`

echo "Building jfltetmo"
make clean && . build/envsetup.sh && brunch flo -j8

END=$(date +%s)
ELAPSED=$((END - START))
E_MIN=$((ELAPSED / 60))
E_SEC=$((ELAPSED - E_MIN * 60))
printf "Elapsed: "
[ $E_MIN != 0 ] && printf "%d min(s) " $E_MIN
printf "%d sec(s)\n" $E_SEC
