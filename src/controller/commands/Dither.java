package controller.commands;

import controller.enums.Command;
import controller.utils.Messages;
import model.enums.ColorChannel;
import model.manipulator.ImageManipulatorVersion2;
import model.manipulator.ImageManipulatorVersion3;

/**
 * Represents the Dither command, which applies the dithering operation on an image to produce
 * an image with a reduced set of colors compared to the color space of the original image.
 */
public class Dither extends AbstractImageManipulationCommand implements ImageManipulationCommand {

  /**
   * Initializes the fields specific to dither command.
   *
   * @param args the arguments to the command
   */
  public Dither(String[] args) {
    super(Command.DITHER, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    validateNumberOfArguments(args);
    String channelName = args[0];
    String originalImageName = args[1];
    String ditheredImageName = args[2];
    ColorChannel matchingChannel = null;
    for (ColorChannel channel : ColorChannel.values()) {
      if (channel.getChannelName().equals(channelName)) {
        matchingChannel = channel;

        ((ImageManipulatorVersion3) manipulator).dither(originalImageName, channel,
                ditheredImageName);
      }
    }
    if (matchingChannel == null) {
      throw new IllegalArgumentException(Messages.INVALID_CHANNEL_VALUE);
    }
  }
}
