public class Coord2D {
    private int x;
    private int y;
    public Coord2D() {
    }

    public Coord2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Coord2D mapFromWorldToPlane(int worldWidth, int worldHeight, int planeWidth, int planeHeight){

        Coord2D ret = new Coord2D();
        ret.setX(Utils.mapRangeToRange(getX(), 0, worldWidth, 0, planeWidth));

        ret.setY(Utils.mapRangeToRange(getY(), 0, worldHeight, 0, planeHeight));

        return ret;
    }

    public static int mapFromWorldToPlaneX(int x, int worldWidth, int planeWidth){
        return Utils.mapRangeToRange(x, 0, worldWidth, 0, planeWidth);
    }

    public static int mapFromWorldToPlaneY(int y, int worldHeight, int planeHeight){
        return Utils.mapRangeToRange(y, 0, worldHeight, 0, planeHeight);
    }

}
