package controller.commands;

import controller.enums.Command;
import controller.utils.Messages;
import model.manipulator.ImageManipulatorVersion2;

/**
 * Represents the Brighten command, which brightens an image by increasing the pixel values.
 */
public class Brighten extends AbstractImageManipulationCommand implements ImageManipulationCommand {

  /**
   * Initializes the fields specific to brighten command.
   *
   * @param args the arguments to the command
   */
  public Brighten(String[] args) {
    super(Command.BRIGHTEN, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    validateNumberOfArguments(args);
    int brighteningAmount;
    try {
      brighteningAmount = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(Messages.INVALID_BRIGHTENING_AMOUNT);
    }
    String originalImageName = args[1];
    String brightenedImageName = args[2];

    manipulator.brighten(originalImageName, brighteningAmount, brightenedImageName);
  }
}
