package com.company;

import org.imgscalr.Scalr;

import javax.imageio.*;
import java.awt.*;

import java.awt.image.*;
import java.io.*;



public class Main {



    public static void main(String[] args) {

        ImageUtils utils = new ImageUtils();

        //File file = new File("C:/images/img.jpg");
        File input = new File("C:/images/img1.jpg");
        try {
            BufferedImage imageRGB = ImageIO.read(input);
            //System.out.print(imageRGB.getWidth());
            //System.out.print(" ");
            //System.out.print(imageRGB.getHeight());
            //System.out.print(" ");
            BufferedImage resizedRGB = utils.resize(imageRGB, 10);
            //System.out.print(resizedRGB.getWidth());
            //System.out.print(" ");
            //System.out.print(resizedRGB.getHeight());
            //System.out.print(" ");
            BufferedImage imageBW = utils.convertToBW(resizedRGB);
            int[] bwArr = utils.intoPixelArray(imageBW);

            //System.out.print(bwArr.length);

            for (int bwPixel : bwArr) {
                System.out.printf("%4d", bwPixel);
            }
        }
        catch (IOException e){
            System.out.print(e.getLocalizedMessage());
        }
    }

}

