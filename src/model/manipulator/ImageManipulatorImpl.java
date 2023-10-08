package model.manipulator;

import java.util.List;

import model.image.Image;
import model.manipulator.ImageManipulator;
import utils.Component;
import utils.FlipDirection;

/**
 * This class implements the functionality defined by the ImageManipulator Interface. It acts as
 * the main point of contact with the controller from the model's side. It delegates the commands
 * from the controller to the ImageImpl class which implements them.
 */
public class ImageManipulatorImpl implements ImageManipulator {

  @Override
  public Image brighten(Image image, int amount) {
    return image.brighten(amount);
  }

  @Override
  public Image flip(Image image, FlipDirection flipDirection) {
    return image.flip(flipDirection);
  }

  @Override
  public Image greyScale(Image image, Component component) {
    return image.greyScale(component);
  }

  @Override
  public List<Image> split(Image image) {
    return image.split();
  }

  @Override
  public Image combine(List<Image> images) {
    return images.get(0).combine(images.subList(1, images.size()));
  }
}
