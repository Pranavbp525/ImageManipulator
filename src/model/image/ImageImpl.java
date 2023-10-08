package model.image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.enums.ColorChannel;
import utils.Component;
import utils.FlipDirection;

/**
 * A class to implement the functionality of the Image interface that offers a builder class to
 * instantiate an image object. This class represents an image and implements functionality for
 * various manipulations on the image such as brightening, flipping, converting an image into
 * greyscale and many more.
 */
public class ImageImpl implements Image {
  private final int height;
  private final int width;
  private final int minimumPixelValue;
  private final int maximumPixelValue;
  private final int numberOfChannels;
  private final int[][][] matrix;

  protected ImageImpl(int height, int width, int minimumPixelValue, int maximumPixelValue,
                    int numberOfChannels, int[][][] matrix) throws IllegalArgumentException {
    if (height == 0 || width == 0) {
      throw new IllegalArgumentException("height or width cannot be 0. Make sure "
              + "you have set the height and width correctly");
    }

    if (matrix == null) {
      throw new IllegalArgumentException("Matrix has not been initialized");
    }

    if (height != matrix.length || width != matrix[0].length
            || numberOfChannels != matrix[0][0].length) {
      throw new IllegalArgumentException("dimensions of the matrix do not match the height, width "
              + "and number of channels of the image.");
    }
    this.height = height;
    this.width = width;
    this.minimumPixelValue = minimumPixelValue;
    this.maximumPixelValue = maximumPixelValue;
    this.numberOfChannels = numberOfChannels;
    this.matrix = matrix;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getMinimumPixelValue() {
    return minimumPixelValue;
  }

  @Override
  public int getMaximumPixelValue() {
    return maximumPixelValue;
  }

  @Override
  public int getNumberOfChannels() {
    return numberOfChannels;
  }

  @Override
  public int getPixelValue(int row, int col, int channel) {
    return matrix[row][col][channel];
  }

  @Override
  public Image brighten(int amount) {
    int[][][] brightenedImageMatrix = new int[height][width][numberOfChannels];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        for (int channel = 0; channel < numberOfChannels; channel++) {
          brightenedImageMatrix[row][col][channel] = Math.max(minimumPixelValue,
                  Math.min(matrix[row][col][channel] + amount, maximumPixelValue));
        }
      }
    }
    return new ImageImplBuilder().setHeight(height).setWidth(width).setMatrix(brightenedImageMatrix)
            .build();
  }

  @Override
  public Image flip(FlipDirection flipDirection) {
    int[][][] flippedImageMatrix = new int[height][width][numberOfChannels];

    if (flipDirection.equals(FlipDirection.HORIZONTAL)) {
      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          System.arraycopy(matrix[row][width - 1 - col], 0, flippedImageMatrix[row][col],
                  0, numberOfChannels);
        }
      }
    } else if (flipDirection.equals(FlipDirection.VERTICAL)) {
      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          System.arraycopy(matrix[height - 1 - row][col], 0, flippedImageMatrix[row][col],
                  0, numberOfChannels);
        }
      }
    }
    return new ImageImplBuilder().setHeight(height).setWidth(width)
            .setMatrix(flippedImageMatrix).build();
  }

  private int[][][] getGreyscaleImageMatrixByChannel(int channelNumber) {
    int[][][] greyscaleImageMatrix = new int[height][width][numberOfChannels];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        for (int channel = 0; channel < numberOfChannels; channel++) {
          greyscaleImageMatrix[row][col][channel] = matrix[row][col][channelNumber];
        }
      }
    }
    return greyscaleImageMatrix;
  }

  private int[][][] getGreyscaleImageMatrixByBrightness(Component component) {
    int[][][] greyscaleImageMatrix = new int[height][width][numberOfChannels];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        for (int channel = 0; channel < numberOfChannels; channel++) {
          switch (component) {
            case VALUE:
              if (Arrays.stream(matrix[row][col]).max().isPresent()) {
                greyscaleImageMatrix[row][col][channel] = Arrays.stream(matrix[row][col]).max()
                        .getAsInt();
              }
              break;
            case LUMA:
              greyscaleImageMatrix[row][col][channel] = (int) Math.round(0.2126
                      * matrix[row][col][0] + 0.7152 * matrix[row][col][1]
                      + 0.0722 * matrix[row][col][2]);
              break;
            case INTENSITY:
              if (Arrays.stream(matrix[row][col]).average().isPresent()) {
                greyscaleImageMatrix[row][col][channel] = (int) Math.round(
                        Arrays.stream(matrix[row][col]).average().getAsDouble());
              }
              break;
            default:
              throw new IllegalArgumentException("Invalid brightness value");
          }
        }
      }
    }
    return greyscaleImageMatrix;
  }

  @Override
  public Image greyScale(Component component) {
    int[][][] greyscaleImageMatrix;

    switch (component) {
      case RED:
      case GREEN:
      case BLUE:
        greyscaleImageMatrix = getGreyscaleImageMatrixByChannel(
                ColorChannel.valueOf(String.valueOf(component)).getChannelNumber());
        break;
      case VALUE:
      case LUMA:
      case INTENSITY:
        greyscaleImageMatrix = getGreyscaleImageMatrixByBrightness(component);
        break;
      default:
        throw new IllegalArgumentException("Invalid component");
    }
    return new ImageImplBuilder().setHeight(height).setWidth(width)
            .setMatrix(greyscaleImageMatrix).build();
  }

  public List<Image> split() {
    return new ArrayList<>(Arrays.asList(greyScale(Component.RED), greyScale(Component.GREEN),
            greyScale(Component.BLUE)));
  }

  @Override
  public Image combine(List<Image> images) throws UnsupportedOperationException {
    images.add(0, this);
    int numberOfChannels = images.size();
    int[][][] combinedImageMatrix = new int[height][width][numberOfChannels];

    if (this.isSimilarTo(images)) {
      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          for (int channel = 0; channel < numberOfChannels; channel++) {
            combinedImageMatrix[row][col][channel] = images.get(channel).getPixelValue(row, col, 0);
          }
        }
      }
    } else {
      throw new UnsupportedOperationException("Cannot combine images of different types");
    }
    return new ImageImplBuilder().setHeight(height).setWidth(width)
            .setMatrix(combinedImageMatrix).build();
  }

  private boolean isSimilarTo(List<Image> images) {
    for (Image image : images) {
      if (!this.isSimilarTo(image)) {
        return false;
      }
    }
    return true;
  }

  private boolean isSimilarTo(Image image) {
    return this.equalsType(image) && this.equalsDimensions(image);
  }

  private boolean equalsDimensions(Image image) {
    return this.height == image.getHeight() && this.width == image.getWidth();
  }

  private boolean equalsType(Image image) {
    return this.numberOfChannels == image.getNumberOfChannels()
            && this.maximumPixelValue == image.getMaximumPixelValue()
            && this.minimumPixelValue == image.getMinimumPixelValue();
  }

  @Override
  public String toString() {
    return "Image{" +
            "height=" + height +
            ", width=" + width +
            ", minimumPixelValue=" + minimumPixelValue +
            ", maximumPixelValue=" + maximumPixelValue +
            ", numberOfChannels=" + numberOfChannels +
            ", matrix=" + Arrays.deepToString(matrix) +
            '}';
  }

  /**
   * An inner builder class used to instantiate the outer class' objects (ImageImpl objects). This
   * class provides the only way to instantiate ImageImpl objects. This class allows us to set
   * various fields of the ImageImpl class before instantiating the object.
   */
  public static class ImageImplBuilder extends AbstractImageBuilder implements ImageBuilder {

    /**
     * Initializes the builder object by calling the super class constructor.
     */
    public ImageImplBuilder() {
      super();
    }

    @Override
    public Image build() {
      return new ImageImpl(height, width, minimumPixelValue, maximumPixelValue, numberOfChannels,
              matrix);
    }
  }
}
