import java.util.ArrayList;
import java.util.List;

public class Route {
    private final String shortName;
    private final String longName;
    private final String color;
    private final String textColor;
    private final List<Stop> stops;

    public Route(String shortName, String longName, String color, String textColor) {
        this.shortName = shortName;
        this.longName = longName;
        this.color = color;
        this.textColor = textColor;
        this.stops = new ArrayList<>();
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getColor() { return color; }

    public String getTextColor() {
        return textColor;
    }

    public String getNameFormatted() {
        String bgColor = AnsiColor.bgHex(getColor());
        String fgColor = AnsiColor.fgHex(getTextColor());
        return bgColor + fgColor + " " + getShortName() + " " + AnsiColor.reset() + " " + getLongName() + "\n";
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void addStop(Stop newStop) {
        stops.add(newStop);
        newStop.addRoute(this);
    }

    public int stopPosition(Stop targetStop) {
        return stops.indexOf(targetStop);
    }

    @Override
    public String toString() {
        String bgColor = AnsiColor.bgHex(getColor());
        String fgColor = AnsiColor.fgHex(getTextColor());
        StringBuilder str = new StringBuilder();
        str.append(bgColor).append(fgColor).append(" ").append(getShortName()).append(" ").append(AnsiColor.reset()).append(" ").append(getLongName()).append("\n");
        for (Stop stop : stops) {
            if (stop == stops.getLast()) {
                str.append("● "). append(stop.getNameFormatted());
            } else {
                str.append("● "). append(stop.getNameFormatted()).append("\n").append("│\n");
            }
        }
        return str.toString();
    }
}
