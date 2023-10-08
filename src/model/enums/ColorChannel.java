package model.enums;

/**
 * This enum represents the 3 color channels present in an RGB image.
 */
public enum ColorChannel {
  RED(0, "red"),
  GREEN(1, "green"),
  BLUE(2, "blue");

  private final int channelNumber;
  private final String channelName;

  /**
   * Constructs the Color given its name and channel number.
   *
   * @param channelNumber the channel number of the Color
   * @param channelName the name of the Color
   */
  ColorChannel(int channelNumber, String channelName) {
    this.channelNumber = channelNumber;
    this.channelName = channelName;
  }

  /**
   * Gives the channel number of th color.
   *
   * @return the channel number of the color
   */
  public int getChannelNumber() {
    return channelNumber;
  }

  /**
   * Gives the channel name of th color.
   *
   * @return the channel name of the color
   */
  public String getChannelName() {
    return channelName;
  }
}

