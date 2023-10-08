package controller.exceptions;

/**
 * This is the exception thrown when the number of arguments provided to the command does not
 * match with the number of arguments required by the command.
 */
public class InvalidNumberOfArgumentsException extends Exception {

  /**
   * Constructs the error message by appending the command name, the required and provided number
   * of arguments to the generic message for the exception.
   *
   * @param commandName the command name entered by the user
   * @param requiredNumberOfArguments the number of arguments required by the command
   * @param providedNumberOfArguments the number of arguments provided to the command
   */
  public InvalidNumberOfArgumentsException(String commandName, int requiredNumberOfArguments,
                                           int providedNumberOfArguments) {
    super("Invalid number of arguments for command : " + commandName + ". Required : "
            + requiredNumberOfArguments + ", Provided : " + providedNumberOfArguments);
  }
}
