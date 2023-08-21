# CSE 11 Fall 2022 PA5 - Image Editor
**Due date: Thursday, Nov 3 @11:59PM PDT**

There is an [FAQ post](https://piazza.com/class/l871flmwsqy8i/post/636) on Piazza. Please read that post first if you have any questions.

**Download the starter code from GitHub by clicking: Code $\rightarrow$ Download ZIP**

## Provided Files
- ImageEditor.java
- [Sample Images](https://github.com/CaoAssignments/cse11-fa22-pa5-image-editor-starter#Testing)

## Goal
Programming Assignment 5 is an introduction to two dimensional arrays in Java. You will need to use arrays and many of the other programming techniques you learned from previous PAs to complete the assignment.

## Overview
- Survey [Google Form, 1 point]
- Image Editor [Gradescope, 99 points]
    - Implementation [94 points]
    - Style [5 points]
- Extra Credit [Gradescope, 3 points]

## Survey [1 Point]
Please fill out [this survey](https://forms.gle/qdwvgYdPz6MNFLTL9) regarding your experience in this course.

## Image Editor [99 points]
In this programming assignment, we will implement a simple image editor that allows users to edit an image by rotating, lowering resolution, and also patching a smaller image onto it.
### Image
To represent our image, we will be utilizing a 2D int array, where each int represents the RGB color value of a pixel. The number of rows represents the height of the image, and the number of columns represents the width of the image. *You may safely assume each row contains the same number of columns (the image is rectangular). You may also assume the image height and width are both greater than 0.*
 
For example, to represent the following image:


![](https://i.imgur.com/RsUbL3Z.png)


we can use a 2D array to represent the RGB value in each pixel:
```
// hex values                         decimal values
[ [0xFF0000, 0x0000FF]                [ [16711680,     255]
  [0xFF7F00, 0x4B0082]                  [16744192, 4915330]
  [0xFFFF00, 0x9400D3]                  [16776960, 9699539]
  [0x00FF00, 0x000000] ]                [   65280,       0] ]
```
The 2D array on the right hand side is what the static variable `int[][] image` stores.

Taking one of the hex values as an example (`0x9400D3`), the RGB value can be broken down into three components where the range for each component is 0-255:
- red: 0x94 in hex is equal to 148 in decimal
- green: 0x00 in hex is equal to 0 in decimal
- blue: 0xD3 in hex is equal to 211 in decimal

The decimal value of `0x9400D3` (hexadecimal) is `9699539`.

*Note: the image above is 400x200 instead of 4x2. It is scaled up for better visibility and the actual size of the 2D array will contain 80000 RGB values instead of 8.*

Below are a few helpful links to help you with this assignment.
- [pixspy](https://pixspy.com/) can help you find the RGB value of each pixel in the image.
- [convert](https://www.rapidtables.com/convert/color/rgb-to-hex.html) RGB values to their corresponding hexadeciaml representation.

### Implementation [94 Points]
#### Static Variables
> <span style="color:red">**__Warning__**</span>
> 
> *Do NOT modify the variable names of provided static instance variables.*
> *Do NOT add additional imports.*
> *Do NOT add any additional static variables. (Unless they are private static final variables.)*
- `static int[][] image`: a 2D array representing RGB values of the image
    - Note: The image will be of size `M x N` where `M` denotes the number of rows and `N` is the number of columns. `M` and `N` can be of different lengths.

#### Methods
##### Provided Methods
There are helper methods are provided to you in the starter code. Please read through them carefully and do not modify any of the provided methods.
- `public static int[][] open(String pathname) throws IOException`
- `public static void load(String pathname) throws IOException`
- `public static void save(String pathname) throws IOException`
- `private static int unpackRedByte(int rgb)`
- `private static int unpackGreenByte(int rgb)`
- `private static int unpackBlueByte(int rgb)`
- `private static int packInt(int red, int green, int blue)`
- `public static void printImage()`


##### Public Methods
**For all methods below, leave the original image unchanged if the input values are invalid. If inputs are valid, then the resulting image should be stored in the static varaible `image`.**
- `public static void rotate(int degree)`
    - Rotate the image by `degree` degrees **clockwise**.
    - The input value is invalid if `degree` is less than 0 **or** if `degree` is not evenly divisible by 90.
    - To demonstrate rotating a 2D array 90° clockwise, here is a sample 3×3 2D array:
    
        ![matrix](https://i.imgur.com/y70v3qw.png)
        
        Please notice the difference between bold and underlined numbers in the below table. Also, the number **3** in the **calculation** column stands for the **number of rows in old 2D array**
        
        |  **value** | **old index** | **new index** |  **calculation** |
        |:-----------:|:------------:|:-------------:|:------------:|
        |      1      | (<ins>0</ins>, **0**) |   (**0**, 2)  | (0, 3-1-<ins>0</ins>) |
        |      2      | (<ins>0</ins>, **1**) |   (**1**, 2)  | (1, 3-1-<ins>0</ins>) |
        |      3      | (<ins>0</ins>, **2**) |   (**2**, 2)  | (2, 3-1-<ins>0</ins>) |
        |      4      | (<ins>1</ins>, **0**) |   (**0**, 1)  | (0, 3-1-<ins>1</ins>) |
        |      5      | (<ins>1</ins>, **1**) |   (**1**, 1)  | (1, 3-1-<ins>1</ins>) |
        |      6      | (<ins>1</ins>, **2**) |   (**2**, 1)  | (2, 3-1-<ins>1</ins>) |
        |      7      | (<ins>2</ins>, **0**) |   (**0**, 0)  | (0, 3-1-<ins>2</ins>) |
        |      8      | (<ins>2</ins>, **1**) |   (**1**, 0)  | (1, 3-1-<ins>2</ins>) |
        |      9      | (<ins>2</ins>, **2**) |   (**2**, 0)  | (2, 3-1-<ins>2</ins>) |
        
        As seen from the table, the general pattern for a 90°-clockwise rotate is:
        - An original pixel at coordinate `(i, j)` becomes a pixel at coordinate `(j, number_of_row_in_old - 1 - i)`.
        
        By using this formula, you can implement the clockwise 90° rotation. Hint: For clockwise rotation of more than 90°, try to think of how these higher-degree rotations are related to a clockwise 90° rotation.

- `public static void downSample(int heightScale, int widthScale)`
    - `heightScale`: down-scaling factor for the height of the image
    - `widthScale`: down-scaling factor for the width of the image
    -  To downsample your image (lower the resolution of the image):
        1. Scale the image smaller so that the pixels in the `heightScale` x `widthScale`-sized-grids become a single pixel.
        2. Fill in the scaled-down pixel with the average RGB value (i.e. average red, average green, and average blue) of original `heightScale` x `widthScale` rectangular grid.
    - Input values are invalid if `heightScale` **or** `widthScale` is
        - less than 1 or greater than the height or width respectively
        - NOT a multiplication factor of the height or width respectively
            - For example, if there are 8 rows (height is 8) and `heightScale` is 3, 8 is not evenly divisible by 3 so the value of `heightScale` is invalid. The original image should be unchanged.
    - Example: `downSample(2, 3)` on an image of size 4×6
    (These four colors are **just** for the visualization, but not the actual colors of the image)
    
    
        ![9pOY16m](https://user-images.githubusercontent.com/16179456/198747658-dd179b00-a4f0-4778-bbcb-f29e46ae549e.png)
        
        The resulting image will be of size 2x2.
        
        Consider the 6 pixels in the blue group (A). To find the down sampled pixel, we **cannot** simply take the average of 6 hex values from these pixels. Instead, to find the average, you should separate **R,G,B** values first. Take the average of the red, average of green, average of blue. Then combine these three values into a single RGB value. **Use our provided methods wisely.**
        
        <img width="446" alt="image" src="https://user-images.githubusercontent.com/16179456/198747716-c2ce0e4c-1a6f-4a37-afe4-1c0a92832a65.png">
        
        Here, to calculate the red **R** for result pixel, perform the calculation:
        1. convert hex to decimal: 
            - 0xdd = 221, 0xff = 255, 0xd3 = 211, etc
        3. find the average in decimal
            - $(221 + 255 + 211 + 222 + 192 + 244) / (2×3) = 224.1\dot{6}$
        5. round down 
            - get 224 in decimal, which is 0xe0 in hexadecimal

        The downsampled values of green **G** and blue **B** can be done similarly. After that, recombine the red, green, blue components into a RGB value for the resulting pixel.
        
        **Note:** If the values are not perfectly divisible, you should round the division results **down** to the closest integer (round down [defintion](https://www.collinsdictionary.com/us/dictionary/english/round-down)). In this **R** calculation, the decimal result is 224.1666..., so we should round down to 224 instead. The corresponding hexadecimal value is e0.
- `public static int patch(int startRow, int startColumn, int[][] 
patchImage, int transparentRed, int transparentGreen, int transparentBlue)`
    - Parameters:
        - `patchImage`: a 2D array representing RGB values of the patch image
        - `transparentRed`, `transparentGreen`, `transparentBlue`: an integer representing the corresponding red, green, and blue component for the transparent RGB color
    - Implementation:
        - Replace a certain part of `image` with `patchImage`, *starting* from position [`startRow`][`startColumn`].
        - If the pixel's RGB value from `patchImage` matches the transparent RGB color, we do not replace the pixel from `image` with the pixel from `patchImage`.
        - Returns the number of pixels that we have patched.
    - You may assume that `transparentRed`, `transparentGreen`, `transparentBlue` will be an integer between 0-255.
    - Parameters are invalid in the following scenarios:
        - `startRow` or `startColumn` is less than 0 or greater than the height or width respectively
        - The *ending* row or column is greater than the height or width respectively
    - Example: Suppose we have a 2x2 image and want to patch `patchImage` at (1, 1). The starting position is (1, 1) and the ending position is (2,2). Keep in mind to make sure both the *starting* and the *ending* position are within `image`, otherwise it is invalid.
![](https://i.imgur.com/H8RgrES.png)
    
### Testing
For this PA, 70% of the test cases can be found on Gradescope and the remaining will be hidden. You will not be able to see the hidden test cases until after grades are released. It is your responsibility to test your program comprehensively. 

Here's an example of how you can test your code.

```java     
public static void main(String[] args) throws IOException {
    load("ucsd.png");
    int[][] patchedImage = open("khosla.png");
    int patchedPixels = patch(50, 100, patchedImage, 255, 255, 255);
    System.out.println(patchedPixels);
    save("ucsd_patch_khosla.png");
}
```

Sample Images Provided:
- Big Image
    - ucsd.png
        - original image
    - khosla.png
        - patch image
    - ucsd_rotate.png
        - `degree` = 90
    - ucsd_downsample.png
        - `heightScale` = 4
        - `widthScale` = 2
    - ucsd_patch_khosla.png
        - we patch khosla onto the ucsd image
        - `startRow` = 50
        - `startColumn` = 100
        - `transparentRed` = 0xFF in hex and 255 in decimal
        - `transparentGreen` = 0xFF in hex and 255 in decimal
        - `transparentBlue` = 0xFF in hex and 255 in decimal
- Small Image
    - sample.png
        - original image
    - redblack.png
        - patch image
    - sample_rotate.png
        - `degree` = 90
    - sample_downsample.png
        - `heightScale` = 2
        - `widthScale` = 4
    - sample_patch_redblack.png
        - we patch redblack onto the sample image
        - `startRow` = 1
        - `startColumn` = 1
        - `transparentRed` = 0xFF in hex and 255 in decimal
        - `transparentGreen` = 0xFF in hex and 255 in decimal
        - `transparentBlue` = 0xFF in hex and 255 in decimal

Note: For the small images, you need to zoom in a lot to view it. Otherwise, you can print out the RGB values stored in the 2D array to check.


### Style [5 Points]
Coding style is an important part of ensuring readability and maintainability of your code. We will grade your code style in all submitted code files according to the style guidelines. Namely, there are a few things you must have in each file/class/method:

1. File header
2. Class header
3. Method header(s)
4. Inline comments
5. Proper indentation
6. Descriptive variable names
7. No magic numbers
8. Reasonably short methods (if you have implemented each method according to specification in this write-up, you’re fine). This is not enforced as strictly.
9. Lines shorter than 80 characters
10. Javadoc conventions (@param, @return tags, /** comments */, etc.)

A full style guide can be found [here](https://github.com/CaoAssignments/style-guide/blob/main/README.md) and a sample styled file can be found [here](https://github.com/CaoAssignments/style-guide/blob/main/SampleFile.java). If you need any clarifications, feel free to ask on Piazza.

## Extra Credit [3 Points]
And now it's your time to shine! Use **all** of these three methods to edit one image to earn **3** extra points for this programming assignment. At the minimum, you need to include a `main method` that calls these three methods. Please be creative and feel free to implement new methods. And then save this specific piece of code in a new file named `ImageEditorEC.java`, name the input images as `input<name>.png` where you replace `<name>` (i.e. `input-original.png`), and name the edited image as `output.png`. **Note: For the input images, you should should take your own images or draw them (do not take images from the internet)** Please submit all these three files on Gradescope and style will not be graded.

## Submission & Grading
Submit the following file to Gradescope by **Thursday, Nov 3 @ 11:59PM PDT**.

- `ImageEditor.java`
- `ImageEditorEC.java` (optional)
- `input.png` (optional)
- `output.png` (optional)

**Important:** Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. **Make sure your code compiles on Gradescope in order to receive partial credit. The file names need to be correct, otherwise no points will be given for the submission.** 

Some photos are due to the courtesy of UC San Diego Media Gallery.
