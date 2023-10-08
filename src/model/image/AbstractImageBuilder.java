package model.image;

/**
 * This is a builder class for an image.
 */
public abstract class AbstractImageBuilder implements ImageBuilder {

  protected int height;
  protected int width;
  protected int minimumPixelValue;
  protected int maximumPixelValue;
  protected int numberOfChannels;
  protected int[][][] matrix;

  /**
   * The constructor initializes these fields to default values as we are only dealing with ASCII
   * RGB images, but they can be changed by using setters.
   */
  protected AbstractImageBuilder() {
    minimumPixelValue = 0;
    maximumPixelValue = 255;
    numberOfChannels = 3;
  }

  /**
   * Sets the height of image object to a certain integer value.
   *
   * @param height height of the image
   * @return ImageBuilder object with height set to the specified value
   */
  public ImageBuilder setHeight(int height) {
    this.height = height;
    return this;
  }

  /**
   * Sets the width of image object to a certain integer value.
   *
   * @param width width of the image
   * @return ImageBuilder object with width set to the specified value
   */
  public ImageBuilder setWidth(int width) {
    this.width = width;
    return this;
  }

  /**
   * Sets the minimum allowed pixel value to the specified integer value.
   *
   * @param minimumPixelValue minimum allowed pixel value for the image
   * @return ImageBuilder object with minimum pixel value set to the specified amount
   */
  public ImageBuilder setMinimumPixelValue(int minimumPixelValue) {
    this.minimumPixelValue = minimumPixelValue;
    return this;
  }

  /**
   * Sets the maximum allowed pixel value to the specified integer value.
   *
   * @param maximumPixelValue maximum allowed pixel value for the image
   * @return ImageBuilder object with maximum pixel value set to the specified amount
   */
  public ImageBuilder setMaximumPixelValue(int maximumPixelValue) {
    this.maximumPixelValue = maximumPixelValue;
    return this;
  }

  /**
   * Sets the number of channels in the image to the specified integer value.
   *
   * @param numberOfChannels number of channels in the image
   * @return ImageBuilder object with number of channels value set to specified amount
   */
  public ImageBuilder setNumberOfChannels(int numberOfChannels) {
    this.numberOfChannels = numberOfChannels;
    return this;
  }

  /**
   * Sets the matrix used to represent the image as a matrix of pixel values.
   *
   * @param matrix the matrix used to represent the image
   * @return ImageBuilder object with matrix used to represent image set to the given matrix
   */
  public ImageBuilder setMatrix(int[][][] matrix) {
    this.matrix = matrix;
    return this;
  }
}
