package controller.commands;

import java.io.File;

import controller.enums.Command;
import controller.utils.ImageUtil;
import model.image.Image;
import model.manipulator.ImageManipulatorVersion2;

/**
 * Represents the Save command, which saves an image to a file from the collection of active
 * images in use by the current image manipulation session.
 */
public class Save extends AbstractImageManipulationCommand implements ImageManipulationCommand {

  /**
   * Initializes the fields specific to save command.
   *
   * @param args the arguments to the command
   */
  public Save(String[] args) {
    super(Command.SAVE, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    validateNumberOfArguments(args);
    Image image;
    String filename = args[0];
    image = manipulator.getImage(args[1]);
    File imageFile = new File(filename);
    File parentFolder = imageFile.getParentFile();
    if (parentFolder != null && !parentFolder.exists()) {
      parentFolder.mkdirs();
    }
    String imagePath = imageFile.getAbsolutePath();
    ImageUtil util = getImageUtilBasedOnImageFileExtension(filename);
    util.saveImageToFile(image, imagePath);
  }
}
