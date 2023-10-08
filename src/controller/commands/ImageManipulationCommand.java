package controller.commands;

import model.manipulator.ImageManipulatorVersion2;

/**
 * Represents an image manipulation command.
 */
public interface ImageManipulationCommand {

  /**
   * Executes the command on the given image manipulator object.
   *
   * @param manipulator the image manipulator object on which the command is to be executed
   * @throws Exception when the command cannot be executed for various reasons
   */
  void execute(ImageManipulatorVersion2 manipulator) throws Exception;

  /**
   * Returns the unique message to be printed before the executing the command.
   *
   * @return the unique message to be printed before the executing the command
   */
  String getPreExecutionMessage();
}
