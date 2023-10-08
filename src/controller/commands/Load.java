package controller.commands;

import java.io.File;
import java.nio.file.Paths;

import controller.enums.Command;
import controller.utils.ImageUtil;
import model.image.Image;
import model.manipulator.ImageManipulatorVersion2;

/**
 * Represents the Load command, which loads an image from a file into the collection of active
 * images to be used by the current image manipulation session.
 */
public class Load extends AbstractImageManipulationCommand implements ImageManipulationCommand {

  /**
   * Initializes the fields specific to load command.
   *
   * @param args the arguments to the command
   */
  public Load(String[] args) {
    super(Command.LOAD, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    validateNumberOfArguments(args);
    String filename = args[0];
    String imageName = args[1];
    String imagePath = new File(Paths.get("").toAbsolutePath().toFile(), filename)
            .getAbsolutePath();
    ImageUtil util = getImageUtilBasedOnImageFileExtension(filename);
    Image loadedImage = util.getImageFromFile(imagePath);
    if (loadedImage != null) {
      manipulator.putImage(imageName, loadedImage);
    }
  }
}
