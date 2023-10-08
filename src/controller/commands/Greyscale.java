package controller.commands;

import controller.enums.ColorTransform;
import controller.enums.Command;
import controller.utils.Messages;
import model.manipulator.ImageManipulatorVersion2;
import model.manipulator.ImageManipulatorVersion3;
import utils.Component;

/**
 * Represents the Greyscale command, which creates a greyscale image of an image based on the
 * given component.
 */
public class Greyscale extends AbstractImageManipulationCommand
        implements ImageManipulationCommand {

  /**
   * Initializes the fields specific to greyscale command.
   *
   * @param args the arguments to the command
   */
  public Greyscale(String[] args) {
    super(Command.GREYSCALE, args);
  }

  @Override
  public void execute(ImageManipulatorVersion2 manipulator) throws Exception {
    if (args.length == 3) {
      String componentName = args[0];
      String originalImageName = args[1];
      String greyscaleImageName = args[2];
      Component matchingComponent = null;
      for (Component component : Component.values()) {
        if (component.getValue().equals(componentName)) {
          matchingComponent = component;

          manipulator.greyScale(originalImageName, component, greyscaleImageName);
        }
      }
      if (matchingComponent == null) {
        throw new IllegalArgumentException(Messages.INVALID_COMPONENT_VALUE);
      }
    }
    else if (args.length == 2) {
      String originalImageName = args[0];
      String greyscaleImageName = args[1];

      ((ImageManipulatorVersion3) manipulator).colorTransform(originalImageName,
              ColorTransform.GREYSCALE.getTransform(), greyscaleImageName);
    }
  }
}
