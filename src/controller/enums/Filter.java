package controller.enums;

import controller.utils.Messages;

/**
 * Represents the collection of matrices corresponding to various kernels that could be passed as
 * filters to an image.
 */
public enum Filter {

  BLUR(new double[][]{{1.0 / 16, 1.0 / 8, 1.0 / 16},
    {1.0 / 8, 1.0 / 4, 1.0 / 8},
    {1.0 / 16, 1.0 / 8, 1.0 / 16}}),

  SHARPEN(new double[][]{{-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
    {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
    {-1.0 / 8, 1.0 / 4, 1.0, 1.0 / 4, -1.0 / 8},
    {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
    {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
  });

  final double[][] kernel;

  /**
   * Validates if the given matrix can be a kernel and initializes it.
   *
   * @param kernel the kernel matrix
   * @throws IllegalArgumentException if the kernel matrix is invalid
   */
  Filter(double[][] kernel) throws IllegalArgumentException {
    if (kernel.length != kernel[0].length || kernel.length % 2 == 0) {
      throw new IllegalArgumentException(Messages.INVALID_KERNEL_DIMENSIONS);
    }
    this.kernel = kernel;
  }

  /**
   * Returns the corresponding kernel matrix.
   *
   * @return the kernel matrix
   */
  public double[][] getKernel() {
    double[][] newMatrix = new double[kernel.length][kernel[0].length];
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[0].length; j++) {
        newMatrix[i][j] = kernel[i][j];
      }
    }
    return newMatrix;
  }
}
