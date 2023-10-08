package model.storage;

import model.exceptions.ImageNotFoundException;
import model.image.Image;

/**
 * Represents a storage for images currently loaded and in use by the current session.
 */
public interface ImageStorage {

  /**
   * Searches the collection of loaded images and returns the requested image, if found.
   *
   * @param imageName the name of the requested image
   * @return the requested image
   * @throws ImageNotFoundException if image is not found
   */
  Image getImage(String imageName) throws ImageNotFoundException;

  /**
   * Stores the given image in the collection of loaded images.
   *
   * @param image the image to be stored
   * @param imageName name of the image
   */
  void putImage(String imageName, Image image);
}
