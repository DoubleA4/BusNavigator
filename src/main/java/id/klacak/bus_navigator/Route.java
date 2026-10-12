package id.klacak.bus_navigator;

import java.util.ArrayList;
import java.util.List;

/** A transit route with an ordered, circular sequence of stops. */
public class Route {
    private final String shortName;
    private final String longName;
    private final String color;
    private final String textColor;
    private final List<Stop> stops;

    /**
     * Creates a route with its identifying names and display colors.
     *
     * @param shortName route code
     * @param longName descriptive route name
     * @param color route background color in hexadecimal format
     * @param textColor route text color in hexadecimal format
     */
    public Route(String shortName, String longName, String color, String textColor) {
        this.shortName = shortName;
        this.longName = longName;
        this.color = color;
        this.textColor = textColor;
        this.stops = new ArrayList<>();
    }

    /**
     * Gets the short code identifying this route.
     *
     * @return the route code
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Gets the full descriptive name of this route.
     *
     * @return the descriptive route name
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Gets the background color used to display this route.
     *
     * @return the route background color in hexadecimal format
     */
    public String getColor() { return color; }

    /**
     * Gets the text color used to display this route.
     *
     * @return the route text color in hexadecimal format
     */
    public String getTextColor() {
        return textColor;
    }

    /**
     * Formats the route code and name with ANSI terminal colors.
     *
     * @return colored route code and name
     */
    public String getNameFormatted() {
        String bgColor = AnsiColor.bgHex(getColor());
        String fgColor = AnsiColor.fgHex(getTextColor());
        return bgColor + fgColor + " " + getShortName() + " " + AnsiColor.reset() + " " + getLongName() + "\n";
    }

    /**
     * Gets the stops associated with this route, in travel order.
     *
     * @return the mutable list of stops
     */
    public List<Stop> getStops() {
        return stops;
    }

    /**
     * Appends a stop to the route and associates this route with that stop.
     *
     * @param newStop stop to append
     */
    public void addStop(Stop newStop) {
        stops.add(newStop);
        newStop.addRoute(this);
    }

    /**
     * Finds the first position of a stop in this route.
     *
     * @param targetStop stop to locate
     * @return zero-based position, or {@code -1} if the stop is not on this route
     */
    public int stopPosition(Stop targetStop) {
        return stops.indexOf(targetStop);
    }

    /** @return a text map of this route and its stops in travel order */
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
