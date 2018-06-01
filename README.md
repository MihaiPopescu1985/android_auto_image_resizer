# android auto image resizer
This program automaticaly resizes images for android projects.

This program will not overwrite existing files and folders, nor will delete the image provided.
This program will copy the image provider in the drawable-mdpi folder and for the other folders, will create new images by changing the size of provided image.

How to use this program:

- download "ImageResizer.java" on your computer
- prepare the destination folder and the image that will be used

<img src="image resizer instructions\1.png"/>

- start command prompt e go inside the folder containing the "ImageResizer.java" file
- write the following command: javac ImageResizer.java

<img src="image resizer instructions\2.png"/>

- start the program with the command : java ImageResizer

<img src="image resizer instructions\4.png"/>

- first, the program will ask the path for the destination folder

<img src="image resizer instructions\5.png"/>

- then the program will ask the image path

<img src="image resizer instructions\6.png"/>

- and finaly, the program will ask the name for the new created images

<img src="image resizer instructions\7.png"/>

- the program will create 5 new folders (drawable-ldpi, drawable-mdpi, drawable-hdpi, drawable-xhdpi, drawable-xxhdpi and drawable-xxxhdpi) and in each folder will create a new image with different size.

<img src="image resizer instructions\8.png"/>
