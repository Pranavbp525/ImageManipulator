package model.image;

/**
 * Represents the builder of an image. As images have many attributes to be specified while
 * constructing, having a dedicated builder removes the room for error while creating an image
 * and also improves readability.
 */
public interface ImageBuilder {

  /**
   * Sets the height of image object to a certain integer value.
   *
   * @param height height of the image
   * @return ImageBuilder object with height set to the specified value
   */
  ImageBuilder setHeight(int height);

  /**
   * Sets the width of image object to a certain integer value.
   *
   * @param width width of the image
   * @return ImageBuilder object with width set to the specified value
   */
  ImageBuilder setWidth(int width);

  /**
   * Sets the minimum allowed pixel value to the specified integer value.
   *
   * @param minimumPixelValue minimum allowed pixel value for the image
   * @return ImageBuilder object with minimum pixel value set to the specified amount
   */
  ImageBuilder setMinimumPixelValue(int minimumPixelValue);

  /**
   * Sets the maximum allowed pixel value to the specified integer value.
   *
   * @param maximumPixelValue maximum allowed pixel value for the image
   * @return ImageBuilder object with maximum pixel value set to the specified amount
   */
  ImageBuilder setMaximumPixelValue(int maximumPixelValue);

  /**
   * Sets the number of channels in the image to the specified integer value.
   *
   * @param numberOfChannels number of channels in the image
   * @return ImageBuilder object with number of channels value set to specified amount
   */
  ImageBuilder setNumberOfChannels(int numberOfChannels);

  /**
   * Sets the matrix used to represent the image as a matrix of pixel values.
   *
   * @param matrix the matrix used to represent the image
   * @return ImageBuilder object with matrix used to represent image set to the given matrix
   */
  ImageBuilder setMatrix(int[][][] matrix);

  /**
   * Creates an Image object with the values set as above.
   *
   * @return Image object
   */
  Image build();
}
