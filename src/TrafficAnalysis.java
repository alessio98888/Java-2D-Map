
import java.awt.*;
import java.util.HashMap;

public interface TrafficAnalysis {
    HashMap<Coord2D, Integer> analyzeTraffic();
    Color getTrafficColor(Coord2D tile);
}
