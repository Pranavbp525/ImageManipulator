package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a positions near a given point.
 */
public class ClusterPositions {
  private final int x;
  private final int y;
  private final List<Integer> red = new ArrayList<>();
  private final List<Integer> green = new ArrayList<>();
  private final List<Integer> blue = new ArrayList<>();

  /**
   * Constructs a new ClusterPositions object with the specified x and y coordinates.
   *
   * @param x The x coordinate of the position.
   * @param y The y coordinate of the position.
   */
  public ClusterPositions(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x coordinate of the position.
   *
   * @return The x coordinate.
   */
  public int getX() {
    return x;
  }

  /**
   * Returns the y coordinate of the position.
   *
   * @return The y coordinate.
   */
  public int getY() {
    return y;
  }

  /**
   * Calculates the Euclidean distance between this position and another specified position.
   *
   * @param height The height of the image grid.
   * @param width  The width of the image grid.
   * @return The Euclidean distance between the two positions.
   */
  public double calculateDistance(int height, int width) {
    double xDistance = Math.pow(this.y - height, 2);
    double yDistance = Math.pow(this.x - width, 2);
    return Math.sqrt(xDistance + yDistance);
  }

  /**
   * Adds color channel information for a cluster associated with this position.
   *
   * @param red   The red color channel value to add.
   * @param green The green color channel value to add.
   * @param blue  The blue color channel value to add.
   */
  public void addCluster(int red, int green, int blue) {
    this.red.add(red);
    this.green.add(green);
    this.blue.add(blue);
  }

  /**
   * Calculates the average red color channel value for the cluster associated with this position.
   *
   * @return The average red color channel value.
   */
  public int calculateAvgRed() {
    int avgRed = 0;
    for (int channel : red) {
      avgRed += channel;
    }
    avgRed /= red.size();
    return avgRed;
  }

  /**
   * Calculates the average green color channel value for the cluster associated with this
   * position.
   *
   * @return The average green color channel value.
   */
  public int calculateAvgGreen() {
    int avgGreen = 0;
    for (int channel : green) {
      avgGreen += channel;
    }
    avgGreen /= green.size();
    return avgGreen;
  }

  /**
   * Calculates the average blue color channel value for the cluster associated with this position.
   *
   * @return The average blue color channel value.
   */
  public int calculateAvgBlue() {
    int avgBlue = 0;
    for (int channel : blue) {
      avgBlue += channel;
    }
    avgBlue /= blue.size();
    return avgBlue;
  }

}
