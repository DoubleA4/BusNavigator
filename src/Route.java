import java.util.ArrayList;
import java.util.List;

public class Route {
    private final String shortName;
    private final String longName;
    private final String color;
    private final String textColor;
    private final List<Stop> stop;

    public Route(String shortName, String longName, String color, String textColor) {
        this.shortName = shortName;
        this.longName = longName;
        this.color = color;
        this.textColor = textColor;
        this.stop = new ArrayList<>();
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getColor() {
        return color;
    }

    public String getTextColor() {
        return textColor;
    }

    public List<Stop> getStop() {
        return stop;
    }

    public void addStop(Stop newStop) {
        stop.add(newStop);
        newStop.addRoute(this);
    }

    public int stopPosition(Stop targetStop) {
        return stop.indexOf(targetStop);
    }

    @Override
    public String toString() {
        return "(" + shortName + ")" + longName;
    }
}
