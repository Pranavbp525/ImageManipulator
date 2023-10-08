package model.manipulator;

import model.exceptions.ImageNotFoundException;

/**
 * This interface represents the new Mosaic operation. We have implemented the new interface as
 * we did not want to change the previous model.
 */
public interface ImageManipulatorVersion4 extends ImageManipulatorVersion3 {
  /**
   * Perform the mosaicking of an image by given seed value.
   *
   * @param originalImageName the name of original image.
   * @param seed              Value of seed for mosaicking of an image.
   * @param mosaicImageName   the name of mosaicked image.
   * @throws ImageNotFoundException if any of the images do not exist
   */
  void mosaic(String originalImageName, int seed, String mosaicImageName)
          throws ImageNotFoundException;
}
