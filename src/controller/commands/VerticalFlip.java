package controller.commands;

import controller.enums.Command;
import model.manipulator.ImageManipulatorVersion2;
import utils.FlipDirection;

/**
 * Represents the Vertical flip command, which flips the pixels of an image vertically, i.e.,
 * the resulting image is mirrored across the horizontal line passing though the center of the
 * image.
 */
public class VerticalFlip extends AbstractImageManipulationCommand implements
        ImageManipulationCommand {

  /**
   * Initializes the fields specific to vertical flip command.
   *
   * @param args the arguments to the command
   */
  public VerticalFlip(String[] args) {
    super(Command.VERTICAL_FLIP, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    validateNumberOfArguments(args);
    String originalImageName = args[0];
    String flippedImageName = args[1];

    manipulator.flip(originalImageName, FlipDirection.VERTICAL, flippedImageName);
  }
}
