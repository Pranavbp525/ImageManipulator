package controller.commands;

import java.util.ArrayList;
import java.util.List;

import controller.enums.Command;
import model.manipulator.ImageManipulatorVersion2;

/**
 * Represents the Split command, which splits a multichannel colored image into greyscale
 * images of individual channels.
 */
public class Split extends AbstractImageManipulationCommand implements ImageManipulationCommand {

  /**
   * Initializes the fields specific to split command.
   *
   * @param args the arguments to the command
   */
  public Split(String[] args) {
    super(Command.SPLIT, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    validateNumberOfArguments(args);
    List<String> arguments = new ArrayList<>(List.of(args));
    String originalImageName = arguments.get(0);
    List<String> splitImageNames = arguments.subList(1, arguments.size());

    manipulator.split(originalImageName, splitImageNames);
  }
}
