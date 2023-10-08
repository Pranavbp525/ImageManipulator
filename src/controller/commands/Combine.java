package controller.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.enums.Command;
import model.manipulator.ImageManipulatorVersion2;

/**
 * Represents the Combine command, which combines greyscale images of multiple channels into one
 * colored image.
 */
public class Combine extends AbstractImageManipulationCommand implements ImageManipulationCommand {

  /**
   * Initializes the fields specific to combine command.
   *
   * @param args the arguments to the command
   */
  public Combine(String[] args) {
    super(Command.COMBINE, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    validateNumberOfArguments(args);
    String combinedImageName = args[0];
    List<String> imageNames = new ArrayList<>(Arrays.asList(args).subList(1, args.length));

    manipulator.combine(imageNames, combinedImageName);
  }
}
