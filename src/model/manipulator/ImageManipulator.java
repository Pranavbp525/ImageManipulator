package model.manipulator;

import java.util.List;

import model.image.Image;
import utils.Component;
import utils.FlipDirection;

/**
 * This represents an image manipulator that supports some basic image manipulation features.
 */
public interface ImageManipulator {

  /**
   * Brightens the given image by increasing the pixel values across all channels by the given
   * amount. Final values lie between 0 and 255, both inclusive.
   *
   * @param image the image to be brightened or darkened
   * @param amount the amount by which the pixel values are to be changed. Positive amount implies
   *               brightening and negative amount implies darkening.
   * @return the brightened or darkened image
   */
  Image brighten(Image image, int amount);

  /**
   * Splits an image into red, green and blue components.
   *
   * @param image the image to be split
   * @return the list of the 3 component images
   */
  List<Image> split(Image image);

  /**
   * Flips the given image horizontally or vertically according to the given flip direction.
   *
   * @param image the image to be flipped
   * @param flipDirection the direction along which the image must be flipped
   * @return the flipped image
   */
  Image flip(Image image, FlipDirection flipDirection);

  /**
   * Creates a greyscale image from the given image based on the given Component.
   *
   * @param image the image to be converted into greyscale
   * @param component the attribute of the image based on which the greyscale image is to be created
   * @return the greyscale image
   */
  Image greyScale(Image image, Component component);

  /**
   * Creates an RGB image composed of the 3 given red, green and blue component greyscale images.
   *
   * @param images the list of images to be combined
   * @return the RGB color image
   */
  Image combine(List<Image> images);
}
