package controller.commands;

import controller.enums.Command;
import controller.enums.Filter;
import model.manipulator.ImageManipulatorVersion2;
import model.manipulator.ImageManipulatorVersion3;

/**
 * Represents the Blur command, which applies the Gaussian blur kernel on an image and resulting
 * image is the blurred version of the original image.
 */
public class Blur extends AbstractImageManipulationCommand implements ImageManipulationCommand {

  /**
   * Initializes the fields specific to blur command.
   *
   * @param args the arguments to the command
   */
  public Blur(String[] args) {
    super(Command.BLUR, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    validateNumberOfArguments(args);
    String originalImageName = args[0];
    String blurredImageName = args[1];

    ((ImageManipulatorVersion3) manipulator).filter(originalImageName, Filter.BLUR.getKernel(),
            blurredImageName);
  }
}
