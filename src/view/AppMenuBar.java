package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * A class that represents the menu bar of the image processing application.
 * It extends the JMenuBar class and contains various menu items and submenus
 * for the different image processing operations that the user can perform.
 */
public class AppMenuBar extends JMenuBar {
  private IView2 view;
  private final JMenu flipMenu;
  private final JMenu brightenAndDarkenMenu;
  private final JMenu splitAndCombineMenu;
  private final JMenu filterMenu;
  private final JMenu colorTransformMenu;
  private final JMenu ditherMenu;
  private final JMenu loadedImagesMenu;
  private final JMenuItem loadItem;
  private final JMenuItem saveItem;
  private final JMenuItem brightenItem;
  private final JMenuItem darkenItem;
  private final JMenuItem sharpenItem;
  private final JMenuItem blurItem;
  private final JMenuItem redDitherItem;
  private final JMenuItem greenDitherItem;
  private final JMenuItem blueDitherItem;
  private final JMenuItem greyscaleItem;
  private final JMenuItem sepiaItem;
  private final JMenuItem splitItem;
  private final JMenuItem combineItem;
  private final JMenuItem verticalFlipItem;
  private final JMenuItem horizontalFlipItem;
  private final JMenu mosaicMenu;
  private final JMenuItem mosaicItem;

  /**
   * A custom menu bar for the image editor application.
   * This menu bar contains various menu items for performing operations on images
   * such as loading, saving, flipping, brightening/darkening, splitting/combining,
   * filtering, color transformation, and dithering. It also includes a menu for
   * loaded images which allows the user to select an already loaded image to work with.
   * This class extends the JMenuBar class and implements the IView interface for
   * interacting with the image editor application.
   */
  public AppMenuBar() {
    JMenu fileMenu = new JMenu("File");
    flipMenu = new JMenu("Flip");
    brightenAndDarkenMenu = new JMenu("Brighten/Darken");
    splitAndCombineMenu = new JMenu("Split/Combine");
    filterMenu = new JMenu("Filter");
    colorTransformMenu = new JMenu("Color Transform");
    ditherMenu = new JMenu("Dither");
    loadedImagesMenu = new JMenu("Loaded images");
    mosaicMenu = new JMenu("Mosaic");

    loadItem = new JMenuItem("Load");
    saveItem = new JMenuItem("Save");
    brightenItem = new JMenuItem("Brighten");
    darkenItem = new JMenuItem("Darken");
    splitItem = new JMenuItem("Split");
    greyscaleItem = new JMenuItem("Greyscale");
    verticalFlipItem = new JMenuItem("Vertical Flip");
    horizontalFlipItem = new JMenuItem("Horizontal Flip");
    combineItem = new JMenuItem("Combine");
    blurItem = new JMenuItem("Blur");
    sharpenItem = new JMenuItem("Sharpen");
    redDitherItem = new JMenuItem("Red");
    greenDitherItem = new JMenuItem("Green");
    blueDitherItem = new JMenuItem("Blue");
    sepiaItem = new JMenuItem("Sepia");
    mosaicItem = new JMenuItem(("Mosaic"));

    fileMenu.add(loadItem);
    fileMenu.add(saveItem);
    brightenAndDarkenMenu.add(brightenItem);
    brightenAndDarkenMenu.add(darkenItem);
    flipMenu.add(verticalFlipItem);
    flipMenu.add(horizontalFlipItem);
    splitAndCombineMenu.add(splitItem);
    splitAndCombineMenu.add(combineItem);
    filterMenu.add(sharpenItem);
    filterMenu.add(blurItem);
    colorTransformMenu.add(greyscaleItem);
    colorTransformMenu.add(sepiaItem);
    ditherMenu.add(redDitherItem);
    ditherMenu.add(greenDitherItem);
    ditherMenu.add(blueDitherItem);
    mosaicMenu.add(mosaicItem);

    add(fileMenu);
    add(brightenAndDarkenMenu);
    add(flipMenu);
    add(splitAndCombineMenu);
    add(filterMenu);
    add(colorTransformMenu);
    add(ditherMenu);
    add(mosaicMenu);
    add(loadedImagesMenu);
  }

  /**
   * Adds event listeners to each menu item that executes the corresponding method in the provided
   * IView object when clicked.
   *
   * @param view The IView object to be associated with the menu items.
   */
  public void addFeatures(IView2 view) {
    this.view = view;
    loadItem.addActionListener(e -> view.load());
    saveItem.addActionListener(e -> view.save());
    brightenItem.addActionListener(e -> view.brighten());
    darkenItem.addActionListener(e -> view.darken());
    splitItem.addActionListener(e -> view.split());
    greyscaleItem.addActionListener(e -> view.greyscale());
    verticalFlipItem.addActionListener(e -> view.verticalFlip());
    horizontalFlipItem.addActionListener(e -> view.horizontalFlip());
    combineItem.addActionListener(e -> view.combine());
    blurItem.addActionListener(e -> view.blur());
    sharpenItem.addActionListener(e -> view.sharpen());
    redDitherItem.addActionListener(e -> view.dither("red"));
    greenDitherItem.addActionListener(e -> view.dither("green"));
    blueDitherItem.addActionListener(e -> view.dither("blue"));
    sepiaItem.addActionListener(e -> view.sepia());
    mosaicItem.addActionListener(e -> view.mosaic());
  }

  /**
   * Adds a new menu item to the loadedImagesMenu that displays the provided image name and
   * associates it with an event listener that executes the getSelectedImage() method in the
   * associated IView object when clicked.
   *
   * @param imageName The name of the image to be displayed in the new menu item.
   */
  public void addLoadedImageName(String imageName) {
    JMenuItem imageNameItem = new JMenuItem(imageName);
    imageNameItem.addActionListener(e -> view.getSelectedImage(imageName));
    loadedImagesMenu.add(imageNameItem);
  }

  /**
   * Enables or disables all menu items based on the provided boolean value.
   *
   * @param b true to enable menu items, false to disable them.
   */
  public void enableMenuItems(boolean b) {
    saveItem.setEnabled(b);
    flipMenu.setEnabled(b);
    brightenAndDarkenMenu.setEnabled(b);
    splitAndCombineMenu.setEnabled(b);
    filterMenu.setEnabled(b);
    colorTransformMenu.setEnabled(b);
    ditherMenu.setEnabled(b);
    loadedImagesMenu.setEnabled(b);
    mosaicMenu.setEnabled(b);
  }
}
