package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controller.controllers.Features;

/**
 * An implementation of the IView that represents its graphical representation using native awt and
 * swing. This view is a GUI implementation of the IView Interface, hence it creates graphical
 * representations for each method in its interface.
 */
public class View extends JFrame implements IView {
  private final List<String> loadedImageNames;
  protected final AppMenuBar appMenuBar;
  private final JFileChooser fileChooser;
  private final JLabel imageLabel;
  private final JLabel histLabel;
  private Features features;
  private ImageIcon imageIcon;

  /**
   * This constructor initialized all the variables to default values and sets up the values for
   * all the UI elements.
   *
   * @param caption The title of the program
   */
  public View(String caption) {
    super(caption);
    setIconImage(new ImageIcon(Constants.APP_ICON_PATH).getImage());
    setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    setResizable(false);

    appMenuBar = new AppMenuBar();
    setJMenuBar(appMenuBar);

    BufferedImage image;
    try {
      image = ImageIO.read(new File(Constants.HOME_IMAGE_PATH));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    JPanel imagePanel = new JPanel();
    imageLabel = new JLabel();
    imageIcon = new ImageIcon(image);
    imageLabel.setIcon(imageIcon);
    imagePanel.add(imageLabel);

    JScrollPane scrollPane = new JScrollPane();
    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.setVerticalAlignment(JLabel.CENTER);
    scrollPane.setViewportView(imageLabel);
    scrollPane.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH -
            Constants.IMAGE_LABEL_WIDTH_OFFSET,
            Constants.IMAGE_PANEL_HEIGHT - Constants.IMAGE_LABEL_HEIGHT_OFFSET));
    imagePanel.add(scrollPane, BorderLayout.CENTER);
    imagePanel.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.IMAGE_PANEL_HEIGHT);
    add(imagePanel);

    JPanel histPanel = new JPanel();
    histPanel.setBackground(Constants.HISTOGRAM_PANEL_BACKGROUND_COLOR);
    histLabel = new JLabel();
    histPanel.add(histLabel);
    histPanel.setBounds(0, Constants.IMAGE_PANEL_HEIGHT, Constants.WINDOW_WIDTH,
            Constants.HISTOGRAM_PANEL_HEIGHT);
    add(histPanel);

    loadedImageNames = new ArrayList<>();
    fileChooser = new JFileChooser();

    appMenuBar.enableMenuItems(false);
    setVisible(true);
  }

  @Override
  public void showImage(BufferedImage image) {
    imageIcon = new ImageIcon(image);
    imageLabel.setIcon(imageIcon);
  }

  @Override
  public void showImageHistogram(int[][] histogramMatrix) {
    histLabel.setIcon(new ImageIcon(ViewUtil.getBufferedImageHistogram(histogramMatrix)));
  }

  @Override
  public void addFeatures(Features features) {
    this.features = features;
  }

  @Override
  public void load() {
    fileChooser.setCurrentDirectory(new File(Constants.IMAGES_PATH));
    int response = fileChooser.showOpenDialog(null);
    if (response == JFileChooser.APPROVE_OPTION) {
      String imageFilename = new File(fileChooser.getSelectedFile().getAbsolutePath()).getName();
      String imageFilePath = Constants.IMAGES_PATH + imageFilename;
      String loadedImageName = showPromptAndGetEnteredText("Enter the image name");
      if (loadedImageName != null) {
        features.load(imageFilePath, loadedImageName);
        appMenuBar.enableMenuItems(true);
      }
    }
  }

  @Override
  public void save() {
    fileChooser.setCurrentDirectory(new File(Constants.IMAGES_PATH));
    int result = fileChooser.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      String imageFilename = new File(fileChooser.getSelectedFile().getAbsolutePath()).getName();
      String imageFilePath = Constants.IMAGES_PATH + imageFilename;
      features.save(imageFilePath);
      JOptionPane.showMessageDialog(null, "Image successfully saved.",
              "Success", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  @Override
  public void brighten() {
    String brighteningAmount = showPromptAndGetEnteredText("Enter the brightening amount");
    String brightenedImageName = showPromptAndGetEnteredText("Enter the brightened image name");
    if (brighteningAmount != null & brightenedImageName != null) {
      features.brighten(brighteningAmount, brightenedImageName);
    }
  }

  @Override
  public void darken() {
    String darkeningAmount = showPromptAndGetEnteredText("Enter the darkening amount");
    String darkenedImageName = showPromptAndGetEnteredText("Enter the darkened image name");
    if (darkeningAmount != null & darkenedImageName != null) {
      features.darken(darkeningAmount, darkenedImageName);
    }
  }

  @Override
  public void verticalFlip() {
    String flippedImageName = showPromptAndGetEnteredText("Enter the flipped image name");
    if (flippedImageName != null) {
      features.verticalFlip(flippedImageName);
    }
  }

  @Override
  public void horizontalFlip() {
    String flippedImageName = showPromptAndGetEnteredText("Enter the flipped image name");
    if (flippedImageName != null) {
      features.horizontalFlip(flippedImageName);
    }
  }

  @Override
  public void blur() {
    String blurredImageName = showPromptAndGetEnteredText("Enter the blurred image name");
    if (blurredImageName != null) {
      features.blur(blurredImageName);
    }
  }

  @Override
  public void sharpen() {
    String sharpenedImageName = showPromptAndGetEnteredText("Enter the sharpened image name");
    if (sharpenedImageName != null) {
      features.sharpen(sharpenedImageName);
    }
  }

  @Override
  public void greyscale() {
    String[] options = {"Red", "Green", "Blue", "Value", "Luma", "Intensity"};
    int selectedOption = JOptionPane.showOptionDialog(null,
            "Choose a component", "Components", JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE, null, options, options[4]);
    if (selectedOption != JOptionPane.CLOSED_OPTION) {
      String greyscaleImageName = showPromptAndGetEnteredText("Enter the greyscale image name");
      if (greyscaleImageName != null) {
        features.greyscale(options[selectedOption].toLowerCase() + "-component",
                greyscaleImageName);
      }
    }
  }

  @Override
  public void sepia() {
    String sepiaImageName = showPromptAndGetEnteredText("Enter the sepia image name");
    if (sepiaImageName != null) {
      features.sepia(sepiaImageName);
    }
  }

  @Override
  public void split() {
    String redImageName = showPromptAndGetEnteredText("Enter the red image name");
    String greenImageName = showPromptAndGetEnteredText("Enter the green image name");
    String blueImageName = showPromptAndGetEnteredText("Enter the blue image name");
    if (redImageName != null && greenImageName != null && blueImageName != null) {
      features.split(redImageName, greenImageName, blueImageName);
      JOptionPane.showMessageDialog(null, "Splitting successful. "
                      + "Images can be viewed in Loaded images dropdown.", "Success",
              JOptionPane.INFORMATION_MESSAGE);
    }
  }

  @Override
  public void combine() {
    String[] options = loadedImageNames.toArray(new String[0]);

    JList<String> list = new JList<>(options);
    list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    int result = JOptionPane.showConfirmDialog(null, new JScrollPane(list),
            "Select 3 options", JOptionPane.OK_CANCEL_OPTION);

    if (result == JOptionPane.OK_OPTION) {
      List<String> selectedOptions = list.getSelectedValuesList();
      if (selectedOptions.size() == 3) {
        String combinedImageName = showPromptAndGetEnteredText("Enter the combined image name");
        if (combinedImageName != null) {
          features.combine(selectedOptions, combinedImageName);
        }
      } else {
        JOptionPane.showMessageDialog(null, "Please select exactly "
                + "3 options.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  @Override
  public void dither(String colorChannelName) {
    String ditheredImageName = showPromptAndGetEnteredText("Enter the dithered image name");
    if (ditheredImageName != null) {
      features.dither(colorChannelName, ditheredImageName);
    }
  }

  @Override
  public void addLoadedImageName(String imageName) {
    loadedImageNames.add(imageName);
    appMenuBar.addLoadedImageName(imageName);
  }

  @Override
  public void getSelectedImage(String imageName) {
    features.showSelectedImage(imageName);
  }

  protected String showPromptAndGetEnteredText(String prompt) {
    String newImageName = "";
    while (newImageName.isBlank()) {
      newImageName = JOptionPane.showInputDialog(prompt);
    }
    return newImageName;
  }
}
