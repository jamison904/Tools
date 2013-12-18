#!/bin/bash
ans=$(zenity  --title="Conky Config App" --list  --width=300 --height=300 --text "Select task from the list below." --radiolist  --column "Run" --column "Task" FALSE "Use-default-Conky-theme" FALSE "Use-Conky-gray-theme" FALSE "Start-Conky" FALSE "Kill-Conky" FALSE "Fix-Conky-covering-windows" FALSE "Remove-Conky-from-Startup" FALSE "Add-Conky-to-Startup" FALSE "Delay-Conky-Startup-time" --separator=":")

arr=$(echo $ans | tr "\:" "\n")
clear
for x in $arr
do

	if [ $x = "Kill-Conky" ]
	then
		echo "=================================================="
		echo -e $RED"Conky"$ENDCOLOR
		/usr/bin/notify-send "Killing Conky"
		killall conky
	fi
	if [ $x = "Start-Conky" ]
	then
		echo "=================================================="
		echo -e $RED"Starting Conky"$ENDCOLOR
		/usr/bin/notify-send "Starting Conky"
		conky
	fi
	if [ $x = "Remove-Conky-from-Startup" ]
	then
		echo "=================================================="
		echo -e $RED"Removing Conky from Startup Applications"$ENDCOLOR
		/usr/bin/notify-send "Removing Conky from Startup Applications"
		mkdir ~/.conky-backup 
                cp ~/.config/autostart/.startconky.sh.desktop ~/.conky-backup 
                rm ~/.config/autostart/.startconky.sh.desktop

	fi
	if [ $x = "Use-default-Conky-theme" ]
	then
		echo "=================================================="
		echo -e $RED"Setting up the Conky default theme"$ENDCOLOR
		/usr/bin/notify-send "Setting up the Conky default theme"
		killall conky 
                rm ~/.conkyrc
                cp ~/.conkyrc.default ~/.conkyrc
                conky
	fi

	if [ $x = "Use-Conky-gray-theme" ]
	then
		echo "=================================================="
		echo -e $RED"Setting up the Conky Gray theme"$ENDCOLOR
		/usr/bin/notify-send "Setting up the Conky Gray theme"
		killall conky 
                rm ~/.conkyrc
                cp ~/.conkyrc.gray ~/.conkyrc
                conky

        fi

	if [ $x = "Fix-Conky-covering-windows" ]
	then
		echo "=================================================="
		echo -e $RED"Killing/Starting Conky"$ENDCOLOR
		/usr/bin/notify-send "Killing/Starting Conky"
		killall conky && conky
	fi

if [ $x = "Add-Conky-to-Startup" ]
	then
		echo "=================================================="
		echo -e $RED"Adding Conky to Startup Applications"$ENDCOLOR
		/usr/bin/notify-send "Adding Conky to Startup Applications"
		cp ~/.conky-backup/.startconky.sh.desktop ~/.config/autostart/
	fi

if [ $x = "Delay-Conky-Startup-time" ]
	then
		echo "=================================================="
		echo -e $RED"Editing startconky.sh"$ENDCOLOR
		/usr/bin/notify-send "Editing startconky.sh"
		gksudo gedit /.startconky.sh
	fi
done
