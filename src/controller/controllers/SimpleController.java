package controller.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import controller.enums.Command;
import controller.utils.ImageUtil;
import controller.utils.Messages;
import controller.utils.PPMImageUtil;
import model.image.Image;
import model.manipulator.ImageManipulator;
import utils.Component;
import utils.FlipDirection;

/**
 * This class represents the Controller of the program. It is responsible for taking inputs,
 * performing validation and sending those to the model. It is also responsible to interact with the
 * file system to load and save images and maintain the collection of loaded images.
 * It accepts user inputs from any source that implements Readable and, sends outputs through any
 * sink that extends Appendable.
 */
public class SimpleController implements Controller {

  private final File CURRENT_WORKING_DIRECTORY;
  private final HashMap<String, Image> loadedImages;
  private final Appendable out;
  private final ImageManipulator manipulator;
  private final Scanner scanner;

  /**
   * Builds the Controller object. Takes the model, input and output streams as arguments.
   *
   * @param manipulator the model of the program
   * @param in          the input source that extends Readable
   * @param out         the output source that extends Appendable
   */
  public SimpleController(ImageManipulator manipulator, Readable in, Appendable out) {
    this.manipulator = manipulator;
    this.out = out;
    this.CURRENT_WORKING_DIRECTORY = Paths.get("").toAbsolutePath().toFile();
    this.loadedImages = new HashMap<>();
    this.scanner = new Scanner(in);
  }

  @Override
  public void run() throws IOException {
    while (true) {
      int selectedOption = getSelectedOption();
      Command selectedCommand = getSelectedCommandByNumber(selectedOption);
      if (selectedCommand == Command.QUIT) {
        out.append(selectedCommand.getPreExecutionMessage()).append("\n");
        break;
      }
      assert selectedCommand != null;
      String fullCommand = getFullCommand(selectedCommand);
      handleFullCommand(selectedCommand, fullCommand);
    }
  }

  private int getSelectedOption() throws IOException {
    int selectedOption;
    out.append(Messages.SELECT_OPTION_NUMBER);
    for (Command command : Command.values()) {
      out.append(String.valueOf(command.getCommandNumber())).append(". ")
              .append(command.getDescription()).append("\n");
    }
    try {
      selectedOption = Integer.parseInt(scanner.nextLine());
    } catch (InputMismatchException e) {
      selectedOption = -1;
    }
    if (selectedOption < 0 || selectedOption > Command.values().length - 1) {
      out.append(Messages.SELECT_OPTION_NUMBER_ERROR);
      return getSelectedOption();
    }
    return selectedOption;
  }

  private Command getSelectedCommandByNumber(int selectedOption) {
    for (Command command : Command.values()) {
      if (command.getCommandNumber() == selectedOption) {
        return command;
      }
    }
    return null;
  }

  private String getFullCommand(Command selectedCommand) throws IOException {
    out.append(Messages.ENTER_FULL_COMMAND);
    out.append(selectedCommand.getSyntax()).append("\n");
    return scanner.nextLine();
  }

  private void handleFullCommand(Command selectedCommand, String fullCommand) throws IOException {
    String[] tokens = fullCommand.split(" ");
    if (!tokens[0].equals(selectedCommand.getName())) {
      out.append(Messages.INVALID_SYNTAX);
      return;
    }
    String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);
    if (args.length == selectedCommand.getNumberOfArguments()) {
      out.append(selectedCommand.getPreExecutionMessage()).append("\n");
      handleInput(selectedCommand, args);
    } else {
      out.append("Incorrect number of arguments for '").append(selectedCommand.getName())
              .append("'. Required : ")
              .append(String.valueOf(selectedCommand.getNumberOfArguments()))
              .append(" Provided : ").append(String.valueOf(args.length)).append("\n");
    }
  }

  private Command getSelectedCommandByName(String commandName) {
    for (Command command : Command.values()) {
      if (command.getName().equals(commandName)) {
        return command;
      }
    }
    return null;
  }

  private void handleInput(Command selectedCommand, String[] args) throws IOException {
    switch (selectedCommand) {
      case LOAD:
        loadImage(args);
        break;
      case SAVE:
        saveImage(args);
        break;
      case VERTICAL_FLIP:
        flipImage(args, FlipDirection.VERTICAL);
        break;
      case HORIZONTAL_FLIP:
        flipImage(args, FlipDirection.HORIZONTAL);
        break;
      case GREYSCALE:
        createGreyscaleImage(args);
        break;
      case SPLIT:
        splitImage(args);
        break;
      case COMBINE:
        combineImages(args);
        break;
      case BRIGHTEN:
        brightenImage(args);
        break;
      case RUN_SCRIPT:
        runScript(args);
        break;
      default:
        out.append(Messages.INVALID_COMMAND);
    }
  }

  private void runScript(String[] args) throws IOException {
    String filename = args[0];
    String scriptPath = new File(CURRENT_WORKING_DIRECTORY, filename).getAbsolutePath();
    Scanner scanner;
    try {
      scanner = new Scanner(new FileInputStream(scriptPath));
    } catch (FileNotFoundException e) {
      out.append(Messages.FILE_NOT_FOUND_ERROR).append(scriptPath).append("\n");
      return;
    }
    boolean flag = true;
    while (scanner.hasNextLine()) {
      flag = true;
      String line = scanner.nextLine();
      if (line.length() != 0 && line.charAt(0) != '#') {
        String fullCommand = line.strip();
        Command command = getSelectedCommandByName(fullCommand.split(" ")[0]);
        if (command != null) {
          handleFullCommand(command, fullCommand);
        } else {
          flag = false;
          out.append("Invalid command syntax or command is null\n");
          break;
        }
      }
    }
    if (flag) {
      out.append(Messages.FINISHED_RUNNING_SCRIPT);
    }
    else {
      out.append("aborted running script. Please fix the commands in the script and try again\n");
    }
  }

  private void brightenImage(String[] args) throws IOException {
    int brighteningAmount;
    try {
      brighteningAmount = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      out.append(Messages.INVALID_BRIGHTENING_AMOUNT);
      return;
    }
    Image image = loadedImages.get(args[1]);
    if (image == null) {
      out.append(Messages.IMAGE_NOT_FOUND);
      return;
    }
    String brightenedImageName = args[2];
    loadedImages.put(brightenedImageName, manipulator.brighten(image, brighteningAmount));
    out.append(Messages.TASK_SUCCESSFUL);
  }

  private void combineImages(String[] args) throws IOException {
    String combinedImageName = args[0];
    List<Image> images = new ArrayList<>();
    for (int i = 1; i < args.length; i++) {
      Image image = loadedImages.get(args[i]);
      if (image == null) {
        out.append(Messages.IMAGE_NOT_FOUND).append(" -> ").append(args[i]).append("\n");
        return;
      }
      images.add(image);
    }
    try {
      loadedImages.put(combinedImageName, manipulator.combine(images));
    } catch (UnsupportedOperationException e) {
      out.append(e.getMessage()).append("\n");
    }
    out.append(Messages.TASK_SUCCESSFUL);
  }

  private void splitImage(String[] args) throws IOException {
    Image image = loadedImages.get(args[0]);
    if (image == null) {
      out.append(Messages.IMAGE_NOT_FOUND);
      return;
    }
    String redImageName = args[1];
    String greenImageName = args[2];
    String blueImageName = args[3];
    List<Image> splitImages = manipulator.split(image);
    if (splitImages.size() == 3) {
      loadedImages.put(redImageName, splitImages.get(0));
      loadedImages.put(greenImageName, splitImages.get(1));
      loadedImages.put(blueImageName, splitImages.get(2));
      out.append(Messages.TASK_SUCCESSFUL);
    }
  }

  private void createGreyscaleImage(String[] args) throws IOException {
    String componentName = args[0];
    Image image = loadedImages.get(args[1]);
    String greyscaleImageName = args[2];
    if (image == null) {
      out.append(Messages.IMAGE_NOT_FOUND);
      return;
    }
    Component matchingComponent = null;
    for (Component component : Component.values()) {
      if (component.getValue().equals(componentName)) {
        matchingComponent = component;
        loadedImages.put(greyscaleImageName, manipulator.greyScale(image, component));
      }
    }
    if (matchingComponent == null) {
      out.append(Messages.INVALID_COMPONENT_VALUE);
    }
    out.append(Messages.TASK_SUCCESSFUL);
  }

  private void flipImage(String[] args, FlipDirection direction) throws IOException {
    Image image = loadedImages.get(args[0]);
    String flippedImageName = args[1];
    if (image == null) {
      out.append(Messages.IMAGE_NOT_FOUND);
      return;
    }
    loadedImages.put(flippedImageName, manipulator.flip(image, direction));
    out.append(Messages.TASK_SUCCESSFUL);
  }

  private void saveImage(String[] args) throws IOException {
    String filename = args[0];
    Image image = loadedImages.get(args[1]);
    if (image == null) {
      out.append(Messages.IMAGE_NOT_FOUND);
      return;
    }
    File imageFile = new File(filename);
    File parentFolder = imageFile.getParentFile();
    if (parentFolder != null && !parentFolder.exists()) {
      parentFolder.mkdirs();
    }
    String imagePath = imageFile.getAbsolutePath();
    ImageUtil util = null;
    if (Objects.equals(args[0].split("[.]")[1], "ppm")) {
      util = new PPMImageUtil();
    } else {
      out.append(Messages.INVALID_IMAGE_FILE_EXTENSION);
    }
    if (util != null) {
      util.saveImageToFile(image, imagePath);
      out.append(Messages.TASK_SUCCESSFUL);
    }
  }

  private void loadImage(String[] args) throws IOException {
    String filename = args[0];
    String imageName = args[1];
    String imagePath = new File(CURRENT_WORKING_DIRECTORY, filename).getAbsolutePath();
    ImageUtil util = null;
    if (Objects.equals(filename.split("[.]")[1], "ppm")) {
      util = new PPMImageUtil();
    } else {
      out.append(Messages.INVALID_IMAGE_EXTENSION);
      throw new IOException();
    }
    Image loadedImage = util.getImageFromFile(imagePath);
    if (loadedImage != null) {
      loadedImages.put(imageName, loadedImage);
      out.append(Messages.TASK_SUCCESSFUL);
    }
  }
}
