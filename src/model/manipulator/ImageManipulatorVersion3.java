package model.manipulator;

import model.enums.ColorChannel;
import model.exceptions.ImageNotFoundException;

/**
 * This interface offers additional manipulation operations on an image such as
 * filtering, color transformation and dithering.
 */
public interface ImageManipulatorVersion3 extends ImageManipulatorVersion2 {

  /**
   * Applies a filter to the image to produce a filtered image.
   *
   * @param originalImageName the name of the original image
   * @param kernel the kernel matrix to be used as filter
   * @param filteredImageName the name of the filtered image
   * @throws ImageNotFoundException if the requested image could not be found
   */
  void filter(String originalImageName, double[][] kernel, String filteredImageName) throws
          ImageNotFoundException;

  /**
   * Applies a color transformation to the image to produce a color transformed image.
   *
   * @param originalImageName the name of the original image
   * @param transform the color transform matrix to be used as transform
   * @param colorTransformedImageName the name of the color transformed image
   * @throws ImageNotFoundException if the requested image could not be found
   */
  void colorTransform(String originalImageName, double[][] transform,
                      String colorTransformedImageName) throws ImageNotFoundException;

  /**
   * Applies dithering to an image to produce a dithered image.
   *
   * @param originalImageName the name of the original image
   * @param channel the channel in the image to be used for dithering
   * @param ditheredImageName the name of the dithered image
   * @throws ImageNotFoundException if the requested image could not be found
   */
  void dither(String originalImageName, ColorChannel channel, String ditheredImageName)
          throws ImageNotFoundException;
}
