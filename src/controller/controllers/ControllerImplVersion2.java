package controller.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

import controller.CollectionOfCommands;
import controller.commands.ImageManipulationCommand;
import controller.enums.Command;
import controller.exceptions.CommandNotFoundException;
import controller.exceptions.InvalidNumberOfArgumentsException;
import controller.utils.Messages;
import model.manipulator.ImageManipulatorVersion2;
import model.exceptions.ImageNotFoundException;

/**
 * This class represents the Controller of the program. It is responsible for taking inputs,
 * performing validation and sending those to the model. It is also responsible to interact with the
 * file system to load and save images and maintain the collection of loaded images.
 * It accepts user inputs from any source that implements Readable and, sends outputs through any
 * sink that extends Appendable.
 */
public class ControllerImplVersion2 implements Controller {

  private final File CURRENT_WORKING_DIRECTORY;
  private final Appendable out;
  private final ImageManipulatorVersion2 manipulator;
  private final Scanner scanner;
  private final CollectionOfCommands commands;

  /**
   * Builds the Controller object. Takes the model, input and output streams as arguments.
   *
   * @param manipulator the model of the program
   * @param in          the input source that extends Readable
   * @param out         the output source that extends Appendable
   */
  public ControllerImplVersion2(ImageManipulatorVersion2 manipulator, Readable in, Appendable out) {
    this.manipulator = manipulator;
    this.out = out;
    this.CURRENT_WORKING_DIRECTORY = Paths.get("").toAbsolutePath().toFile();
    this.scanner = new Scanner(in);
    this.commands = new CollectionOfCommands();
  }

  @Override
  public void run() throws IOException {
    out.append(Messages.WELCOME_MESSAGE);
    while (true) {
      out.append(Messages.TYPE_INSTRUCTION_MESSAGE);
      String[] tokens = scanner.nextLine().split(" ");
      if (tokens.length > 0) {
        try {
          String commandName = tokens[0];
          if (commandName.equalsIgnoreCase("q")
                  || commandName.equalsIgnoreCase(Command.QUIT.getName())) {
            out.append(Command.QUIT.getPreExecutionMessage());
            break;
          }
          handleInput(tokens);
        } catch (Exception e) {
          out.append(e.getMessage()).append("\n");
        }
      }
    }
  }

  private void handleInput(String[] tokens) throws Exception {
    ImageManipulationCommand command;
    String commandName = tokens[0];
    if (tokens.length < 2) {
      throw new RuntimeException(Messages.ARGUMENTS_NOT_PROVIDED);
    }
    String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);
    if (commandName.equals(Command.RUN_SCRIPT.getName())) {
      runScript(args);
      return;
    }
    Function<String[], ImageManipulationCommand> cmd = commands.get(commandName);
    command = cmd.apply(args);
    out.append(command.getPreExecutionMessage()).append("\n");
    command.execute(manipulator);
    out.append(Messages.TASK_SUCCESSFUL);
  }

  private void runScript(String[] args) throws Exception {
    if (args.length != Command.RUN_SCRIPT.getNumberOfArguments()) {
      throw new InvalidNumberOfArgumentsException(Command.RUN_SCRIPT.getName(),
              Command.RUN_SCRIPT.getNumberOfArguments(), args.length);
    }
    String filename = args[0];
    String scriptPath = new File(CURRENT_WORKING_DIRECTORY, filename).getAbsolutePath();
    Scanner scanner;
    try {
      scanner = new Scanner(new FileInputStream(scriptPath));
    }
    catch (FileNotFoundException ex) {
      out.append(Messages.FILE_NOT_FOUND_ERROR).append(scriptPath).append("\n");
      return;
    }
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.length() != 0 && line.charAt(0) != '#') {
        try {
          handleInput(line.strip().split(" "));
        } catch (ImageNotFoundException e) {
          out.append(Messages.IMAGE_NOT_FOUND);
          out.append(Messages.ABORTING_SCRIPT);
          return;
        } catch (CommandNotFoundException e) {
          out.append(Messages.INVALID_COMMAND);
          out.append(Messages.ABORTING_SCRIPT);
          return;
        }
      }
    }
    out.append(Messages.FINISHED_RUNNING_SCRIPT).append(filename).append("\n");
  }
}
