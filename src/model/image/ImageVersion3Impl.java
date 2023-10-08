package model.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

import utils.ClusterPositions;

/**
 * This class implements a ImageVersion3 interface. This class performs the mosaic operation on
 * the image.
 */
public class ImageVersion3Impl extends ImageVersion2Impl implements ImageVersion3 {

  protected ImageVersion3Impl(int height, int width, int minimumPixelValue, int maximumPixelValue,
                              int numberOfChannels, int[][][] matrix) {
    super(height, width, minimumPixelValue, maximumPixelValue, numberOfChannels, matrix);
  }

  @Override
  public Image mosaic(int seed) {
    int width = this.getWidth();
    int height = this.getHeight();

    if (seed == width * height) {
      return this;
    } else if (seed > width * height || seed < 1) {
      throw new IllegalArgumentException("Error: Seed value is invalid");
    } else {
      int[][][] mosaickedImageMatrix =
              new int[height][width][this.getNumberOfChannels()];
      List<ClusterPositions> cluster = findCluster(height, width, seed);

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int minDistance = findDist(i, j, cluster, seed);
          cluster.get(minDistance).addCluster(this.getPixelValue(i, j, 0),
                  this.getPixelValue(i, j, 1), this.getPixelValue(i, j, 2));
        }
      }

      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          int minDistance = findDist(row, col, cluster, seed);
          mosaickedImageMatrix[row][col][0] = cluster.get(minDistance).calculateAvgRed();
          mosaickedImageMatrix[row][col][1] = cluster.get(minDistance).calculateAvgGreen();
          mosaickedImageMatrix[row][col][2] = cluster.get(minDistance).calculateAvgBlue();
        }
      }
      return new ImageVersion3ImplBuilder().setHeight(height).setWidth(width)
              .setMatrix(mosaickedImageMatrix).build();
    }
  }


  private int findDist(int i, int j, List<ClusterPositions> centroids, int seeds) {
    ArrayList<Double> distances = new ArrayList<>();
    for (int k = 0; k < seeds; k++) {
      distances.add(centroids.get(k).calculateDistance(i, j));
    }
    return distances.indexOf(Collections.min(distances));
  }

  private List<ClusterPositions> findCluster(int height, int width, int seeds) {
    List<ClusterPositions> cluster = new ArrayList<>();
    Random random = new Random();
    int randX = random.nextInt(width);
    int randY = random.nextInt(height);
    ClusterPositions clusterPositions = new ClusterPositions(randX, randY);
    cluster.add(clusterPositions);
    while (cluster.size() != seeds) {
      randX = random.nextInt(width);
      randY = random.nextInt(height);
      clusterPositions = new ClusterPositions(randX, randY);
      for (int i = 0; i < cluster.size(); i++) {
        if (!(cluster.get(i).getX() == clusterPositions.getX()
                && cluster.get(i).getY() == clusterPositions.getY())) {
          cluster.add(clusterPositions);
          break;
        }
      }
    }
    return cluster;
  }

  /**
   * This is a builder class to build the ImageVersion3Impl object.
   */
  public static class ImageVersion3ImplBuilder extends AbstractImageBuilder
          implements ImageBuilder {
    /**
     * Initializes the builder object by calling the super class constructor.
     */
    public ImageVersion3ImplBuilder() {
      super();
    }

    @Override
    public ImageVersion3 build() {
      return new ImageVersion3Impl(height, width, minimumPixelValue, maximumPixelValue,
              numberOfChannels, matrix);
    }
  }
}
