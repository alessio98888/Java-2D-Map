import java.awt.*;
import java.util.ArrayList;

public class TilesManager {
    private int numberOfTiles;
    private int tileWidth;
    private int tileHeight;
    private int worldWidth;
    private int worldHeight;

    private ArrayList<Coord2D> tiles;

    public int getWorldWidth() {
        return worldWidth;
    }

    public void setWorldWidth(int worldWidth) {
        this.worldWidth = worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public void setWorldHeight(int worldHeight) {
        this.worldHeight = worldHeight;
    }

    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    public void setNumberOfTiles(int numberOfTiles) {
        this.numberOfTiles = numberOfTiles;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public ArrayList<Coord2D> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Coord2D> tiles) {
        this.tiles = tiles;
    }


    public void generateTiles(int numberOfTiles, int worldWidth, int worldHeight){

        ArrayList<Coord2D> tilesNonMappedUpperLeftCoord = new ArrayList<>();

        // DRAW GRID
        int stepVertical = worldWidth / numberOfTiles;
        int stepHorizontal = worldHeight / numberOfTiles;

        int currentX = stepVertical;
        int currentY = stepHorizontal;
        for(int i = 0; i < numberOfTiles; i++){
            Coord2D tileCoord = new Coord2D();
            tileCoord.setX(currentX);
            tileCoord.setY(currentY);
            tilesNonMappedUpperLeftCoord.add(tileCoord);
            currentX += stepVertical;
            currentY += stepHorizontal;
        }

        setTiles(tilesNonMappedUpperLeftCoord);
        setTileWidth(stepVertical);
        setTileHeight(stepHorizontal);
        setNumberOfTiles(numberOfTiles);
        setWorldHeight(worldHeight);
        setWorldWidth(worldWidth);

    }

    public void drawGridToPlane(Graphics2D g, int planeWidth, int planeHeight){
        for(Coord2D tile : tiles){
            Coord2D mappedToPlaneTile = tile.mapFromWorldToPlane(worldWidth, worldHeight, planeWidth, planeHeight);
            int mappedX = mappedToPlaneTile.getX();
            int mappedY = mappedToPlaneTile.getY();
            g.drawLine(mappedX,0, mappedX, planeHeight);
            g.drawLine(0, mappedY, planeWidth, mappedY);
        }
    }

    public void fillCorrespondingTileWithColor(Graphics2D g, Coord2D worldCoord, int planeWidth, int planeHeight, Color color){
        drawTileToPlane(g, getCorrespondingTile(worldCoord), planeWidth, planeHeight, color);
    }



    private void drawTileToPlane(Graphics2D g, Coord2D worldTile, int planeWidth, int planeHeight, Color color){
        TileRect tileRect = new TileRect(worldTile, getTileWidth(), getTileHeight());
        tileRect.toPlane(worldWidth, worldHeight, planeWidth, planeHeight);
        tileRect.draw(g, color);
    }

    public Coord2D getCorrespondingTile(Coord2D worldCoord){
        Coord2D correspondingTile = new Coord2D();

        correspondingTile.setX(
                Math.floorDiv(worldCoord.getX(), getTileWidth())
                        *
                        getTileWidth()
        );

        correspondingTile.setY(
                Math.floorDiv(worldCoord.getY(), getTileHeight())
                        *
                        getTileHeight()
        );
        return correspondingTile;
    }


}
