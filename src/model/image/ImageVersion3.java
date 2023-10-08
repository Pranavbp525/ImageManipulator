package model.image;

/**
 * This interface represents the new mosaic operation for an image. New interface has been
 * created so that we don't have to change the old code.
 */
public interface ImageVersion3 extends ImageVersion2 {

  /**
   * This method is created to perform a mosaicking operation over an image.
   *
   * @param seed the seed amount by which a mosaicking will be done.
   * @return the mosaicked image which will be generated.
   */
  Image mosaic(int seed);
}
