package id.klacak.bus_navigator;

import java.util.ArrayList;
import java.util.List;

/** A transit stop and the routes that serve it. */
public class Stop {
    private final String name;
    private final List<Route> routes;

    /**
     * Creates a stop with the given name.
     *
     * @param name stop name
     */
    public Stop(String name) {
        this.name = name;
        this.routes = new ArrayList<>();
    }

    /**
     * Gets the name of this stop.
     *
     * @return the stop name
     */
    public String getName() {
        return name;
    }

    /**
     * Formats the stop name followed by colored codes for its serving routes.
     *
     * @return formatted stop name and route codes
     */
    public String getNameFormatted() {
        StringBuilder str = new StringBuilder();
        str.append(getName()).append(" ");
        for (Route route : routes) {
            String bgColor = AnsiColor.bgHex(route.getColor());
            String fgColor = AnsiColor.fgHex(route.getTextColor());
            str.append(bgColor).append(fgColor).append(" ").append(route.getShortName()).append(" ").append(AnsiColor.reset()).append(" ");
        }
        return str.toString();
    }

    /**
     * Gets the routes that serve this stop.
     *
     * @return the mutable list of serving routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * Adds a serving route if it is not already present.
     *
     * @param newRoute route to associate with this stop
     */
    public void addRoute(Route newRoute) {
        if (!routes.contains(newRoute)) {
            routes.add(newRoute);
        }
    }

    /** @return a text description of this stop and its serving routes */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Halte ").append(name).append("\n");
        str.append("Melayani rute:\n");
        for (Route route : routes) {
            String bgColor = AnsiColor.bgHex(route.getColor());
            String fgColor = AnsiColor.fgHex(route.getTextColor());
            str.append(bgColor).append(fgColor).append(" ").append(route.getShortName()).append(" ").append(AnsiColor.reset()).append(" ").append(route.getLongName()).append("\n");
        }
        return str.toString();
    }
}
