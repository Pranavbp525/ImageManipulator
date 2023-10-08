package model.image;

import controller.utils.Messages;
import model.enums.ColorChannel;

/**
 * This class contains the functionality required to implement new image manipulation features
 * such as filtering, color transformation and dithering on an image.
 */
public class ImageVersion2Impl extends ImageImpl implements ImageVersion2 {

  protected ImageVersion2Impl(int height, int width, int minimumPixelValue, int maximumPixelValue,
                              int numberOfChannels, int[][][] matrix) {
    super(height, width, minimumPixelValue, maximumPixelValue, numberOfChannels, matrix);
  }

  @Override
  public Image filter(double[][] kernel) throws IllegalArgumentException {
    if (kernel.length != kernel[0].length || kernel.length % 2 == 0) {
      throw new IllegalArgumentException("Invalid kernel dimensions");
    }
    int[][][] result = new int[this.getHeight()][this.getWidth()][this.getNumberOfChannels()];
    int kernelSize = kernel.length;
    for (int c = 0; c < this.getNumberOfChannels(); c++) {
      for (int i = 0; i < this.getHeight(); i++) {
        for (int j = 0; j < this.getWidth(); j++) {
          int sum = 0;

          for (int ki = 0; ki < kernelSize; ki++) {
            int ii = i + ki - (kernelSize / 2);

            for (int kj = 0; kj < kernelSize; kj++) {
              int jj = j + kj - (kernelSize / 2);

              if (ii >= 0 && ii < this.getHeight() && jj >= 0 && jj < this.getWidth()) {
                sum += kernel[ki][kj] * this.getPixelValue(ii, jj, c);
              }
            }
          }
          if (sum < 0) {
            sum = 0;
          }
          if (sum > 255) {
            sum = 255;
          }
          sum = Math.round(sum);

          result[i][j][c] = sum;
        }
      }
    }
    return new ImageVersion2ImplBuilder()
            .setHeight(this.getHeight())
            .setWidth(this.getWidth())
            .setNumberOfChannels(this.getNumberOfChannels())
            .setMatrix(result)
            .build();
  }

  @Override
  public Image colorTransform(double[][] transform) {
    int[][][] transformedImage =
            new int[this.getHeight()][this.getWidth()][this.getNumberOfChannels()];


    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        //for (int k = 0; k < this.getNumberOfChannels(); k++) {
        int red = this.getPixelValue(i, j, 0);
        int green = this.getPixelValue(i, j, 1);
        int blue = this.getPixelValue(i, j, 2);

        double newRed = transform[0][0] * red + transform[0][1] * green + transform[0][2] * blue;
        double newGreen = transform[1][0] * red + transform[1][1] * green + transform[1][2] * blue;
        double newBlue = transform[2][0] * red + transform[2][1] * green + transform[2][2] * blue;

        // clamp the resulting values in the range [0, 255]
        newRed = Math.min(Math.max(newRed, 0), 255);
        newGreen = Math.min(Math.max(newGreen, 0), 255);
        newBlue = Math.min(Math.max(newBlue, 0), 255);

        transformedImage[i][j][0] = (int) Math.round(newRed);
        transformedImage[i][j][1] = (int) Math.round(newGreen);
        transformedImage[i][j][2] = (int) Math.round(newBlue);

      }
    }
    return new ImageVersion2ImplBuilder()
            .setHeight(this.getHeight())
            .setWidth(this.getWidth())
            .setMatrix(transformedImage)
            .build();
  }

  @Override
  public Image dither(ColorChannel channel) {
    int height = this.getHeight();
    int width = this.getWidth();
    int[][] gray = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = this.getPixelValue(i, j, 0);
        int g = this.getPixelValue(i, j, 1);
        int b = this.getPixelValue(i, j, 2);
        switch (channel.getChannelName()) {
          case "red":
            gray[i][j] = r;
            break;
          case "green":
            gray[i][j] = g;
            break;
          case "blue":
            gray[i][j] = b;
            break;
          default:
            throw new IllegalArgumentException(Messages.INVALID_CHANNEL_VALUE);
        }
      }
    }

    int[][][] ditheredImage = new int[this.getHeight()][this.getWidth()]
            [this.getNumberOfChannels()];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int old_color = gray[i][j];
        int new_color = (old_color < 128) ? 0 : 255;
        int error = old_color - new_color;
        ditheredImage[i][j][0] = new_color;
        ditheredImage[i][j][1] = new_color;
        ditheredImage[i][j][2] = new_color;
        if (j < width - 1) {
          gray[i][j + 1] += (7 * error) / 16;
        }
        if (i < height - 1 && j > 0) {
          gray[i + 1][j - 1] += (3 * error) / 16;
        }
        if (i < height - 1) {
          gray[i + 1][j] += (5 * error) / 16;
        }
        if (i < height - 1 && j < width - 1) {
          gray[i + 1][j + 1] += (error) / 16;
        }
      }
    }

    return new ImageVersion2ImplBuilder()
            .setHeight(this.getHeight())
            .setWidth(this.getWidth())
            .setMatrix(ditheredImage)
            .build();
  }

  /**
   * This is a builder class to build the ImageVersion2Impl object.
   */
  public static class ImageVersion2ImplBuilder extends AbstractImageBuilder
          implements ImageBuilder {
    /**
     * Initializes the builder object by calling the super class constructor.
     */
    public ImageVersion2ImplBuilder() {
      super();
    }

    @Override
    public ImageVersion2 build() {
      return new ImageVersion2Impl(height, width, minimumPixelValue, maximumPixelValue,
              numberOfChannels, matrix);
    }
  }
}
