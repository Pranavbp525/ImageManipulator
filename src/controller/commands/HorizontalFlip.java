package controller.commands;

import controller.enums.Command;
import model.manipulator.ImageManipulatorVersion2;
import utils.FlipDirection;

/**
 * Represents the Horizontal flip command, which flips the pixels of an image horizontally, i.e.,
 * the resulting image is mirrored across the vertical line passing though the center of the image.
 */
public class HorizontalFlip extends AbstractImageManipulationCommand implements
        ImageManipulationCommand {

  /**
   * Initializes the fields specific to horizontal flip command.
   *
   * @param args the arguments to the command
   */
  public HorizontalFlip(String[] args) {
    super(Command.HORIZONTAL_FLIP, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    validateNumberOfArguments(args);
    String originalImageName = args[0];
    String flippedImageName = args[1];
    manipulator.flip(originalImageName, FlipDirection.HORIZONTAL, flippedImageName);
  }
}
