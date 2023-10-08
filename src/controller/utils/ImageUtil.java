package controller.utils;

import java.io.IOException;

import model.image.Image;

/**
 * This interface contains utility methods to load an image from a file and save an image to a file.
 */
public interface ImageUtil {

  /**
   * This method is used to save an image to a PPM file.
   *
   * @param image the image to be saved
   * @param imagePath the location where it is to be saved
   * @throws IOException if unable to save
   */
  void saveImageToFile(Image image, String imagePath) throws IOException;

  /**
   * This method is used to extract an Image from a Portable PixMap file.
   *
   * @param filename the location of the PPM file
   * @return the extracted image
   * @throws IOException if unable to get image
   */
  Image getImageFromFile(String filename) throws IOException;

}
