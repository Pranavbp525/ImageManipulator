package controller.exceptions;

import controller.utils.Messages;

/**
 * This is the exception thrown when the command entered by the user does not match any of
 * commands known to the program.
 */
public class CommandNotFoundException extends Exception {

  /**
   * Constructs the error message by appending the command name entered by the user to the generic
   * message for the exception.
   *
   * @param commandName the command name entered by the user
   */
  public CommandNotFoundException(String commandName) {
    super(Messages.COMMAND_NOT_FOUND + commandName);
  }
}
