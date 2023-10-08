package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import controller.commands.Brighten;
import controller.commands.Combine;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.ImageManipulationCommand;
import controller.commands.Load;
import controller.commands.Mosaic;
import controller.commands.Save;
import controller.commands.Sharpen;
import controller.commands.Split;
import controller.commands.VerticalFlip;
import controller.commands.Blur;
import controller.commands.Sepia;
import controller.commands.Dither;

import controller.enums.Command;
import controller.exceptions.CommandNotFoundException;

/**
 * This class contains the collection of all commands known to the program. The appropriate
 * command can be fetched from the collection by passing the command name and the corresponding
 * function object will be returned, which can be executed by calling the 'execute' method on the
 * object.
 */
public class CollectionOfCommands {

  private final Map<String, Function<String[], ImageManipulationCommand>> commands;

  /**
   * Instantiates the collection of commands by adding all the commands.
   */
  public CollectionOfCommands() {
    this.commands = new HashMap<>();
    addCommandsToCollection();
  }

  /**
   * Returns the executable function object of the command corresponding to the name of the command.
   *
   * @param commandName the name of the command
   * @return the executable function object of the command
   * @throws CommandNotFoundException if the command name does not match any of the commands in
   *          the collection
   */
  public Function<String[], ImageManipulationCommand> get(String commandName) throws
          CommandNotFoundException {
    Function<String[], ImageManipulationCommand> commandFunction = commands.get(commandName);
    if (commandFunction == null) {
      throw new CommandNotFoundException(commandName);
    }
    return commandFunction;
  }

  private void addCommandsToCollection() {
    commands.put(Command.BRIGHTEN.getName(), Brighten::new);
    commands.put(Command.VERTICAL_FLIP.getName(), VerticalFlip::new);
    commands.put(Command.HORIZONTAL_FLIP.getName(), HorizontalFlip::new);
    commands.put(Command.SPLIT.getName(), Split::new);
    commands.put(Command.GREYSCALE.getName(), Greyscale::new);
    commands.put(Command.COMBINE.getName(), Combine::new);
    commands.put(Command.LOAD.getName(), Load::new);
    commands.put(Command.SAVE.getName(), Save::new);
    commands.put(Command.BLUR.getName(), Blur::new);
    commands.put(Command.SHARPEN.getName(), Sharpen::new);
    commands.put(Command.SEPIA.getName(), Sepia::new);
    commands.put(Command.DITHER.getName(), Dither::new);
    commands.put(Command.MOSAIC.getName(), Mosaic::new);
  }
}
