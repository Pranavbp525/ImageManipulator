package view;

import java.awt.image.BufferedImage;

import controller.controllers.Features;

/**
 * This Interface acts as the View of the program that offers the user a way to interact with the
 * program and use any feature offered by the program.
 */
public interface IView {

  /**
   * Shows the specified Image to the user.
   *
   * @param image The BufferedImage image object that represents the image to be shown.
   */
  void showImage(BufferedImage image);

  /**
   * Shows the histograms as line charts for all the three color channels of the image in addition
   * to the image's value component. A histogram represents the frequencies of each pixel value in
   * the image.
   *
   * @param histogramMatrix The 2d array that contains the frequencies for each pixel for the 3
   *                        channels in the image, and the intensity.
   */
  void showImageHistogram(int[][] histogramMatrix);

  /**
   * Adds all the features offered by the program to be shown to the user, so he can use them
   * when required.
   *
   * @param features The features object encapsulating all the features offered by the program.
   */
  void addFeatures(Features features);

  /**
   * Provides a way to the user to load an image into the memory, facilitates in selecting the
   * image to be loaded.
   */
  void load();

  /**
   * Provides the user an interface to access the brightened feature of the program to
   * brighten the
   * image that is loaded and active.
   */
  void brighten();

  /**
   * Provides the user an interface to access the darken feature of the program to darken the image
   * that is loaded and active.
   */
  void darken();

  /**
   * Provides the user an interface to access the vertical flip feature of the program to vertically
   * flip an image that is loaded and active.
   */
  void verticalFlip();

  /**
   * Provides the user an interface to access the horizontal flip feature of the program to
   * horizontally flip an image that is loaded and active.
   */
  void horizontalFlip();

  /**
   * Provides the user an interface to access the greyscale feature of the program to greyscale the
   * image that is loaded and active.
   */
  void greyscale();

  /**
   * Provides the user an interface to access the blur feature of the program to blur the image
   * that is loaded and active.
   */
  void blur();

  /**
   * Provides the user an interface to access the sharpen feature of the program to sharpen the
   * image that is loaded and active.
   */
  void sharpen();

  /**
   * Provides the user an interface to access the sepia feature of the program to sepia the image
   * that is loaded and active.
   */
  void sepia();

  /**
   * Provides the user an interface to access the split feature of the program to split the loaded
   * image into 3 images based on its r,g,b components.
   */
  void split();

  /**
   * Provides the user an interface to access the combine feature of the program to combine three
   * images into a single image.
   */
  void combine();

  /**
   * Provides the user an interface to access the dither feature of the program to dither an image
   * based in the color channel provided.
   *
   * @param colorChannelName The color channel by which user dithers the image.
   */
  void dither(String colorChannelName);

  /**
   * Provides the user an interface to access the save feature of the program to save any of the
   * images in memory to the disk.
   */
  void save();

  /**
   * Allows to add an image name to the loaded image names list.
   *
   * @param imageName the image name to be added to list of loaded image names.
   */
  void addLoadedImageName(String imageName);

  /**
   * Allows to select an image name from list of loaded image names and show it.
   *
   * @param imageName the image name to select.
   */
  void getSelectedImage(String imageName);


}
