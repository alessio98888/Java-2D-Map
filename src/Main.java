import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;


public class Main {
    public static Path p;

    public static void main(String[] args)  {


        int numberOfTiles = Configuration.getNumberOfTiles();
        int worldWidth = Configuration.getWorldWidth();
        int worldHeight = Configuration.getWorldHeight();

        p = new Path();
        Random rand = new Random();


        int initialX = worldWidth/2;
        int initialY = worldHeight/2;
        int currX = initialX;
        int currY = initialY;


        int amountOfDelta = 500;
        for(int n = 0; n < 100; n++){

            int deltaX = rand.nextInt(amountOfDelta) - amountOfDelta/2;
            int deltaY = rand.nextInt(amountOfDelta) - amountOfDelta/2;
            currX += deltaX;
            currY += deltaY;

            if(currX < 0){
                currX = 0;
            } else if(currX > worldWidth) {
                currX = worldWidth;
            }
            if(currY < 0){
                currY = 0;
            } else if(currY > worldHeight) {
                currY = worldHeight;
            }

            Coord2D curr = new Coord2D(currX, currY);

            p.addSpaceTimeLocation(new SpaceTimeLocation(curr, LocalDateTime.now().plusSeconds(n * 10L) ));

        }

        System.out.println(p);
        try {
            new RootFrame();
        } catch (IOException e) {
            System.err.println("Error opening the root frame.");
            throw new RuntimeException(e);
        }
    }
}