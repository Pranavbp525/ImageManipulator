package controller.controllers;

/**
 * This interface represents the view controller. It has mosaic method which will help the
 * controller to handle the mosaic operation request made by GUI.
 */
public interface NewFeatures extends Features {
  /**
   * Perform the mosaicking of an image by given seed value.
   *
   * @param seed            Value of seed for mosaicking of an image.
   * @param mosaicImageName the name of mosaicked image.
   */
  void mosaic(String seed, String mosaicImageName);
}
