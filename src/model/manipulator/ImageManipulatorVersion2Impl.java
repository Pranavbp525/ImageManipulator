package model.manipulator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import model.exceptions.ImageNotFoundException;
import model.image.Image;
import utils.Component;
import utils.FlipDirection;

/**
 * This class implements the functionality defined by the ImageManipulator Interface. It acts as
 * the main point of contact with the controller from the model's side. It delegates the commands
 * from the controller to the ImageImpl class which implements them.
 */
public class ImageManipulatorVersion2Impl extends BaseImageManipulator
        implements ImageManipulatorVersion2 {

  /**
   * Initializes the image manipulator by calling the super class constructor.
   */
  public ImageManipulatorVersion2Impl() {
    super();
  }

  @Override
  public void brighten(String originalImageName, int amount, String brightenedImageName)
          throws ImageNotFoundException {
    Image image = imageDatabase.getImage(originalImageName);
    imageDatabase.putImage(brightenedImageName, image.brighten(amount));
  }

  @Override
  public void flip(String originalImageName, FlipDirection flipDirection, String flippedImageName)
          throws ImageNotFoundException {
    Image image = imageDatabase.getImage(originalImageName);
    imageDatabase.putImage(flippedImageName, image.flip(flipDirection));
  }

  @Override
  public void greyScale(String originalImageName, Component component, String greyscaleImageName)
          throws ImageNotFoundException {
    Image image = imageDatabase.getImage(originalImageName);
    imageDatabase.putImage(greyscaleImageName, image.greyScale(component));
  }

  @Override
  public void split(String originalImageName, List<String> splitImageNames)
          throws ImageNotFoundException {
    Image image = imageDatabase.getImage(originalImageName);
    List<Image> splitImages = image.split();

    IntStream.range(0, Math.min(splitImageNames.size(), splitImages.size()))
            .parallel()
            .forEach(i -> {
              String imageName = splitImageNames.get(i);
              Image splitImage = splitImages.get(i);
              imageDatabase.putImage(imageName, splitImage);
            });
  }

  @Override
  public void combine(List<String> imageNames, String combinedImageName)
          throws ImageNotFoundException {
    List<Image> images = new ArrayList<>();
    for (String imageName : imageNames) {
      Image image = imageDatabase.getImage(imageName);
      images.add(image);
    }
    imageDatabase.putImage(combinedImageName, images.get(0)
            .combine(images.subList(1, images.size())));
  }
}
