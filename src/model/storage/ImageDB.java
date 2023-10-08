package model.storage;

import java.util.HashMap;

import model.exceptions.ImageNotFoundException;
import model.image.Image;

/**
 * Acts as an image storage, storing all the images loaded in the current session in a hashmap.
 */
public class ImageDB implements ImageStorage {

  private final HashMap<String, Image> loadedImages;

  /**
   * Initializes the hashmap to store images.
   */
  public ImageDB() {
    this.loadedImages = new HashMap<>();
  }

  @Override
  public Image getImage(String imageName) throws ImageNotFoundException {
    Image image = loadedImages.get(imageName);
    if (image == null) {
      throw new ImageNotFoundException(imageName);
    }
    return image;
  }

  @Override
  public void putImage(String imageName, Image image) {
    loadedImages.put(imageName, image);
  }
}
