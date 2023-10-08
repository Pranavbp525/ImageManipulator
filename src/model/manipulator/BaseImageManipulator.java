package model.manipulator;

import model.storage.ImageDB;
import model.exceptions.ImageNotFoundException;
import model.storage.ImageStorage;
import model.image.Image;

/**
 * Contains the code common to all image manipulators, i.e. loading and saving an image to an image
 * storage.
 */
public abstract class BaseImageManipulator implements ImageStorage {

  protected final ImageStorage imageDatabase;

  /**
   * Initializes the storage object.
   */
  public BaseImageManipulator() {
    this.imageDatabase = new ImageDB();
  }

  @Override
  public Image getImage(String imageName) throws ImageNotFoundException {
    return imageDatabase.getImage(imageName);
  }

  @Override
  public void putImage(String imageName, Image image) {
    imageDatabase.putImage(imageName, image);
  }
}
