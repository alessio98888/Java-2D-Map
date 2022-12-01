import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ImageJPanel extends JPanel {
    private final BufferedImage backgroundImage;
    public static final int worldWidth = 10000;
    public static final int worldHeight = 10000;

    public static final int numbertOfTiles = 50;
    public int getImageHeight(){
        return backgroundImage.getHeight();
    }

    public int getImageWidth(){
        return backgroundImage.getWidth();
    }
    public ImageJPanel(String fileName) throws IOException {
        backgroundImage = ImageIO.read(new File(fileName));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println(getHeight());
        System.out.println(getWidth());

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),this);


        /*CircleDrawer circleDrawer = new CircleDrawer(worldWidth, worldHeight);
        circleDrawer.drawCircleToPlane(
                (Graphics2D) g,
                new CircleDrawer.Circle(5000, 5000, 100, Color.BLUE),
                getWidth(),
                getHeight()
        );

        circleDrawer.drawCircleToPlane(
                (Graphics2D) g,
                new CircleDrawer.Circle(6000, 5000, 100, Color.RED),
                getWidth(),
                getHeight()
        );

        circleDrawer.drawCircleToPlane(
                (Graphics2D) g,
                new CircleDrawer.Circle(7000, 5000, 100, Color.DARK_GRAY),
                getWidth(),
                getHeight()
        );*/

        // DRAW GRID
        TilesManager tilesManager = new TilesManager();
        tilesManager.generateTiles(numbertOfTiles, worldWidth, worldHeight);
        tilesManager.drawGridToPlane((Graphics2D) g, getWidth(), getHeight());
        tilesManager.fillCorrespondingTileWithColor(
                (Graphics2D) g,
                new Coord2D(9999, 9999),
                getWidth(), getHeight(),
                Color.BLUE);

        tilesManager.fillCorrespondingTileWithColor(
                (Graphics2D) g,
                new Coord2D(0, 0),
                getWidth(), getHeight(),
                Color.RED);

        HashMap<Coord2D, Integer> tilesTraffic = new HashMap<Coord2D, Integer>();
        for(SpaceTimeLocation l : Main.p.getPath()){
            CircleDrawer circleDrawer = new CircleDrawer(worldWidth, worldHeight);
            circleDrawer.drawCircleToPlane(
                    (Graphics2D) g,
                    new CircleDrawer.Circle(l.getSpace().getX(), l.getSpace().getY(), 30, Color.BLUE),
                    getWidth(),
                    getHeight()
            );
            Coord2D tile = tilesManager.getCorrespondingTile(l.getSpace());

            tilesTraffic.put(tile, tilesTraffic.getOrDefault(tile, 0) + 1);


        }
        int maxTraffic = Collections.max(tilesTraffic.values());
        for(Coord2D tileK : tilesTraffic.keySet()){
            int tileKTraffic = tilesTraffic.get(tileK);
            //System.out.println(tileKTraffic);


            Color trafficColor = ColorUtilities.transitionOfHueRange(tileKTraffic/(float) maxTraffic, 120, 0);
            tilesManager.fillCorrespondingTileWithColor((Graphics2D) g, tileK, getWidth(), getHeight(), trafficColor);
        }
    }






}
