package controller.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.image.Image;
import model.exceptions.ImageNotFoundException;
import model.image.ImageVersion2Impl;

/**
 * This class contains utility methods to load an image from a file and save an image to a file.
 * These operations are supported for multiple conventional image file formats.
 */
public class GenericImageUtil implements ImageUtil {

  @Override
  public void saveImageToFile(Image image, String imagePath) throws IOException {
    String extension = imagePath.split("[.]")[1];
    try {
      BufferedImage bufferedImage = CommonImageUtil.getBufferedImageFromImage(image);
      File output = new File(imagePath);
      ImageIO.write(bufferedImage, extension, output);
    } catch (IOException e) {
      throw new IOException(Messages.IMAGE_SAVE_FAILED + image + ", " + e.getMessage());
    } catch (ImageNotFoundException e) {
      throw new IOException(Messages.IMAGE_NOT_FOUND + image + ", " + e.getMessage());
    }
  }

  @Override
  public Image getImageFromFile(String filename) throws IOException {
    try {
      File imageFile = new File(filename);
      FileInputStream imageStream = new FileInputStream(imageFile);
      BufferedImage image = ImageIO.read(imageStream);

      int height = image.getHeight();
      int width = image.getWidth();
      int numChannels = image.getAlphaRaster() == null ? 3 : 4;
      int[][][] imageMatrix = new int[height][width][numChannels];

      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          int pixel = image.getRGB(col, row);
          imageMatrix[row][col][0] = (pixel >> 16) & 0xff;
          imageMatrix[row][col][1] = (pixel >> 8) & 0xff;
          imageMatrix[row][col][2] = pixel & 0xff;
          if (numChannels == 4) {
            imageMatrix[row][col][3] = (pixel >> 24) & 0xff;
          }
        }
      }

      return new ImageVersion2Impl.ImageVersion2ImplBuilder().setHeight(height).setWidth(width)
              .setMatrix(imageMatrix).setNumberOfChannels(numChannels).build();

    } catch (IOException e) {
      throw new IOException(Messages.IMAGE_READ_FAILED + e.getMessage());
    }
  }
}
