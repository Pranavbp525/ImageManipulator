package model.manipulator;

import java.util.List;

import model.exceptions.ImageNotFoundException;
import model.storage.ImageStorage;
import utils.Component;
import utils.FlipDirection;

/**
 * This represents an image manipulator that supports some basic image manipulation features.
 */
public interface ImageManipulatorVersion2 extends ImageStorage {

  /**
   * Brightens the given image by increasing the pixel values across all channels by the given
   * amount. Final values lie between 0 and 255, both inclusive.
   *
   * @param originalImageName the name of the image to be brightened or darkened
   * @param amount the amount by which the pixel values are to be changed. Positive amount implies
   *             brightening and negative amount implies darkening.
   * @param brightenedImageName the name of the brightened or darkened image
   * @throws ImageNotFoundException if the image does not exist
   */
  void brighten(String originalImageName, int amount, String brightenedImageName)
          throws ImageNotFoundException;

  /**
   * Splits an image into red, green and blue components.
   *
   * @param originalImageName the name of the image to be split
   * @param splitImageNames the names of the split images
   * @throws ImageNotFoundException if image is not found
   */
  void split(String originalImageName, List<String> splitImageNames) throws ImageNotFoundException;

  /**
   * Flips the given image horizontally or vertically according to the given flip direction.
   *
   * @param originalImageName the name of the image to be flipped
   * @param flipDirection the direction along which the image must be flipped
   * @param flippedImageName the name of flipped image
   * @throws ImageNotFoundException if the image does not exist
   */
  void flip(String originalImageName, FlipDirection flipDirection, String flippedImageName)
          throws ImageNotFoundException;

  /**
   * Creates a greyscale image from the given image based on the given Component.
   *
   * @param originalImageName the name of the image to be converted to greyscale
   * @param component the attribute of the image based on which the greyscale image is to be created
   * @param greyscaleImageName the name of the greyscale image
   * @throws ImageNotFoundException if the image does not exist
   */
  void greyScale(String originalImageName, Component component, String greyscaleImageName)
          throws ImageNotFoundException;

  /**
   * Creates an RGB image composed of the 3 given red, green and blue component greyscale images.
   *
   * @param imageNames the list of the names of images to be combined
   * @param combinedImageName the name of the combined image
   * @throws ImageNotFoundException if any of the images do not exist
   */
  void combine(List<String> imageNames, String combinedImageName) throws ImageNotFoundException;


}
