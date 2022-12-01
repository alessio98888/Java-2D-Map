import java.time.LocalDateTime;

public class SpaceTimeLocation {
    private Coord2D space;
    private LocalDateTime time;

    public SpaceTimeLocation(Coord2D space, LocalDateTime time) {
        this.space = space;
        this.time = time;
    }


    public Coord2D getSpace() {
        return space;
    }

    public void setSpace(Coord2D space) {
        this.space = space;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }



}
