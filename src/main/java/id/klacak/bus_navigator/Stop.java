package id.klacak.bus_navigator;

import java.util.ArrayList;
import java.util.List;

public class Stop {
    private final String name;
    private final List<Route> routes;

    public Stop(String name) {
        this.name = name;
        this.routes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

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

    public List<Route> getRoutes() {
        return routes;
    }

    public void addRoute(Route newRoute) {
        if (!routes.contains(newRoute)) {
            routes.add(newRoute);
        }
    }

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
