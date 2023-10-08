package controller.controllers;

import java.util.List;

/**
 * This interface defines the methods for implementing all the features offered by our program.
 */
public interface Features extends Controller {

  /**
   * Load feature allows to load an image into the memory in order to perform any image
   * manipulations on it.
   * @param imageFilename The path of the image to be loaded.
   * @param loadedImageName The name to the image to load.
   */
  void load(String imageFilename, String loadedImageName);

  /**
   * Brighten feature allows to brighten an  image by a certain brightenAmount.
   * @param brighteningAmount The amount by which to brighten the loaded image.
   * @param brightenedImageName The resultant brightened image's name.
   */
  void brighten(String brighteningAmount, String brightenedImageName);

  /**
   * Darken feature allows to darken an image by a certain darken amount.
   * @param darkeningAmount The amount by which to darken the loaded image.
   * @param darkenedImageName The resultant darkened image's name.
   */
  void darken(String darkeningAmount, String darkenedImageName);

  /**
   * VerticalFlip feature allows to flip an image vertically.
   * @param flippedImageName The resultant flipped image's name.
   */
  void verticalFlip(String flippedImageName);

  /**
   * HorizontalFlip feature allows to flip an image horizontally.
   * @param flippedImageName The resultant flipped image's name.
   */
  void horizontalFlip(String flippedImageName);

  /**
   * Greyscale feature allows to convert an image into a greyscale image based on one of the image's
   * several components.
   * @param componentName The component over which to apply greyscale operation to the image.
   * @param greyscaleImageName The resultant greyscale image's name.
   */
  void greyscale(String componentName, String greyscaleImageName);

  /**
   * Blur Feature allows to blur an image by filtering the image with a specific kernel.
   * @param blurredImageName The resultant blurred image's name.
   */
  void blur(String blurredImageName);

  /**
   * Sharpen feature allows to sharpen an image by filtering the image with a specific kernel.
   * @param sharpenedImageName The resultant sharpened image's name.
   */
  void sharpen(String sharpenedImageName);

  /**
   * Sepia Feature allows to apply sepia color transformation to an image.
   * @param sepiaImageName The resultant sepia image's name.
   */
  void sepia(String sepiaImageName);

  /**
   * Split Feature allows to split the given rgb image into 3 images each representative of its 3
   * channels - red channel, green channel, blue channel.
   * @param redImageName The name of the image created from the red channel.
   * @param greenImageName The name of the image created form the green channel.
   * @param blueImageName The name of the image created from the blue channel.
   */
  void split(String redImageName, String greenImageName, String blueImageName);

  /**
   * Combine Feature allows to combine 3 images into a single image. The red channel of first image,
   * the green channel of second image, and the blue channel of the third image make up the red,
   * green, and blue channels of the combined image respectively.
   * @param selectedImageNames list containing image names of three images to combine.
   * @param combinedImageName the resultant combined image's name.
   */
  void combine(List<String> selectedImageNames, String combinedImageName);

  /**
   * Dither feature applies a dither operation to an image to make it look like the output of a
   * dot matrix printer.
   * @param channelName The channel using which to perform the dither operation.
   * @param ditheredImageName The resultant dithered image's name.
   */
  void dither(String channelName, String ditheredImageName);

  /**
   * Save feature allows to save any image in memory permanently to the disk.
   * @param imageFilename Path of the image to be saved.
   */
  void save(String imageFilename);

  /**
   * This feature enables to make the selected image visible to the user.
   * @param imageName The name of the image selected.
   */
  void showSelectedImage(String imageName);
}
