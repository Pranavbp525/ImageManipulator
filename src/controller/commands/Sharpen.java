package controller.commands;

import controller.enums.Command;
import controller.enums.Filter;
import model.manipulator.ImageManipulatorVersion2;
import model.manipulator.ImageManipulatorVersion3;

/**
 * Represents the Sharpen command, which applies the Sharpen kernel on an image and resulting
 * image is the sharpened version of the original image.
 */
public class Sharpen extends AbstractImageManipulationCommand implements ImageManipulationCommand {

  /**
   * Initializes the fields specific to sharpen command.
   *
   * @param args the arguments to the command
   */
  public Sharpen(String[] args) {
    super(Command.SHARPEN, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    validateNumberOfArguments(args);
    String originalImageName = args[0];
    String sharpenedImageName = args[1];

    ((ImageManipulatorVersion3) manipulator).filter(originalImageName, Filter.SHARPEN.getKernel(),
            sharpenedImageName);
  }
}
