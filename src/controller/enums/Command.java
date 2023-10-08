package controller.enums;

/**
 * This enum represents the commands used for image manipulation and their properties.
 */
public enum Command {

  QUIT(0, 0, "quit", "quit program",
          "quit", "Quitting..."),
  LOAD(1, 2, "load", "load image",
          "load images/{image-filename}.ppm {image-name}", "Loading image..."),
  SAVE(2, 2, "save", "save image",
          "save images/{image-filename}.ppm {image-name}", "Saving image..."),
  BRIGHTEN(3, 3, "brighten", "brighten image",
          "brighten {brightening-amount} {image-name} {brightened-image-name}",
          "Brightening image..."),
  GREYSCALE(4, 3, "greyscale",
          "create greyscale image",
          "greyscale {component-name} {image-name} {greyscale-image-name}",
          "Creating greyscale image..."),
  SPLIT(5, 4, "rgb-split",
          "split image into greyscale images",
          "rgb-split {image-name} {red-image-name} {green-image-name} {blue-image-name}",
          "Splitting image..."),
  COMBINE(6, 4, "rgb-combine",
          "combine greyscale images",
          "rgb-combine {image-name} {red-image-name} {green-image-name} {blue-image-name}",
          "Combining images..."),
  HORIZONTAL_FLIP(7, 2, "horizontal-flip",
          "flip image horizontally",
          "horizontal-flip {image-name} {flipped-image-name}",
          "Horizontally flipping image..."),
  VERTICAL_FLIP(8, 2, "vertical-flip",
          "flip image vertically",
          "vertical-flip {image-name} {flipped-image-name}",
          "Vertically flipping image..."),

  RUN_SCRIPT(9, 1, "run", "run script",
          "run scripts/{script-filename}.txt", "Running script..."),

  BLUR(10,2,"blur","blur an image",
          "blur {image-name} {blurred-image-name}", "Blurring Image..."),

  SHARPEN(11,2,"sharpen","sharpen an image",
          "sharpen {image-name} {sharpened-image-name}",
          "Sharpening Image..."),

  SEPIA(12,2,"sepia","sepia an image",
          "sepia {image-name} {sepia-image-name}",
          "Creating Sepia Image..."),

  DITHER(13,3,"dither","create a dithered image",
          "dither {component-name} {image-name} {dithered-image-name}",
          "Creating dithered Image..."),
  MOSAIC(14, 3, "mosaic", "mosaic image",
          "mosaic {mosaicking-amount} {image-name} {mosaicked-image-name}",
          "Mosaicking image...");


  private final int commandNumber;
  private final int numberOfArguments;
  private final String name;
  private final String description;
  private final String syntax;
  private final String preExecutionMessage;

  /**
   * Initializes a command with its properties.
   *
   * @param commandNumber the position of the command in the sequence
   * @param numberOfArguments the number of arguments required by the command
   * @param name the name of the command
   * @param description a brief description of the command
   * @param syntax the syntax of the command
   * @param preExecutionMessage the message to be printed before executing the command
   */
  Command(int commandNumber, int numberOfArguments, String name, String description, String syntax,
          String preExecutionMessage) {
    this.commandNumber = commandNumber;
    this.numberOfArguments = numberOfArguments;
    this.name = name;
    this.description = description;
    this.syntax = syntax;
    this.preExecutionMessage = preExecutionMessage;
  }

  /**
   * Gives the command number.
   *
   * @return the command number
   */
  public int getCommandNumber() {
    return commandNumber;
  }

  /**
   * Gives the number of arguments required by the command.
   *
   * @return the number of arguments required by the command
   */
  public int getNumberOfArguments() {
    return numberOfArguments;
  }

  /**
   * Gives the command name.
   *
   * @return the command name
   */
  public String getName() {
    return name;
  }

  /**
   * Gives the command description.
   *
   * @return the command description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gives the command syntax.
   *
   * @return the command syntax
   */
  public String getSyntax() {
    return syntax;
  }

  /**
   * Gives the message to be printed before executing the command.
   *
   * @return the message to be printed before executing the command
   */
  public String getPreExecutionMessage() {
    return preExecutionMessage;
  }
}
