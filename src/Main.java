import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;


public class Main {
    public static Path p;
    public static void main(String[] args)  {

        p = new Path();
        Random rand = new Random();


        int initialX = 5000;
        int initialY = 5000;
        int currX = initialX;
        int currY = initialY;


        int a = 0;
        int n = 0;
        int amountOfDelta = 500;
        while(n < 100){

            int deltaX = rand.nextInt(amountOfDelta) - amountOfDelta/2;
            int deltaY = rand.nextInt(amountOfDelta) - amountOfDelta/2;
            currX += deltaX;
            currY += deltaY;

            if(currX < 0){
                currX = 0;
            } else if(currX > 10000) {
                currX = 10000;
            }
            if(currY < 0){
                currY = 0;
            } else if(currY > 10000) {
                currY = 10000;
            }

            Coord2D curr = new Coord2D(currX, currY);


            p.addSpaceTimeLocation(new SpaceTimeLocation(curr, LocalDateTime.now().plusSeconds(n * 10L) ));

            n+=1;

        }
        System.out.println(p);
        try {
            new RootFrame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}