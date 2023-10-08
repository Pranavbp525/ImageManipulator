package utils;

/**
 * This enum represents the properties of an image on which a greyscale image can be extracted.
 */
public enum Component {

  RED("red-component"),
  GREEN("green-component"),
  BLUE("blue-component"),
  VALUE("value-component"),
  INTENSITY("intensity-component"),
  LUMA("luma-component");

  final String value;

  /**
   * Constructs the Component given its value.
   *
   * @param value the value of the component
   */
  Component(String value) {
    this.value = value;
  }

  /**
   * Gives the value of the component.
   *
   * @return the value of the component
   */
  public String getValue() {
    return value;
  }
}
