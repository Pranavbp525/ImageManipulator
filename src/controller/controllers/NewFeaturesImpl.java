package controller.controllers;

import java.io.IOException;

import controller.commands.Mosaic;
import model.manipulator.ImageManipulatorVersion2;
import view.IView2;

/**
 * This class implements the new controller interface 'NewFeatures'. This class handles the
 * mosaic operation for GUI request.
 */
public class NewFeaturesImpl extends GUIController implements NewFeatures {

  private final IView2 view;

  /**
   * The constructor is used to initialize the model object.
   *
   * @param model The model object using which the controller executed all the features.
   * @param view  The view object which the controller uses to send its output to.
   */
  public NewFeaturesImpl(ImageManipulatorVersion2 model, IView2 view) {
    super(model, view);
    this.view = view;
  }

  @Override
  public void run() throws IOException {
    this.view.addFeatures(this);
  }

  @Override
  public void mosaic(String seed, String mosaicImageName) {
    String[] args = new String[]{seed, imageInFocus, mosaicImageName};
    command = new Mosaic(args);
    updateView(mosaicImageName);
  }
}
