package controller.utils;

import java.awt.image.BufferedImage;

import model.exceptions.ImageNotFoundException;
import model.image.Image;

/**
 * The CommonImageUtil class provides methods to perform common image processing tasks such as
 * retrieving, resizing and getting histogram array of an image.
 */
public class CommonImageUtil {

  /**
   * Returns a BufferedImage generated from an Image.
   *
   * @param image the original image
   * @return the buffered image
   * @throws ImageNotFoundException if unable to get the image
   */
  public static BufferedImage getBufferedImageFromImage(Image image) throws ImageNotFoundException {
    return getBufferedImage(image);
  }

  private static BufferedImage getBufferedImage(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();
    int numChannels = image.getNumberOfChannels();
    BufferedImage bufferedImage = numChannels == 4
            ? new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB) :
            new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int pixel;
        int red = image.getPixelValue(row, col, 0);
        int green = image.getPixelValue(row, col, 1);
        int blue = image.getPixelValue(row, col, 2);
        if (numChannels == 4) {
          int alpha = image.getPixelValue(row, col, 3);
          pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
        } else {
          pixel = (red << 16) | (green << 8) | blue;
        }
        bufferedImage.setRGB(col, row, pixel);
      }
    }
    return bufferedImage;
  }

  /**
   * Returns a 2D integer array representing the histogram matrix of an image.
   * The matrix consists of
   * three rows, corresponding to the intensity values of red, green, and blue pixels, and 256
   * columns, representing the count of each intensity value from 0 to 255.
   *
   * @param image the Image object to obtain the histogram matrix from
   * @return a 2D integer array representing the histogram matrix of the image
   * @throws IllegalArgumentException if the input Image object is null
   */
  public static int[][] getImageHistogramMatrix(Image image) {
    int[][] histogramMatrix = new int[4][256];
    int height = image.getHeight();
    int width = image.getWidth();

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int red = image.getPixelValue(row, col, 0);
        histogramMatrix[0][red]++;
        int green = image.getPixelValue(row, col, 1);
        histogramMatrix[1][green]++;
        int blue = image.getPixelValue(row, col, 2);
        histogramMatrix[2][blue]++;
        int avg = (int) ((red + green + blue) / 3.0);
        histogramMatrix[3][avg]++;
      }
    }
    int minValue = Integer.MAX_VALUE;
    int maxValue = Integer.MIN_VALUE;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < histogramMatrix[i].length; j++) {
        if (histogramMatrix[i][j] < minValue) {
          minValue = histogramMatrix[i][j];
        }
        if (histogramMatrix[i][j] > maxValue) {
          maxValue = histogramMatrix[i][j];
        }
      }
      for (int k = 0; k < histogramMatrix[i].length; k++) {
        try {
          histogramMatrix[i][k] = (histogramMatrix[i][k] - minValue) * 100 / (maxValue - minValue);
        } catch (ArithmeticException e) {
          histogramMatrix[i][k] = Math.min(histogramMatrix[i][k], 100);
        }
      }
    }
    return histogramMatrix;
  }
}
