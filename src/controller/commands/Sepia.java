package controller.commands;

import controller.enums.ColorTransform;
import controller.enums.Command;
import model.manipulator.ImageManipulatorVersion2;
import model.manipulator.ImageManipulatorVersion3;

/**
 * Represents the Sepia command, which applies the Sepia filter on an image and resulting image
 * is the Sepia-toned version of the original image.
 */
public class Sepia extends AbstractImageManipulationCommand implements ImageManipulationCommand {

  /**
   * Initializes the fields specific to sepia command.
   *
   * @param args the arguments to the command
   */
  public Sepia(String[] args) {
    super(Command.SEPIA, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    validateNumberOfArguments(args);
    String originalImageName = args[0];
    String sepiaImageName = args[1];

    ((ImageManipulatorVersion3) manipulator).colorTransform(originalImageName,
            ColorTransform.SEPIA.getTransform(), sepiaImageName);
  }
}
