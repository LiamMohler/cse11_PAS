/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file (ImageEditor.java) will allow users to edit images.
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class will simulate image editing softwares, as it allows users to
 * downscale images, patch photos, or rotate them in increments of 90 degrees.
 */

public class ImageEditor {
    /* Constants (Magic numbers) */
    private static final String PNG_FORMAT = "png";
    private static final String NON_RGB_WARNING =
            "Warning: we do not support the image you provided. \n" +
            "Please change another image and try again.";
    private static final String RGB_TEMPLATE = "(%3d, %3d, %3d) ";
    private static final int BLUE_BYTE_SHIFT = 0;
    private static final int GREEN_BYTE_SHIFT = 8;
    private static final int RED_BYTE_SHIFT = 16;
    private static final int ALPHA_BYTE_SHIFT = 24;
    private static final int BLUE_BYTE_MASK = 0xff << BLUE_BYTE_SHIFT;
    private static final int GREEN_BYTE_MASK = 0xff << GREEN_BYTE_SHIFT;
    private static final int RED_BYTE_MASK = 0xff << RED_BYTE_SHIFT;
    private static final int ALPHA_BYTE_MASK = ~(0xff << ALPHA_BYTE_SHIFT);
    private static final int NINEZERO = 90;
    private static final int THREESIXZERO = 360;

    /* Static variables - DO NOT add any additional static variables */
    static int[][] image;

    /**
     * Open an image from disk and return a 2D array of its pixels.
     * Use 'load' if you need to load the image into 'image' 2D array instead
     * of returning the array.
     *
     * @param pathname path and name to the file, e.g. "input.png",
     *                 "D:\\Folder\\ucsd.png" (for Windows), or
     *                 "/User/username/Desktop/my_photo.png" (for Linux/macOS).
     *                 Do NOT use "~/Desktop/xxx.png" (not supported in Java).
     * @return 2D array storing the rgb value of each pixel in the image
     * @throws IOException when file cannot be found or read
     */
    public static int[][] open(String pathname) throws IOException {
        BufferedImage data = ImageIO.read(new File(pathname));
        if (data.getType() != BufferedImage.TYPE_3BYTE_BGR &&
                data.getType() != BufferedImage.TYPE_4BYTE_ABGR) {
            System.err.println(NON_RGB_WARNING);
        }
        int[][] array = new int[data.getHeight()][data.getWidth()];

        for (int row = 0; row < data.getHeight(); row++) {
            for (int column = 0; column < data.getWidth(); column++) {
                /* Images are stored by column major
                   i.e. (2, 10) is the pixel on the column 2 and row 10
                   However, in class, arrays are in row major
                   i.e. [2][10] is the 11th element on the 2nd row
                   So we reverse the order of i and j when we load the image.
                 */
                array[row][column] = data.getRGB(column, row) &
                        ALPHA_BYTE_MASK;
            }
        }

        return array;
    }

    /**
     * Load an image from disk to the 'image' 2D array.
     *
     * @param pathname path and name to the file, see open for examples.
     * @throws IOException when file cannot be found or read
     */
    public static void load(String pathname) throws IOException {
        image = open(pathname);
    }

    /**
     * Save the 2D image array to a PNG file on the disk.
     *
     * @param pathname path and name for the file. Should be different from
     *                 the input file. See load for examples.
     * @throws IOException when file cannot be found or written
     */
    public static void save(String pathname) throws IOException {
        BufferedImage data = new BufferedImage(
                image[0].length, image.length, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < data.getHeight(); row++) {
            for (int column = 0; column < data.getWidth(); column++) {
                // reverse it back when we write the image
                data.setRGB(column, row, image[row][column]);
            }
        }
        ImageIO.write(data, PNG_FORMAT, new File(pathname));
    }

    /**
     * Unpack red byte from a packed RGB int
     *
     * @param rgb RGB packed int
     * @return red value in that packed pixel, 0 <= red <= 255
     */
    private static int unpackRedByte(int rgb) {
        return (rgb & RED_BYTE_MASK) >> RED_BYTE_SHIFT;
    }

    /**
     * Unpack green byte from a packed RGB int
     *
     * @param rgb RGB packed int
     * @return green value in that packed pixel, 0 <= green <= 255
     */
    private static int unpackGreenByte(int rgb) {
        return (rgb & GREEN_BYTE_MASK) >> GREEN_BYTE_SHIFT;
    }

    /**
     * Unpack blue byte from a packed RGB int
     *
     * @param rgb RGB packed int
     * @return blue value in that packed pixel, 0 <= blue <= 255
     */
    private static int unpackBlueByte(int rgb) {
        return (rgb & BLUE_BYTE_MASK) >> BLUE_BYTE_SHIFT;
    }

    /**
     * Pack RGB bytes back to an int in the format of
     * [byte0: unused][byte1: red][byte2: green][byte3: blue]
     *
     * @param red   red byte, must satisfy 0 <= red <= 255
     * @param green green byte, must satisfy 0 <= green <= 255
     * @param blue  blue byte, must satisfy 0 <= blue <= 255
     * @return packed int to represent a pixel
     */
    private static int packInt(int red, int green, int blue) {
        return (red << RED_BYTE_SHIFT)
                + (green << GREEN_BYTE_SHIFT)
                + (blue << BLUE_BYTE_SHIFT);
    }

    /**
     * Print the current image 2D array in (red, green, blue) format.
     * Each line represents a row in the image.
     */
    public static void printImage() {
        for (int[] ints : image) {
            for (int pixel : ints) {
                System.out.printf(
                        RGB_TEMPLATE,
                        unpackRedByte(pixel),
                        unpackGreenByte(pixel),
                        unpackBlueByte(pixel));
            }
            System.out.println();
        }
    }

    /**
     * This method rotate the image by the inputted degree ammount ( if 90 
     * degree increments).
     * 
     * @param degree - the ammount of degrees to rotate.
     */


    public static void rotate(int degree){
        // in > than 360 make less than
        degree = degree % THREESIXZERO;
        //if not 90 degrees then return
        if(degree % NINEZERO != 0)
            return;

        if(image == null || image.length == 0)
            return;
        //while its 90 degrees or more then we want to rotate
        while(degree > 0){
            int[][] tempArr = new int[image[0].length][image.length];
            for (int row = 0; row < image.length; row++) {
                for (int column = 0; column < image[row].length; column++) {
                    tempArr[column][(image.length-1)-row] = image[row][column];
                }
            }
            degree-=NINEZERO;
            image = tempArr;
        }
    }


    /**
     * This method will downscale the images resolution to the inputted 
     * heightScale and widthScale.
     * 
     * @param heightScale - the height to scale off of
     * * @param heightScale - the width to scale off of
     */

    public static void downSample(int heightScale, int widthScale){
        if(image == null || image.length == 0)
            return;
        if(heightScale < 1 || widthScale < 1 || image.length % heightScale
            != 0 || image[0].length % widthScale != 0)
            return;
        int newHeight = image.length / heightScale;
        int newWidth = image[0].length / widthScale;
        int divideBy = widthScale*heightScale;
        int[][] downSampleArr = new int[newHeight][newWidth];
        
        //loop throguh the original image
        for(int i = 0;i<image.length;i+=heightScale){
            for(int j = 0; j<image[0].length;j+=widthScale){
                int redSum = 0;
                int greenSum = 0;
                int blueSum = 0;
                //loop throguh each box/rectangle created by the heightScale
                //and widthScale
                for(int row = i; row<i + heightScale;row++){
                    for(int col = j; col<j + widthScale;col++){
                        //add together
                        redSum+=unpackRedByte(image[row][col]);
                        greenSum+=unpackGreenByte(image[row][col]);
                        blueSum+=unpackBlueByte(image[row][col]);
                    }
                }
                //pack each sum into new hexa stored in a dif arr
                downSampleArr[i/heightScale][j/widthScale] = packInt(
                            redSum/divideBy, 
                            greenSum/divideBy, 
                            blueSum/divideBy);
            }
        }        
        //img = temp
        image = downSampleArr;
    }


    /**
     * This method will patch the image from the inputted startRow and Col
     * with the inputted patchImage, using the transRed, transGreen, transBlue
     * variables.
     * 
     * @param startRow - the starting row
     * @param startCol - the starting col
     * @param patchImage - the image to patch
     * @param transparentRed - the transparent red value
     * @param transparentGreen - the transparent green value
     * @param transparentBlue - the transparent blue value
     * 
     * @return - the number of pixels patched.
     */


    public static int patch(int startRow, int startColumn, int[][] patchImage, 
            int transparentRed, int transparentGreen, int transparentBlue){
        //if null then return
        if(image == null || image.length == 0)
            return 0;
        //if not in bounds the return
        if(startRow > image.length || startRow < 0 || startColumn < 0 
            || startColumn > image[0].length || 
            startRow + patchImage.length > image.length
            || startColumn + patchImage[0].length > image[0].length)
            return 0;

        int numPatched = 0;
        int packedTransparent = packInt(transparentRed, transparentGreen, 
            transparentBlue); 
        //loop through and change image to the patched image
        //add to numpatched
        for(int i = 0; i < patchImage.length; i++){
            for(int j = 0; j < patchImage[i].length; j++){
                if(patchImage[i][j] != packedTransparent){
                    image[i+startRow][j+startColumn] = patchImage[i][j];
                    numPatched ++;
                }
            }
        }
        //ret
        return numPatched;
    }
}