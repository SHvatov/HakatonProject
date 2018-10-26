package com.company;

import org.imgscalr.Scalr;
import java.awt.image.*;

//Commented 'System.out.print's were used for debugging
//The 'try' blocks were commented due to the unsuccessfulness of the attempt to make them save the output image to the disk

public class ImageUtils {

    static  BufferedImage convertToBW(BufferedImage imageRGB) {

        int imgWidth = imageRGB.getWidth();
        int imgHeight = imageRGB.getHeight();

        int[] pixels = imageRGB.getRGB(0, 0, imgWidth, imgHeight, null, 0, imgWidth);
        int[] bwPixels = new int[pixels.length];

        for (int i = 0, n = pixels.length; i < n; i++) {  //pixel by pixel RGB components are calculated
            int red = (pixels[i] & 0x00ff0000) / 0x00010000;
            //System.out.printf("%4d re", red);
            int green = (pixels[i] & 0x0000ff00) / 0x00000100;
            //System.out.printf("%4d gr", green);
            int blue = (pixels[i] & 0x000000ff);
            //System.out.printf("%4d bl", blue);
            int bw = (red + green + blue) / 3;
            //System.out.printf("%4d bw", bw);
            bwPixels[i] = 0xff000000 + bw * 0x00010000 + bw * 0x00000100 + bw; //and new Black&White pixels are constructed
        }

        BufferedImage imageBW = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_4BYTE_ABGR);
        imageBW.setRGB(0, 0, imgWidth, imgHeight, bwPixels, 0, imgWidth);
/*
        try {
            File output = new File("C:\\lib\\saved.jpg");
            ImageIO.write(imageBW, "JPG", output);
        }
        catch (IOException e){
            System.out.print(e.getLocalizedMessage());
        }
*/
        return imageBW;
    }

    //Uses Scalr library
    static BufferedImage resize(BufferedImage unresized, int width){
        BufferedImage resized = Scalr.resize(unresized, width);

/*
        File output = new File("C:\\resized.jpg");

        try {
            ImageIO.write(resized, "JPG", output);
        }
        catch (IOException e){
            System.out.print(e.getLocalizedMessage());
        }
*/
        return resized;
    }

    static int[] intoPixelArray(BufferedImage imgBW){

        int imgWidth = imgBW.getWidth();
        int imgHeight = imgBW.getHeight();

        int[] pixels = imgBW.getRGB(0, 0, imgWidth, imgHeight, null, 0, imgWidth);
        int[] bwArray = new int[pixels.length];

        //Cause all the RGB components ore the same, we take the last one just because it's the easiest to
        for (int i = 0, n = pixels.length; i < n; i++) {
            bwArray[i] = (pixels[i] & 0x000000ff);
        }

        return bwArray;
    }

}
