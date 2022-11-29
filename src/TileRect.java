import java.awt.*;

public class TileRect {

    private Coord2D upperLeft;
    private int width;
    private int height;

    public TileRect(Coord2D upperLeft, int width, int height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    public void toPlane(int worldWidth, int worldHeight, int planeWidth, int planeHeight){
        setUpperLeft(getUpperLeft().mapFromWorldToPlane(worldWidth, worldHeight, planeWidth, planeHeight));
        setWidth(Coord2D.mapFromWorldToPlaneX(getWidth(), worldWidth, planeWidth));
        setHeight(Coord2D.mapFromWorldToPlaneY(getHeight(), worldHeight, planeHeight));
    }

    public void draw(Graphics2D g, Color color){
        Color prevColor = g.getColor();
        g.setColor(color);
        g.fillRect(
                getUpperLeft().getX(),
                getUpperLeft().getY(),
                getWidth(),
                getHeight()
        );
        g.setColor(prevColor);
    }
    public Coord2D getUpperLeft() {
        return upperLeft;
    }

    public void setUpperLeft(Coord2D upperLeft) {
        this.upperLeft = upperLeft;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
