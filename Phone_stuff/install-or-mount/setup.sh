#!/bin/sh
##########################################################
# debootstrap image creator from device  v1              #
# Created by userdelroot r00t316@gmail.com               #
#                                                        #
# This script is for non-commercial use only             #
# feel free to modify and use this script                #
# as long as this header stays in tacted                 #
# enjoy!                                                 #
##########################################################


##########################################################
# initial setup after second-stage                       #
##########################################################

echo "Finalizing setup"
echo
echo

export SUDO_USER="nexus"



dpkg-divert --local --rename --add /sbin/initctl
cp /bin/true /sbin/initctl


apt-get update

echo "Downloading and Installing packages"
echo "This could take a few minutes depending on internet connection speed"
apt-get install vim desktop-base x11-xserver-utils xfonts-base openssh-server openssh-client lxde menu-xdg hicolor-icon-theme gtk2-engines xfonts-100dpi xfonts-75dpi x11vnc xvfb --no-install-recommends -yq
apt-get clean

echo "Setting user: ${SUDO_USER} with sudo access"
groupadd -g 1000 ${SUDO_USER}
# useradd -g ${SUDO_USER} ${SUDO_USER} 
#
# usermod -g ${SUDO_USER} ${SUDO_USER}
# add android groups.

adduser --uid 1000 --gid 1000 nexus

echo "Setting up required android groups"
groupadd -g 3001 aid_net_bt_admin
groupadd -g 3002 aid_net_bt
groupadd -g 3003 aid_inet
groupadd -g 3004 aid_inet_raw
groupadd -g 3005 aid_inet_admin
groupadd -g 3006 aid_net_bw_stats  # read bandwidth statistics 
groupadd -g 3007 aid_net_bw_acct   # change bandwidth statistics accounting
groupadd -g 3008 aid_net_bt_stack     # bluetooth: access config files

echo "Setting up user/groups"
gpasswd -a ${SUDO_USER} aid_net_bt_admin
gpasswd -a ${SUDO_USER} aid_net_bt
gpasswd -a ${SUDO_USER} aid_inet
gpasswd -a ${SUDO_USER} aid_inet_raw
gpasswd -a ${SUDO_USER} aid_inet_admin
gpasswd -a ${SUDO_USER} aid_net_bw_stats
gpasswd -a ${SUDO_USER} aid_net_bw_acct
gpasswd -a ${SUDO_USER} aid_net_bt_stack
gpasswd -a ${SUDO_USER} adm
gpasswd -a ${SUDO_USER} sudo
echo
echo
gpasswd -a root aid_inet
gpasswd -a root aid_inet_raw
gpasswd -a root aid_inet_admin


echo
echo "default user: ${SUDO_USER}"
echo "enter password for user ${SUDO_USER}"
echo
passwd "${SUDO_USER}"


echo
echo "Install init scripts"
cat >/home/${SUDO_USER}/lxdestart.sh <<EOF
#!/bin/bash

# make sure not aleady running
pkill Xvfb

# Check if the frame buffer is already running
pgrep Xvfb > /dev/null
if [ \$? -eq 0 ]; then
    echo "Xvfb is already running. If you want to kill it, use:"
    echo "pkill -15 Xvfb"
    exit
fi

# Launch the X11 frame buffer
geometry=\'1280x750x24\'

Xvfb -screen 0 \$geometry -ac > /dev/null 2>&1 &
export DISPLAY=:0

# Give the server a chance to start up
sleep 1

# Now start the LXDE session
startlxde > /dev/null 2>&1 &

echo "Launched LXDE session"

# Launch the VNC server
x11vnc -q -localhost -display :0 -forever -usepw > /dev/null 2>&1 &
echo "Vnc server launched"
IP=\`ifconfig wlan0 | awk '/inet addr/ {split ($2,A,":"); print A[2]}'\`
echo "Connect vnc to \$IP"
  
EOF


cat >/root/init.sh <<EOF
#!/bin/bash

export PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/sbin
export TERM=linux
export HOME=/root

#############################
# Tidy up previous sessions #
#############################
rm /tmp/.X* > /dev/null 2>&1
rm /tmp/.X11-unix/X* > /dev/null 2>&1
rm /var/run/dbus/pid > /dev/null 2>&1
rm /var/run/reboot-required* > /dev/null 2>&1


dbus-dameom --system --fork > /dev/null 2>&1


echo
echo "To shut down the Linux environment, just enter 'exit' at this terminal
      WAIT for all shutdown routines to finish!"
echo

echo "To launch LXDE enter sh lxdestart.sh, then connect via vnc"

###############################################################
# Spawn and interactive shell - this effectively halts script #
# execution until the spawning shell is exited (i.e. you want #
# to shut down vncserver and exit the ubuntu environment)     #
###############################################################

sudo -u nexus -i

#########################################
# Stop services and make sure to kill   #
# processes running, this allows Proper #
# proper unmount of chroot              #
########################################
	
	pkill -15 dbus-daemon
	pkill -15 Xvfb   # allows for clean unmount
	pkill -15 menu-cache # allows for clean unmount
	pkill -15 menu-cached
	pkill -15 lib
	/etc/init.d/ssh stop


# allow processes to stop before we fully exit
echo "Waiting for system shutdown"

sleep 3
EOF




chmod 755 /root/init.sh




echo "fixing file permissions"
mkdir -p /var/run/dbus
chown messagebus.messagebus /var/run/dbus > /dev/null 2>&1
sed -i 's/^allowed_users=.*/allowed_users=anybody/g' /etc/X11/Xwrapper.config

dpkg-divert --local --rename --add /sbin/initctl 
ln -s /bin/true /sbin/initctl

dpkg-divert --local --rename --add /usr/bin/lxde-logout
ln -s /bin/true /usr/bin/lxde-logout


echo "Setting up x11vnx"
mkdir /home/${SUDO_USER}/.x11vnc
x11vnc -storepasswd nexus /home/${SUDO_USER}/.x11vnc/passwd
echo
chown ${SUDO_USER}.${SUDO_USER} /home/${SUDO_USER}/.x11vnc
chown ${SUDO_USER}.${SUDO_USER} /home/${SUDO_USER}/.x11vnc/*
chown ${SUDO_USER}.${SUDO_USER} /home/${SUDO_USER}/lxdestart.sh*
echo "default password: nexus"
echo "You should probably change this default password"
echo "to change the default password once you login"
echo "type x11vnc -storepasswd newpass ~/.x11vnc/passwd"


locale-gen en_US en_US.UTF-8
dpkg-reconfigure tzdata

mount /proc
#stop systemd
pkill ssh
pkill ssh-agent
pkill systemd


echo "setup complete, enjoy!"
