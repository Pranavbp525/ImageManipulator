package controller.commands;

import controller.enums.Command;
import controller.utils.Messages;
import model.manipulator.ImageManipulatorVersion2;
import model.manipulator.ImageManipulatorVersion4;

/**
 * Represents the Mosaic command, which perform the mosaicking of an image by given seed value.
 */
public class Mosaic extends AbstractImageManipulationCommand implements ImageManipulationCommand {

  /**
   * Initializes the fields specific to mosaic command.
   *
   * @param args the arguments to the command
   */
  public Mosaic(String[] args) {
    super(Command.MOSAIC, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    validateNumberOfArguments(args);
    int seed;
    try {
      seed = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(Messages.INVALID_MOSAIC_SEED);
    }
    String originalImageName = args[1];
    String mosaicImageName = args[2];

    ((ImageManipulatorVersion4) manipulator).mosaic(originalImageName, seed, mosaicImageName);
  }
}
