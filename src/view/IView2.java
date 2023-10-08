package view;

import controller.controllers.NewFeatures;

/**
 * This Interface acts as the View of the program that offers the user a way to interact with the
 * program and use mosaic operation.
 */
public interface IView2 extends IView {

  /**
   * Adds all the features offered by the program to be shown to the user, so he can use them
   * when required.
   *
   * @param features The features object encapsulating all the features offered by the program.
   */
  void addFeatures(NewFeatures features);

  /**
   * Provides a user a method to perform mosaic operation on given image.
   */
  void mosaic();
}
