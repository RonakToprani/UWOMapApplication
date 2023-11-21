
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This class displays the map image in the app
 * @version 1.0
 * 
 * @author Colin Brown
 * @author Sinthuja Jeevarajhan
 * @author Victoria Houde
 * @author Edson Jun Da Huang
 * @author Ronak Toprani
 */
public class MapPanel extends JPanel {
    static private BufferedImage[] mapImages = loadMapImages();
    static private BufferedImage image = mapImages[0];
    static private BufferedImage pin;
    static private BufferedImage selectedPin;

    /**
     * Constructor for class MapPanel initializes pin images
     */
    public MapPanel() {
        try {
            pin = ImageIO.read(App.class.getClassLoader().getResource("PinImgs/pin.png"));
            selectedPin = ImageIO.read(App.class.getClassLoader().getResource("PinImgs/selectedPin.png"));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    /**
     * This method draws the images in the app including the map images, POIs,
     * map, etc
     * @param g, object Graphics 
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
        for (Layer layer : App.curr.getLayers()) {
            for (POI poi : layer.getPOIs()) {
                if (poi.getVis()) {
                    if (App.selected != null && App.selected == poi) {
                        g.drawImage(selectedPin, poi.getX() - 9, poi.getY() - 30, null);
                    } else {
                        g.drawImage(pin, poi.getX() - 7, poi.getY() - 26, null);
                    }
                }
            }
        }
    }

    /**
     * This method loads in and returns array of all map images
     * @return imageArr, array of all map images
     */
    private static BufferedImage[] loadMapImages() {
        BufferedImage[] imageArr = new BufferedImage[12];
        for (Building building : App.buildings) {
            for (Map map : building.getMaps()) {
                try {
                    imageArr[map.getMapID()] = ImageIO.read(App.class.getClassLoader().getResource(map.getMapImage()));
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
        return imageArr;
    }

    /**
     * Setter method sets the currently displayed map
     * @param index 
     */
    public static void setMap(int index) {
        image = mapImages[index];
    }

    /**
     * Getter method returns the width of pin
     * @return pin.getWidth()
     */
    public static int getPinWidth() {
        return pin.getWidth();
    }

    /**
     * Setter method returns the height of pin
     * @return pin.getHeight()
     */
    public static int getPinHeight() {
        return pin.getHeight();
    }
}
