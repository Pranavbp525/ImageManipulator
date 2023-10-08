package model.exceptions;

/**
 * This is the exception thrown when the image name entered by the user does not match any of
 * images present in the collection of loaded images.
 */
public class ImageNotFoundException extends Exception {

  /**
   * Constructs the error message by appending the image name entered by the user to the generic
   * message for the exception.
   *
   * @param imageName the image name entered by the user
   */
  public ImageNotFoundException(String imageName) {
    super("Image not found : " + imageName);
  }
}
