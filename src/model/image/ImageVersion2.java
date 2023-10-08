package model.image;

import model.enums.ColorChannel;

/**
 * Represents an image that supports additional image manipulation features such as filtering,
 * color transformation and dithering on an image.
 */
public interface ImageVersion2 extends Image {

  /**
   * Applies the given kernel to each pixel in the image, returning a new image with the filtered
   * result. The kernel is a 2D array of double values. The size of the kernel should be odd,
   * and the center element of the kernel should be positioned at the point of interest in the
   * image. The output pixel value is the weighted sum of the neighboring pixel values, where the
   * weights are the corresponding values in the kernel.
   *
   * @param kernel a 2D array of double values representing the filter kernel.
   * @return a new Image object with the filtered result.
   * @throws IllegalArgumentException if the kernel is invalid, or if the image has an unsupported
   *     number of channels
   */
  Image filter(double[][] kernel) throws IllegalArgumentException;

  /**
   * Applies the given color transform matrix to each pixel in the image, returning a new image with
   * the transformed result. The color transform matrix is a 2D array of double values. Each row of
   * the matrix represents the new color values for each of the input color channels
   * (in the order of Red, Green, Blue). The output pixel value is the result of
   * multiplying the input pixel values with the corresponding row of the color transform matrix.
   *
   * @param transform a 2D array of double values representing the color transform matrix.
   * @return a new Image object with the transformed result.
   * @throws IllegalArgumentException if the color transform matrix is invalid.
   */
  Image colorTransform(double[][] transform) throws IllegalArgumentException;

  /**
   * Applies the dithering algorithm to the given color component of the image,
   * returning a new image with the dithered result. The dithering process converts the original
   * image to a black and white indexed color scheme, using only two colors: black and white.
   * The algorithm distributes the error caused by the conversion to neighboring pixels, causing a
   * more natural-looking result.
   *
   * @param channel the color channel to dither (Red, Green, or Blue)
   * @return a new Image object with the dithered result
   */
  Image dither(ColorChannel channel);
}
