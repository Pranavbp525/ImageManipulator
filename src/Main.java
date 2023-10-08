import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import controller.controllers.Controller;
import controller.controllers.ControllerImplVersion2;
import controller.controllers.NewFeaturesImpl;
import model.manipulator.ImageManipulatorVersion4;
import model.manipulator.ImageManipulatorVersion4Impl;
import view.IView2;
import view.View2;

/**
 * The purpose of this class is to act as a starting point to run the program. It connects the
 * model and controller and gives control to the controller thereafter.
 */
public class Main {

  /**
   * Instantiates model and controller objects and links the controller and model together by
   * passing the model's instance to the controller's constructor. It then relinquishes control to
   * the controller.
   *
   * @param args array of strings that represent input arguments
   */
  public static void main(String[] args) {
    ImageManipulatorVersion4 manipulator = new ImageManipulatorVersion4Impl();
    Controller controller = getControllerBasedOnArgs(args, manipulator);
    try {
      controller.run();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // This method handles the input arguments passed to main method. Based on the number and type
  // of the arguments, it passes a suitable input stream to the Controller.
  private static Controller getControllerBasedOnArgs(String[] args,
                                                     ImageManipulatorVersion4 manipulator) {
    if (args != null) {
      if (args.length == 2) {
        if (args[0].equals("-file")) {
          Reader in = new StringReader("run " + args[1] + "\nq");
          return new ControllerImplVersion2(manipulator, in, System.out);
        }
      } else if (args.length == 1) {
        if (args[0].equals("-text")) {
          return new ControllerImplVersion2(manipulator,
                  new InputStreamReader(System.in), System.out);
        }
      }
    }
    IView2 view = new View2("Image Wizard");
    return new NewFeaturesImpl(manipulator, view);
  }
}
