import java.awt.*;
import java.util.ArrayList;

public class CircleDrawer {

    static class Circle
    {
        // Center of circle coordinates
        Coord2D coord;
        public int diameter;
        Color color;
        public Circle(int x, int y, int diameter, Color color) {
            this.coord = new Coord2D(x, y);
            this.diameter = diameter;
            this.color = color;
        }

        public int coordThatIndicatesCenter(int coordinate){
            return coordinate - diameter/2;
        }
        public Coord2D coordThatIndicatesCenter(){
            return new Coord2D(coord.getX() - diameter/2, coord.getY() - diameter/2);
        }

        public Circle toPlane(int worldWidth, int worldHeight, int planeWidth, int planeHeight){
            Coord2D mappedCoord = coordThatIndicatesCenter().mapFromWorldToPlane(worldWidth, worldHeight, planeWidth, planeHeight);
            int planeDiameter = Utils.mapRangeToRange(diameter, 0, worldWidth, 0, planeWidth + planeHeight);
            return new Circle(mappedCoord.getX(), mappedCoord.getY(), planeDiameter, color);

        }

    }

    int worldWidth;
    int worldHeight;
    ArrayList<Circle> circles;
    private void initCircles() {
        circles = new ArrayList<Circle>();
    }

    public CircleDrawer(int worldWidth, int worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        initCircles();
    }
    public void addCircleToPlane(Circle c, int planeWidth, int planeHeight){
        circles.add(c.toPlane(worldWidth, worldHeight, planeWidth, planeHeight));
    }
    public void draw(Graphics2D g) // draw must be called by paintComponent of the panel
    {
        for (Circle c : circles){
            g.fillOval(c.coord.getX(), c.coord.getY(), c.diameter, c.diameter);
        }
    }

    public void drawCircleToPlane(Graphics2D g, Circle circleToDraw, int planeWidth, int planeHeight ) // draw must be called by paintComponent of the panel
    {
        Color prevColor = g.getColor();
        g.setColor(circleToDraw.color);
        Circle cToPlane = circleToDraw.toPlane(worldWidth, worldHeight, planeWidth, planeHeight);
        g.fillOval(cToPlane.coord.getX(), cToPlane.coord.getY(), cToPlane.diameter, cToPlane.diameter);
        g.setColor(prevColor);

    }


}
