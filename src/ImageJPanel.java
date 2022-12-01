import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageJPanel extends JPanel {
    private final BufferedImage backgroundImage;
    public static final int worldWidth = Configuration.getWorldWidth();
    public static final int worldHeight = Configuration.getWorldHeight();

    public static final int numberOfTiles = Configuration.getNumberOfTiles();

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
        /*System.out.println(getHeight());
        System.out.println(getWidth());*/

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),this);


        TilesManager tilesManager = new TilesManager();
        tilesManager.generateTiles(numberOfTiles, worldWidth, worldHeight);
        if(Configuration.isDrawGrid()){
            tilesManager.drawGridToPlane((Graphics2D) g, getWidth(), getHeight());
        }

        /*
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
        */


        TrafficAnalysis trafficAnalysis = new SimpleTrafficAnalysis(tilesManager);
        HashMap<Coord2D, Integer> tilesTraffic = trafficAnalysis.analyzeTraffic();

        for(Coord2D tileK : tilesTraffic.keySet()){
            Color trafficColor = trafficAnalysis.getTrafficColor(tileK);
            tilesManager.fillCorrespondingTileWithColor((Graphics2D) g, tileK, getWidth(), getHeight(), trafficColor);
        }

        for(SpaceTimeLocation l : Main.p.getPath()) {
            CircleDrawer circleDrawer = new CircleDrawer(worldWidth, worldHeight);
            circleDrawer.drawCircleToPlane(
                    (Graphics2D) g,
                    new CircleDrawer.Circle(l.getSpace().getX(), l.getSpace().getY(), 30, Color.BLUE),
                    getWidth(),
                    getHeight()
            );
        }
    }
}
