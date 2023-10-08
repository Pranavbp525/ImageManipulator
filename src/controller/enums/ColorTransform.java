package controller.enums;

import controller.utils.Messages;

/**
 * Represents the collection of matrices corresponding to various color transforms on the pixels of
 * an image.
 */
public enum ColorTransform {

  GREYSCALE(new double[][]{{0.2126, 0.7152, 0.0722},
    {0.2126, 0.7152, 0.0722},
    {0.2126, 0.7152, 0.0722}}),

  SEPIA(new double[][]{{0.393, 0.769, 0.189},
    {0.349, 0.686, 0.168},
    {0.272, 0.534, 0.131}});

  final double[][] transform;

  /**
   * Validates if the given matrix can be a color transform and initializes it.
   *
   * @param transform the color transform matrix
   * @throws IllegalArgumentException if the color transform matrix is invalid
   */
  ColorTransform(double[][] transform) throws IllegalArgumentException {
    if (transform.length != transform[0].length) {
      throw new IllegalArgumentException(Messages.INVALID_TRANSFORM_DIMENSIONS);
    }
    this.transform = transform;
  }

  /**
   * Returns the corresponding color transform matrix.
   *
   * @return the color transform matrix
   */
  public double[][] getTransform() {
    double[][] newMatrix = new double[transform.length][transform[0].length];
    for (int i = 0; i < transform.length; i++) {
      for (int j = 0; j < transform[0].length; j++) {
        newMatrix[i][j] = transform[i][j];
      }
    }
    return newMatrix;
  }
}
