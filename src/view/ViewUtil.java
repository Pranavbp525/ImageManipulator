package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a utility class for the view and provides functionality for those methods which
 * require a lot of calculations such as drawing images onto the GUI.
 */
public class ViewUtil {

  /**
   * Creates a buffered image of the histogram from the given histogram matrix.
   *
   * @param histogramMatrix the matrix containing frequency of pixel values for red, green, blue
   *                        and intensity channels.
   * @return the buffered image of the histogram
   */
  public static BufferedImage getBufferedImageHistogram(int[][] histogramMatrix) {
    List<Color> colors = new ArrayList<>();
    colors.add(Color.RED);
    colors.add(Color.GREEN);
    colors.add(Color.BLUE);
    colors.add(Constants.HISTOGRAM_INTENSITY_COLOR);
    BufferedImage histogram = new BufferedImage(Constants.WINDOW_WIDTH,
            Constants.HISTOGRAM_PANEL_HEIGHT,
            BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = histogram.createGraphics();
    g2d.setColor(Constants.HISTOGRAM_LABEL_COLOR);

    // Draw x-axis
    g2d.drawLine(Constants.HISTOGRAM_X_MARGIN, Constants.HISTOGRAM_IMAGE_HEIGHT +
                    Constants.HISTOGRAM_Y_MARGIN,
            Constants.HISTOGRAM_X_AXIS_MAX_VALUE
                    * Constants.HISTOGRAM_HORIZONTAL_SCALING_FACTOR + Constants.HISTOGRAM_X_MARGIN,
            Constants.HISTOGRAM_IMAGE_HEIGHT + Constants.HISTOGRAM_Y_MARGIN);

    for (int x = 0; x <= Constants.HISTOGRAM_X_AXIS_MAX_VALUE; x = x
            + Constants.HISTOGRAM_X_TICKS_SPACING) {
      int tickLocation =
              Constants.HISTOGRAM_X_MARGIN + x * Constants.HISTOGRAM_HORIZONTAL_SCALING_FACTOR;
      // Draw ticks on x-axis
      g2d.drawLine(tickLocation,
              Constants.HISTOGRAM_IMAGE_HEIGHT + Constants.HISTOGRAM_Y_MARGIN
                      - Constants.HISTOGRAM_TICKS_LENGTH / 2,
              tickLocation,
              Constants.HISTOGRAM_IMAGE_HEIGHT + Constants.HISTOGRAM_Y_MARGIN
                      + Constants.HISTOGRAM_TICKS_LENGTH / 2);
      // Draw values on x-axis
      String value = String.valueOf(x);
      g2d.drawString(value, tickLocation - g2d.getFontMetrics().stringWidth(value) / 2,
              Constants.HISTOGRAM_IMAGE_HEIGHT + Constants.HISTOGRAM_Y_MARGIN * 2);
    }
    // Draw label on x-axis
    g2d.drawString(Constants.HISTOGRAM_X_LABEL,
            Constants.WINDOW_WIDTH / 2 - g2d.getFontMetrics()
                    .stringWidth(Constants.HISTOGRAM_X_LABEL) / 2,
            Constants.HISTOGRAM_IMAGE_HEIGHT + Constants.HISTOGRAM_Y_MARGIN * 3);

    // Draw y-axis
    g2d.drawLine(Constants.HISTOGRAM_X_MARGIN,
            Constants.HISTOGRAM_IMAGE_HEIGHT + Constants.HISTOGRAM_Y_MARGIN
                    - Constants.HISTOGRAM_Y_AXIS_MAX_VALUE
                    * Constants.HISTOGRAM_VERTICAL_SCALING_FACTOR,
            Constants.HISTOGRAM_X_MARGIN,
            Constants.HISTOGRAM_IMAGE_HEIGHT + Constants.HISTOGRAM_Y_MARGIN);

    for (int y = 0; y <= Constants.HISTOGRAM_Y_AXIS_MAX_VALUE; y = y
            + Constants.HISTOGRAM_Y_TICKS_SPACING) {
      int tickLocation =
              Constants.HISTOGRAM_IMAGE_HEIGHT - Constants.HISTOGRAM_VERTICAL_SCALING_FACTOR
                      * Constants.HISTOGRAM_Y_AXIS_MAX_VALUE + Constants.HISTOGRAM_Y_MARGIN
                      + y * Constants.HISTOGRAM_VERTICAL_SCALING_FACTOR;
      // Draw ticks on y-axis
      g2d.drawLine(Constants.HISTOGRAM_X_MARGIN - Constants.HISTOGRAM_TICKS_LENGTH / 2,
              tickLocation,
              Constants.HISTOGRAM_X_MARGIN
                      + Constants.HISTOGRAM_TICKS_LENGTH / 2, tickLocation);
      // Draw values on y-axis
      String value = String.valueOf(Constants.HISTOGRAM_Y_AXIS_MAX_VALUE - y);
      g2d.drawString(value, Constants.HISTOGRAM_X_MARGIN
                      - Constants.HISTOGRAM_Y_AXIS_LABEL_OFFSET,
              tickLocation + g2d.getFontMetrics().getHeight() / 2);
    }

    // Draw label on y-axis
    g2d.rotate(-Math.PI / 2);
    g2d.drawString(Constants.HISTOGRAM_Y_LABEL,
            -Constants.HISTOGRAM_IMAGE_HEIGHT / 2 - g2d.getFontMetrics()
                    .stringWidth(Constants.HISTOGRAM_Y_LABEL) / 2,
            Constants.HISTOGRAM_X_MARGIN - Constants.HISTOGRAM_Y_MARGIN * 2);
    g2d.rotate(Math.PI / 2);

    // Draw line plots
    g2d.setStroke(new BasicStroke(Constants.HISTOGRAM_STROKE_WIDTH));
    for (int i = 0; i < 255; i++) {
      for (int c = 0; c < 4; c++) {
        g2d.setColor(colors.get(c));
        g2d.drawLine(i * Constants.HISTOGRAM_HORIZONTAL_SCALING_FACTOR
                        + Constants.HISTOGRAM_X_MARGIN,
                Constants.HISTOGRAM_IMAGE_HEIGHT - histogramMatrix[c][i]
                        * Constants.HISTOGRAM_VERTICAL_SCALING_FACTOR
                        + Constants.HISTOGRAM_Y_MARGIN,
                (i + 1) * Constants.HISTOGRAM_HORIZONTAL_SCALING_FACTOR
                        + Constants.HISTOGRAM_X_MARGIN,
                Constants.HISTOGRAM_IMAGE_HEIGHT - histogramMatrix[c][i + 1]
                        * Constants.HISTOGRAM_VERTICAL_SCALING_FACTOR
                        + Constants.HISTOGRAM_Y_MARGIN);
      }
    }
    g2d.dispose();
    return histogram;
  }
}
