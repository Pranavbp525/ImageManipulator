package model.manipulator;

import model.enums.ColorChannel;
import model.exceptions.ImageNotFoundException;
import model.image.Image;
import model.image.ImageVersion2;
import model.image.ImageVersion2Impl;

/**
 * This class offers support to additional manipulation operations on an image such as
 * filtering, color transformation and dithering.
 */
public class ImageManipulatorVersion3Impl extends ImageManipulatorVersion2Impl implements
        ImageManipulatorVersion3 {

  /**
   * Initializes the image manipulator by calling the super class constructor.
   */
  public ImageManipulatorVersion3Impl() {
    super();
  }

  @Override
  public void filter(String originalImageName, double[][] kernel, String filteredImageName)
          throws ImageNotFoundException {
    Image image = imageDatabase.getImage(originalImageName);
    ImageVersion2 imageVersion2 = getImageVersion2Object(image);
    imageDatabase.putImage(filteredImageName, imageVersion2.filter(kernel));
  }

  @Override
  public void colorTransform(String originalImageName, double[][] transform,
                             String colorTransformedImageName) throws ImageNotFoundException {
    Image image = imageDatabase.getImage(originalImageName);
    ImageVersion2 imageVersion2 = getImageVersion2Object(image);
    imageDatabase.putImage(colorTransformedImageName, imageVersion2.colorTransform(transform));
  }

  @Override
  public void dither(String originalImageName, ColorChannel channel, String ditheredImageName)
          throws ImageNotFoundException {
    Image image = imageDatabase.getImage(originalImageName);
    ImageVersion2 imageVersion2 = getImageVersion2Object(image);
    imageDatabase.putImage(ditheredImageName, imageVersion2.dither(channel));
  }


  protected ImageVersion2 getImageVersion2Object(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();
    int channels = image.getNumberOfChannels();
    int[][][] imageMatrix = new int[height][width][channels];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int c = 0; c < channels; c++) {
          imageMatrix[i][j][c] = image.getPixelValue(i,j,c);
        }
      }
    }

    return (ImageVersion2) new ImageVersion2Impl
            .ImageVersion2ImplBuilder()
            .setHeight(height)
            .setWidth(width)
            .setMatrix(imageMatrix)
            .setNumberOfChannels(channels)
            .build();
  }
}
