
import java.awt.*;
import java.util.HashMap;

public interface TrafficAnalysis {
    void analyzeTraffic();
    HashMap<Coord2D, Integer> getTrafficAnalysis();
    Color getTrafficColor(Coord2D tile);
}
