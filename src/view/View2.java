package view;

import controller.controllers.NewFeatures;

/**
 * This view class implements the IView2 class. It will add the mosaic method to handle the
 * mosaic operation request made on GUI. It will call the new controller for this request
 * fulfillment.
 */
public class View2 extends View implements IView2 {
  private NewFeatures features;

  /**
   * This constructor initialized all the variables to default values and sets up the values for
   * all the UI elements.
   *
   * @param caption The title of the program
   */
  public View2(String caption) {
    super(caption);
  }

  @Override
  public void addFeatures(NewFeatures features) {
    this.features = features;
    appMenuBar.addFeatures(this);
    super.addFeatures(features);
  }

  @Override
  public void mosaic() {
    String mosaicSeed = showPromptAndGetEnteredText("Enter the seed value");
    String mosaicImageName = showPromptAndGetEnteredText("Enter the mosaic image name");
    if (mosaicSeed != null & mosaicImageName != null) {
      features.mosaic(mosaicSeed, mosaicImageName);
    }
  }

}
