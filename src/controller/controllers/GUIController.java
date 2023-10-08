package controller.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import controller.commands.Blur;
import controller.commands.Brighten;
import controller.commands.Combine;
import controller.commands.Dither;
import controller.commands.Greyscale;
import controller.commands.HorizontalFlip;
import controller.commands.ImageManipulationCommand;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import controller.commands.Split;
import controller.commands.VerticalFlip;
import controller.utils.CommonImageUtil;
import model.image.Image;
import model.manipulator.ImageManipulatorVersion2;
import model.exceptions.ImageNotFoundException;
import view.IView;

/**
 * This controller is specifically designed to work with GUI views. It implements the Features class
 * which contains all the features offered by the program. This controller uses command design
 * pattern to perform the operation and updates the view (GUI) accordingly so that the user can see
 * those updates made to the image.
 */
public class GUIController implements Features {
  private final ImageManipulatorVersion2 model;
  private final IView view;
  protected String imageInFocus;
  protected ImageManipulationCommand command;

  /**
   * The constructor is used to initialize the model object.
   * @param model The model object using which the controller executed all the features.
   * @param view The view object which the controller uses to send its output to.
   */
  public GUIController(ImageManipulatorVersion2 model, IView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void run() throws IOException {
    this.view.addFeatures(this);
  }

  @Override
  public void load(String imageFilename, String loadedImageName) {
    String[] args = new String[]{imageFilename, loadedImageName};
    command = new Load(args);
    updateView(loadedImageName);
  }

  @Override
  public void save(String imageFilename) {
    String[] args = new String[]{imageFilename, imageInFocus};
    command = new Save(args);
    try {
      executeCommand();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  private BufferedImage getBufferedImage(String imageName) {
    try {
      Image image = model.getImage(imageName);
      return CommonImageUtil.getBufferedImageFromImage(image);
    } catch (ImageNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private int[][] getHistogramMatrix(String imageName) {
    try {
      Image image = model.getImage(imageName);
      return CommonImageUtil.getImageHistogramMatrix(image);
    } catch (ImageNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  protected void updateView(String imageName) {
    try {
      executeCommand();
      showImageAndHistogram(imageName);
      view.addLoadedImageName(imageName);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public void showSelectedImage(String imageName) {
    showImageAndHistogram(imageName);
  }

  private void showImageAndHistogram(String imageName) {
    view.showImage(getBufferedImage(imageName));
    view.showImageHistogram(getHistogramMatrix(imageName));
    imageInFocus = imageName;
  }

  private void executeCommand() throws Exception {
    command.execute(model);
  }

  @Override
  public void brighten(String brighteningAmount, String brightenedImageName) {
    changeBrightness(brighteningAmount, brightenedImageName);
  }

  @Override
  public void darken(String darkeningAmount, String darkenedImageName) {
    changeBrightness("-" + darkeningAmount, darkenedImageName);
  }

  private void changeBrightness(String amount, String modifiedImageName) {
    String[] args = new String[]{amount, imageInFocus, modifiedImageName};
    command = new Brighten(args);
    updateView(modifiedImageName);
  }

  @Override
  public void verticalFlip(String flippedImageName) {
    String[] args = new String[]{imageInFocus, flippedImageName};
    command = new VerticalFlip(args);
    updateView(flippedImageName);
  }

  @Override
  public void horizontalFlip(String flippedImageName) {
    String[] args = new String[]{imageInFocus, flippedImageName};
    command = new HorizontalFlip(args);
    updateView(flippedImageName);
  }

  @Override
  public void greyscale(String componentName, String greyscaleImageName) {
    String[] args = new String[]{componentName, imageInFocus, greyscaleImageName};
    command = new Greyscale(args);
    updateView(greyscaleImageName);
  }

  @Override
  public void blur(String blurredImageName) {
    String[] args = new String[]{imageInFocus, blurredImageName};
    command = new Blur(args);
    updateView(blurredImageName);
  }

  @Override
  public void sharpen(String sharpenedImageName) {
    String[] args = new String[]{imageInFocus, sharpenedImageName};
    command = new Sharpen(args);
    updateView(sharpenedImageName);
  }

  @Override
  public void sepia(String sepiaImageName) {
    String[] args = new String[]{imageInFocus, sepiaImageName};
    command = new Sepia(args);
    updateView(sepiaImageName);
  }

  @Override
  public void split(String redImageName, String greenImageName, String blueImageName) {
    String[] args = new String[]{imageInFocus, redImageName, greenImageName, blueImageName};
    command = new Split(args);
    try {
      executeCommand();
      view.addLoadedImageName(args[1]);
      view.addLoadedImageName(args[2]);
      view.addLoadedImageName(args[3]);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public void combine(List<String> selectedImageNames, String combinedImageName) {
    selectedImageNames.add(0, combinedImageName);
    String[] args = selectedImageNames.toArray(new String[0]);
    command = new Combine(args);
    updateView(combinedImageName);
  }

  @Override
  public void dither(String channelName, String ditheredImageName) {
    String[] args = new String[]{channelName, imageInFocus, ditheredImageName};
    command = new Dither(args);
    updateView(ditheredImageName);
  }
}
