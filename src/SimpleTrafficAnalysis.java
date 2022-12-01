import java.awt.*;
import java.util.Collections;
import java.util.HashMap;

public class SimpleTrafficAnalysis implements TrafficAnalysis{
    private TilesManager tilesManager;


    private HashMap<Coord2D, Integer> tilesTraffic;

    private int maxTraffic;
    public SimpleTrafficAnalysis(TilesManager tilesManager) {
        this.tilesManager = tilesManager;
    }

    @Override
    public Color getTrafficColor(Coord2D tile){
        int tileKTraffic = tilesTraffic.get(tile);
        return ColorUtilities.transitionOfHueRange(tileKTraffic/(float) maxTraffic, 120, 0);
    }

    @Override
    public HashMap<Coord2D, Integer> getTrafficAnalysis() {
        return tilesTraffic;
    }
    @Override
    public void analyzeTraffic(){
        tilesTraffic = new HashMap<>();
        for(SpaceTimeLocation l : Main.p.getPath()){

            Coord2D tile = tilesManager.getCorrespondingTile(l.getSpace());

            tilesTraffic.put(tile, tilesTraffic.getOrDefault(tile, 0) + 1);
        }
        maxTraffic = Collections.max(tilesTraffic.values());
    }

    public HashMap<Coord2D, Integer> getTilesTraffic() {
        return tilesTraffic;
    }

}
