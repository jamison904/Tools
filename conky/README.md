add all files to "home" folder then

sudo apt-get install conky conky-all

cd && wget -O .start-conky http://goo.gl/qvhpJ

chmod +x .start-conky


Conky How-to Guide - http://forum.pinguyos.com/Thread-Conky-How-to-Guide

[quote='pinguy' pid='142' dateline='1286244463']
For this Conky setup you will need a screen resolution of 1600x1200 or larger for it to fit, otherwise you will need to remove items. Also make sure you remove any extra CPU info, otherwise it may not load.

[B].conkyrc[/B][CODE]# Use Xft?
use_xft yes
xftfont Droid Sans:size=8
xftalpha 0.8
text_buffer_size 2048

# Update interval in seconds
update_interval 1

# This is the number of times Conky will update before quitting.
# Set to zero to run forever.
total_run_times 0

# Create own window instead of using desktop (required in nautilus)
own_window yes
own_window_transparent yes
own_window_type override
#own_window_hints undecorated,below,sticky,skip_taskbar,skip_pager

# Use double buffering (reduces flicker, may not work for everyone)
double_buffer yes

# Minimum size of text area
minimum_size 182 0
maximum_width 182

# Draw shades?
draw_shades no
default_color D6D6D6 #4D4D4D
# Draw outlines?
draw_outline no

# Draw borders around text
draw_borders no

# Stippled borders?
stippled_borders 0

# border margins
border_margin 5

# border width
border_width 1

# Text alignment, other possible values are commented
#alignment top_left
alignment top_right
#alignment bottom_left
#alignment bottom_right

# Gap between borders of screen and text
# same thing as passing -x at command line
gap_x 25
gap_y 40

# -- Lua Load -- #
lua_load ~/.draw_bg.lua
lua_draw_hook_pre draw_bg

TEXT
SYSTEM ${hr 2} 
${goto 6}${voffset 6}${font OpenLogos:size=22}u${font}${goto 36}${voffset -18}${pre_exec cat /etc/issue.net} $machine
${goto 36}Kernel: ${kernel} 
${hr 2}

${goto 6}${font StyleBats:size=16}A${font}${voffset -4}${goto 32}CPU1: ${cpu cpu0}% ${alignr}${cpubar cpu0 8,60}
${goto 6}${voffset 4}${font StyleBats:size=16}A${font}${voffset -4}${goto 32}CPU2: ${cpu cpu1}% ${alignr}${cpubar cpu1 8,60}
${goto 6}${voffset 4}${font StyleBats:size=16}A${font}${voffset -4}${goto 32}CPU3: ${cpu cpu2}% ${alignr}${cpubar cpu2 8,60}
${goto 6}${voffset 4}${font StyleBats:size=16}A${font}${voffset -4}${goto 32}CPU4: ${cpu cpu3}% ${alignr}${cpubar cpu3 8,60}
${goto 6}${voffset 4}${font StyleBats:size=16}g${font}${voffset -4}${goto 32}RAM: ${mem} ${alignr}${membar 8,60}
${goto 6}${voffset 4}${font StyleBats:size=16}x${font}${voffset -4}${goto 32}DISK: ${diskio}${alignr}${diskiograph 8,60 F57900 FCAF3E} 
${goto 6}${voffset 4}${font StyleBats:size=16}j${font}${voffset -4}${goto 32}SWAP: $swapperc% ${alignr}${swapbar 8,60}
${goto 6}${voffset 4}${font Devil inside:size=16}1${font}${voffset -4}${goto 32}CPU Temp: ${alignr}${exec sensors | grep 'CPU Temperature' | cut -c19-20}°C / ${color #FCAF3E}${exec sensors | grep 'CPU Temperature' | cut -c37-38}°C$color
${goto 5}${voffset 4}${font Devil inside:size=16}x${font}${voffset -4}${goto 32}Mother Temp: ${alignr}${exec sensors | grep 'MB Temperature' | cut -c19-20}°C / ${color #FCAF3E}${exec sensors | grep 'MB Temperature' | cut -c37-38}°C$color
${goto 4.5}${voffset 2}${font Poky:size=15}y${font}${voffset -6}${goto 32}HD Temp:${alignr}${exec hddtemp /dev/sda -n --unit=C}°C / ${color #FCAF3E}${exec sensors | grep 'CPU Temperature' | cut -c54-55}°C$color
${goto 6}${font Martin Vogel's Symbols:size=16}j${font}${voffset -4}${goto 32}Fan Speed: ${alignr}${exec sensors | grep 'RPM'| cut -c16-25}
${goto 6}${voffset 4}${font StyleBats:size=16}q${font}${voffset -4}${goto 32}Uptime: ${alignr}${uptime}
${goto 6}${voffset 4}${font StyleBats:size=16}k${font}${voffset -4}${goto 32}Processes: ${alignr}$processes ($running_processes running)
${goto 7.5}${voffset 4}${font Poky:size=15}a${font}${goto 32}${voffset -10}Highest: ${alignr 13}CPU${alignr}RAM
${goto 32}${voffset -5.5}${hr 1}
${voffset -1}${goto 32}${top name 1} ${goto 124}${top cpu 1}${alignr }${top mem 1}
${voffset -1}${goto 32}${top name 2} ${goto 124}${top cpu 2}${alignr }${top mem 2}
${voffset -1}${goto 32}${top name 3} ${goto 124}${top cpu 3}${alignr }${top mem 3}
${voffset -1}${goto 32}${top name 4} ${goto 124}${top cpu 4}${alignr }${top mem 4}

${voffset -1}DATE ${hr 2}
${alignc 35}${font Arial Black:size=26}${time %H:%M}${font}
${alignc}${time %A %d %B}

${voffset -1}HD ${hr 2}
${goto 3}${voffset 4}${font Poky:size=16}H${font}${goto 29}${voffset -11} Root: ${fs_used_perc /}%${alignr}${fs_size /}
${goto 29} Free: ${fs_free /}${alignr}${fs_bar 8,60 /}
${goto 3}${voffset 8}${font Poky:size=16}H${font}${goto 29}${voffset -11} Home: ${fs_used_perc /home}%${alignr}${fs_size /home}
${goto 29} Free: ${fs_free /home}${alignr}${fs_bar 8,60 /home}

${voffset -1}NETWORK ${hr 2}
${if_up wlan0} 
${font Poky:size=14}Y${font}${goto 32}${voffset -8}SSID: ${wireless_essid wlan0}
${goto 32}Signal: ${wireless_link_qual wlan0}% ${alignr}${wireless_link_bar 8,60 wlan0}
${voffset 4}${font VariShapes Solid:size=14}q${font}${goto 32}${voffset -6}Up: ${upspeed wlan0}${font} ${alignr}${upspeedgraph wlan0 8,60 F57900 FCAF3E}
${goto 32}Total: ${totalup wlan0}
${voffset 4}${font VariShapes Solid:size=14}Q${font}${goto 32}${voffset -6}Down: ${downspeed wlan0}${font} ${alignr}${downspeedgraph wlan0 8,60 F57900 FCAF3E}
${goto 32}Total: ${totaldown wlan0}
${voffset 4}${font Poky:size=13}w${font}${goto 32}${voffset -8}Local IP: ${alignr}${addr wlan0}
${goto 32}Public IP: ${alignr}${execi 3600 wget -O - http://whatismyip.org/ | tail}
# |--ETH0
${else}${if_up eth0}
${voffset -13}${font VariShapes Solid:size=14}q${font}${goto 32}${voffset -6}Up: ${upspeed eth0}${font} ${alignr}${upspeedgraph eth0 8,60 F57900 FCAF3E}
${goto 32}Total: ${totalup eth0}
${voffset -2}${font VariShapes Solid:size=14}Q${font}${goto 32}${voffset -6}Down: ${downspeed eth0}${font} ${alignr}${downspeedgraph eth0 8,60 F57900 FCAF3E}
${goto 32}Total: ${totaldown eth0}
${voffset -2}${font Poky:size=13}w${font}${goto 32}${voffset -4}Local IP: ${alignr}${addr eth0}
${goto 32}Public IP: ${alignr}${execi 3600 wget -O - http://whatismyip.org/ | tail}
# |--PPP0
${endif}${else}${if_up ppp0}
${voffset -13}${font VariShapes Solid:size=14}q${font}${goto 32}${voffset -6}Up: ${upspeed ppp0}${font} ${alignr}${upspeedgraph ppp0 8,60 F57900 FCAF3E}
${goto 32}Total: ${totalup ppp0}
${voffset -2}${font VariShapes Solid:size=14}Q${font}${goto 32}${voffset -6}Down: ${downspeed ppp0}${font} ${alignr}${downspeedgraph ppp0 8,60 F57900 FCAF3E}
${goto 32}Total: ${totaldown ppp0}
${voffset -2}${font Poky:size=13}w${font}${goto 32}${voffset -4}Local IP: ${alignr}${addr ppp0}
${endif}${else}${voffset 4}${font PizzaDude Bullets:size=12}4${font}${goto 32}Network Unavailable${endif}${endif}
${voffset -1}WEATHER ${hr 2}

${voffset -10}${alignr 56}${font ConkyWeather:style=Bold:size=40}${execi 600 conkyForecast --location=UKXX0113 --datatype=WF}${font}
${voffset -50}${font Weather:size=40}y${font} ${voffset -38}${font Arial Black:size=26}${execi 600 conkyForecast --location=UKXX0113 --datatype=HT}${font}

${alignc 43}${execpi 600 conkyForecast --location=UKXX0113 --datatype=DW --startday=1 --shortweekday} ${alignc 8}${execpi 600 conkyForecast --location=UKXX0113 --datatype=DW --startday=2 --shortweekday} ${alignc -29}${execpi 600 conkyForecast --location=UKXX0113 --datatype=DW --startday=3 --shortweekday} ${alignc -64}${execpi 600 conkyForecast --location=UKXX0113 --datatype=DW --startday=4 --shortweekday}
${alignc 75}${font ConkyWeather:size=28}${execpi 600 conkyForecast --location=UKXX0113 --datatype=WF --startday=1 --endday=4 --spaces=1}${font}
${font DejaVu Sans:size=7}${alignc 48}${execpi 600 conkyForecast --location=UKXX0113 --datatype=HT --startday=1 --hideunits --centeredwidth=3}/${execpi 600 conkyForecast --location=UKXX0113 --datatype=LT --startday=1 --hideunits --centeredwidth=3} ${alignc -14}${execpi 600 conkyForecast --location=UKXX0113 --datatype=HT --startday=2 --hideunits --centeredwidth=3}/${execpi 600 conkyForecast --location=UKXX0113 --datatype=LT --startday=2 --hideunits --centeredwidth=3} ${alignc -40}${execpi 600 conkyForecast --location=UKXX0113 --datatype=HT --startday=3 --hideunits --centeredwidth=3}/${execpi 600 conkyForecast --location=UKXX0113 --datatype=LT --startday=3 --hideunits --centeredwidth=3} ${alignr 6}${execpi 600 conkyForecast --location=UKXX0113 --datatype=HT --startday=4 --hideunits --centeredwidth=3}/${execpi 600 conkyForecast --location=UKXX0113 --datatype=LT --startday=4 --hideunits --centeredwidth=3}${font}
${voffset 4}Location:${alignr}${execi 600 conkyForecast --location=UKXX0113 --datatype=CN}
Last Updated: ${alignr} ${execi 600 conkyForecast --location=UKXX0113 --hideunits --datatype=LU -m 0 }
Feels Like:${alignr}${execi 600 conkyForecast --location=UKXX0113 --datatype=LT}
Dew Point: ${alignr}${execi 600 conkyForecast --location=UKXX0113 --datatype=DP}
Current Condition:${alignr}${execi 600 conkyForecast --location=UKXX0113 --datatype=CC}
Chance of Precip: ${alignr}${execi 600 conkyForecast --location=UKXX0113 --startday=0 --datatype=PC}
Humidity: ${alignr}${execi 600 conkyForecast --location=UKXX0113 --datatype=HM}
Wind: ${alignr}${execi 600 conkyForecast --location=UKXX0113 --datatype=WS --imperial} - ${execi 600 conkyForecast --location=UKXX0113 --datatype=WD}
Pressure: ${alignr}${execi 600 conkyForecast --location=UKXX0113 --hideunits --datatype=BR}
Visibility: ${alignr}${execi 600 conkyForecast --location=UKXX0113 --datatype=VI --imperial}
Sunrise: ${alignr}${execi 600 conkyForecast --location=UKXX0113 --datatype=SR}
Sunset: ${alignr}${execi 600 conkyForecast --location=UKXX0113 --datatype=SS}
Moon Phase:${alignr 8}${execi 600 conkyForecast --location=UKXX0113 --datatype=MP} ${font MoonPhases:size=8}${execi 600 conkyForecast --location=UKXX0113 --datatype=MF}${font}
${hr 2}[/CODE]

[B].conkyForecast.config[/B][code]# config settings for conkyForecast.py
CACHE_FOLDERPATH = /tmp/
CONNECTION_TIMEOUT = 5
EXPIRY_MINUTES = 5
TIME_FORMAT = %H:%M
DATE_FORMAT = %d-%m-%y
LOCALE = Poole, United Kingdom
XOAP_PARTNER_ID = 
XOAP_LICENCE_KEY = UKXX0113
MAXIMUM_DAYS_FORECAST = 7
BASE_XOAP_URL = http://xoap.weather.com/weather/local/<LOCATION>?cc=*&dayf=10&link=xoap&prod=xoap&par=<XOAP_PARTNER_ID>&key=<XOAP_LICENCE_KEY>&unit=m[/code]

[B].draw_bg.lua[/B][code]--[[
Background by londonali1010 (2009)

This script draws a background to the Conky window. It covers the whole of the Conky window, but you can specify rounded corners, if you wish.

To call this script in Conky, use (assuming you have saved this script to ~/scripts/):
	lua_load ~/.scripts/draw_bg.lua
	lua_draw_hook_pre draw_bg

Changelog:
+ v1.0 -- Original release (07.10.2009)
]]

-- Change these settings to affect your background.
-- "corner_r" is the radius, in pixels, of the rounded corners. If you don't want rounded corners, use 0.

corner_r=20

-- Set the colour and transparency (alpha) of your background.

bg_colour=0x4D4D4D
bg_alpha=0.40

require 'cairo'
function rgb_to_r_g_b(colour,alpha)
	return ((colour / 0x10000) % 0x100) / 255., ((colour / 0x100) % 0x100) / 255., (colour % 0x100) / 255., alpha
end

function conky_draw_bg()
	if conky_window==nil then return end
	local w=conky_window.width
	local h=conky_window.height
	local cs=cairo_xlib_surface_create(conky_window.display, conky_window.drawable, conky_window.visual, w, h)
	cr=cairo_create(cs)
	
	cairo_move_to(cr,corner_r,0)
	cairo_line_to(cr,w-corner_r,0)
	cairo_curve_to(cr,w,0,w,0,w,corner_r)
	cairo_line_to(cr,w,h-corner_r)
	cairo_curve_to(cr,w,h,w,h,w-corner_r,h)
	cairo_line_to(cr,corner_r,h)
	cairo_curve_to(cr,0,h,0,h,0,h-corner_r)
	cairo_line_to(cr,0,corner_r)
	cairo_curve_to(cr,0,0,0,0,corner_r,0)
	cairo_close_path(cr)
	
	cairo_set_source_rgba(cr,rgb_to_r_g_b(bg_colour,bg_alpha))
	cairo_fill(cr)
end[/code]

[IMG]http://img341.imageshack.us/img341/9464/desk1001.png[/IMG][URL=http://img810.imageshack.us/img810/9441/desk1001.jpg][IMG]http://img810.imageshack.us/img810/9441/desk1001.th.jpg[/IMG][/URL]

Things you will need to install first.

[code]$sudo apt-get install python-statgrab ttf-droid ttf-liberation hddtemp curl lm-sensors conky-all
$sudo chmod u+s /usr/sbin/hddtemp
$sudo sensors-detect #answering Yes (default) to all questions, even that last one that defaults to No[/code]

Now that you have everything installed for conky to work we now need to get the extra icons, download [URL=http://gnome-look.org/content/show.php/CONKY-colors?content=92328]this[/URL] then extract the conky-colors.tar.gz and cd to the folder in the terminal, then run this.

[code]$make
$make install[/code]

To get the forecast info to work we need to install conkyforecast, open the terminal and paste this into it.

[code]$sudo add-apt-repository ppa:conkyhardcore/ppa
$sudo apt-get update && sudo apt-get install conkyforecast[/code]

To get the conkyforecast to show your local weather info we need to edit [B].conkyrc[/B] and [B].conkyForecast.config[/B]. Go to this [URL=http://xoap.weather.com/search/search?where=YOUR_CITY]URL[/URL] and replace [B]YOUR_CITY[/B] in the URL address bar with the name of your city. You will see a xml similar to the image below.

[IMG]http://img59.imageshack.us/img59/3518/selection001h.png[/IMG]

Now that you got the local ID for your area replace all the [B]UKXX0113[/B] with your local ID in the [B].conkyrc[/B] file posted. [I](you can use the "Find and Replace" feature in gedit [B]ctrl-h[/B])[/I]. Also replace [B]XOAP_LICENCE_KEY = UKXX0113[/B] with your local ID in the [B].conkyForecast.config[/B] and change [B]LOCALE = Poole, United Kingdom[/B] with the name of your area, exactly as shown on the page you got your local ID from.

Now that the [B].conkyrc[/B] and [B].conkyForecast.config[/B] have been edited with your area info copy all 3 files [i]([B].conkyrc[/B], [B].conkyForecast.config[/B] and [B].draw_bg.lua[/B])[/i] to your [B]home[/B] folder.
Ok let's test it. Press alt + F2 to open the run command in Gnome. Type [B]conky[/B] and it should appear on your desktop.
If it doesn't work retrace the steps / ask questions.
[/quote]


