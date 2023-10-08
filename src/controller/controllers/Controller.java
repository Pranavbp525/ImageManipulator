package controller.controllers;

import java.io.IOException;

/**
 * This interface represents the controller of the program. It is responsible to receive the inputs,
 * send the outputs and act as an intermediate between the user, model and the view.
 */
public interface Controller {

  /**
   * This method takes control from the main method and fully controls the program.
   * @throws IOException if there is a problem while interacting with input and output streams
   */
  void run() throws IOException;
}
