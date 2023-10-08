package model.manipulator;

import model.exceptions.ImageNotFoundException;
import model.image.Image;
import model.image.ImageVersion3;
import model.image.ImageVersion3Impl;

/**
 * This class implements the ImageManipulatorVersion4 interface. This class wil perform the
 * mosaicking of an image.
 */
public class ImageManipulatorVersion4Impl extends ImageManipulatorVersion3Impl
        implements ImageManipulatorVersion4 {

  /**
   * Initializes the image manipulator by calling the super class constructor.
   */
  public ImageManipulatorVersion4Impl() {
    super();
  }

  @Override
  public void mosaic(String originalImageName, int seed, String mosaicImageName)
          throws ImageNotFoundException {
    Image image = imageDatabase.getImage(originalImageName);
    ImageVersion3 imageVersion3 = getImageVersion3Object(image);
    imageDatabase.putImage(mosaicImageName, imageVersion3.mosaic(seed));
  }

  private ImageVersion3 getImageVersion3Object(Image image) {
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

    return (ImageVersion3) new ImageVersion3Impl
            .ImageVersion3ImplBuilder()
            .setHeight(height)
            .setWidth(width)
            .setMatrix(imageMatrix)
            .setNumberOfChannels(channels)
            .build();
  }
}
