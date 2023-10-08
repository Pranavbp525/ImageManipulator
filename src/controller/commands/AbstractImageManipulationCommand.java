package controller.commands;

import java.util.Objects;

import controller.enums.Command;
import controller.utils.GenericImageUtil;
import controller.utils.ImageUtil;
import controller.utils.Messages;
import controller.utils.PPMImageUtil;
import controller.exceptions.InvalidNumberOfArgumentsException;

/**
 * This is an abstract class for all Image Manipulation commands. It provides common code to all
 * image manipulation commands such as initializing command specific fields, validating the number
 * of arguments received and fetching the unique message to be printed before executing a
 * specific command.
 */
public abstract class AbstractImageManipulationCommand implements ImageManipulationCommand {

  protected final Command command;
  protected final String[] args;
  protected final int requiredNumberOfArguments;
  protected final String preExecutionMessage;

  protected AbstractImageManipulationCommand(Command command, String[] args) {
    this.command = command;
    this.requiredNumberOfArguments = command.getNumberOfArguments();
    this.args = args;
    this.preExecutionMessage = command.getPreExecutionMessage();
  }

  protected void validateNumberOfArguments(String[] args) throws InvalidNumberOfArgumentsException {
    if (args.length != requiredNumberOfArguments) {
      throw new InvalidNumberOfArgumentsException(command.getName(), requiredNumberOfArguments,
              args.length);
    }
  }

  protected ImageUtil getImageUtilBasedOnImageFileExtension(String filename) throws
          IllegalArgumentException {
    ImageUtil util;
    String extension;
    try {
      extension = filename.split("[.]")[1];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException(Messages.IMAGE_EXTENSION_MISSING);
    }

    if (Objects.equals(extension, "ppm")) {
      util = new PPMImageUtil();
    } else {
      util = new GenericImageUtil();
    }
    return util;
  }

  @Override
  public String getPreExecutionMessage() {
    return preExecutionMessage;
  }
}
