package view;

import java.awt.Color;

/**
 * This class contains all the constants used in the View such as dimensions. This makes it easy
 * to change the appearance of the view without touching its code.
 */
public class Constants {

  // These commented out paths must be used if the program is run from Main. Else, if the JAR
  // file is run, the below paths are to be used.

  //  public static final String HOME_IMAGE_PATH = "res/images/homePageImage.jpg";
  //  public static final String APP_ICON_PATH = "res/images/appIcon.png";
  //  public static final String IMAGES_PATH = "res/images/";

  public static final String HOME_IMAGE_PATH = "images/homePageImage.jpg";
  public static final String APP_ICON_PATH = "images/appIcon.png";
  public static final String IMAGES_PATH = "images/";
  public static final int WINDOW_WIDTH = 700;
  public static final int WINDOW_HEIGHT = 940;
  public static final int IMAGE_LABEL_WIDTH_OFFSET = 40;
  public static final int IMAGE_LABEL_HEIGHT_OFFSET = 20;
  public static final int IMAGE_PANEL_HEIGHT = 500;
  public static final int HISTOGRAM_PANEL_HEIGHT = 440;
  public static final int HISTOGRAM_IMAGE_HEIGHT = 180;
  public static final int  HISTOGRAM_HORIZONTAL_SCALING_FACTOR = 3;
  public static final int  HISTOGRAM_VERTICAL_SCALING_FACTOR = 2;
  public static final int HISTOGRAM_STROKE_WIDTH = 2;
  public static final int HISTOGRAM_X_MARGIN = 60;
  public static final int HISTOGRAM_Y_MARGIN = 20;
  public static final int HISTOGRAM_X_TICKS_SPACING = 16;
  public static final int HISTOGRAM_Y_TICKS_SPACING = 10;
  public static final int HISTOGRAM_TICKS_LENGTH = 10;
  public static final int HISTOGRAM_X_AXIS_MAX_VALUE = 256;
  public static final int HISTOGRAM_Y_AXIS_MAX_VALUE = 100;
  public static final int HISTOGRAM_Y_AXIS_LABEL_OFFSET = 30;
  public static final String HISTOGRAM_X_LABEL = "Pixel Values";
  public static final String HISTOGRAM_Y_LABEL = "Frequency (Normalized between 0 & 100)";
  public static final Color HISTOGRAM_INTENSITY_COLOR = Color.WHITE;
  public static final Color HISTOGRAM_LABEL_COLOR = Color.YELLOW;
  public static final Color HISTOGRAM_PANEL_BACKGROUND_COLOR = Color.BLACK;
}
