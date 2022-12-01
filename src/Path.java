import java.util.ArrayList;

public class Path {


    private ArrayList<SpaceTimeLocation> path;
    public Path() {
        path = new ArrayList<>();
    }
    public Path(ArrayList<SpaceTimeLocation> path) {
        this.path = path;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(SpaceTimeLocation l : getPath()){
            s.append(l.getSpace());
            s.append(" (");
            s.append(l.getTime().toString());
            s.append("); ");
        }
        return s.toString();
    }
    public void addSpaceTimeLocation(SpaceTimeLocation toAdd){
        this.path.add(toAdd);
    }

    public ArrayList<SpaceTimeLocation> getPath() {
        return path;
    }

    public void setPath(ArrayList<SpaceTimeLocation> path) {
        this.path = path;
    }



}
