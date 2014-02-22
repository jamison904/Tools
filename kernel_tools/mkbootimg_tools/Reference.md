mkbootimg_tools
===============

First thing you need to do is add your kernel boot.img into the mkbootimg_tools-master folder.


Next your going to need to unpack the boot.img, to unpack kernel boot.img type the following command below in terminal. (To make things easier, Just copy and paste)...


./mkboot boot.img ./unpacked


Once unpacked, go into the "unpacked" folder and delete the old zImage and then add your newely zImage that has been created after a successful compile with all your kernel github changes. (You can go into the "ramdisk" folder and do any ramdisk changes that you want.)


Once done with the changes your going to need to repack the boot.img, to repack kernel with new boot.img type the following command below in terminal. (Just copy and paste again)...


./mkboot ./unpacked newboot.img


In the mkbootimg_tools-master folder you will then see a new file called "newboot.img". (That is your newely created kernel boot.img with all your github changes, zImage, and ramdisk changes.)

Now Rename newboot.img to boot.img, then add it to your kernel flashable.zip... Done! :)
