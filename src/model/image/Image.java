package model.image;

import java.util.List;

import utils.Component;
import utils.FlipDirection;

/**
 * This represents an image that supports some basic image manipulation features.
 */
public interface Image {

  /**
   * Brightens the image by increasing the pixel values across all channels by the given amount.
   * Final values lie between minimum and maximum pixel values, both inclusive.
   *
   * @param amount the amount by which the pixel values are to be changed. Positive amount implies
   *               brightening and negative amount implies darkening.
   * @return the brightened or darkened image
   */
  Image brighten(int amount);

  /**
   * Splits the image into its component images. For example, an RGB image is split into red, green
   * and blue greyscale images.
   *
   * @return the list of component images
   */
  List<Image> split();

  /**
   * Flips the image according to the given flip direction.
   *
   * @param flipDirection the direction along which the image must be flipped
   * @return the flipped image
   */
  Image flip(FlipDirection flipDirection);

  /**
   * Creates a greyscale image from the image based on the given component.
   *
   * @param component the attribute of the image based on which the greyscale image is to be created
   * @return the greyscale image
   */
  Image greyScale(Component component);

  /**
   * Creates an image composed of the component greyscale images in the given order.
   * The values in the first channel of the combined image are taken from the image on which this
   * method is called. The values of the subsequent channels are taken from the images passed as
   * arguments in the given order.
   *
   * @param images the ordered list of images which are to be combined with this image
   * @return the RGB color image
   */
  Image combine(List<Image> images);

  /**
   * Returns image height.
   *
   * @return image height
   */
  int getHeight();

  /**
   * Returns image width.
   *
   * @return image width
   */
  int getWidth();

  /**
   * Returns number of channels in image.
   *
   * @return number of channels in image
   */
  int getNumberOfChannels();

  /**
   * Returns the maximum allowed pixel value.
   *
   * @return maximum allowed pixel value
   */
  int getMaximumPixelValue();

  /**
   * Returns the minimum allowed pixel value.
   *
   * @return minimum allowed pixel value
   */
  int getMinimumPixelValue();

  /**
   * Returns the pixel value at the given location in the image.
   *
   * @param row row of the image
   * @param col column of the image
   * @param channel channel of the image
   * @return the pixel value at the given row, column and channel in the image
   */
  int getPixelValue(int row, int col, int channel);
}
