package id.klacak.bus_navigator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** In-memory collection of the stops and routes in a transit system. */
public class TransitNetwork {
    private final List<Stop> stops;
    private final List<Route> routes;

    /** Creates an empty transit network. */
    public TransitNetwork() {
        stops = new ArrayList<>();
        routes = new ArrayList<>();
    }

    /**
     * Adds a stop to the network.
     *
     * @param newStop stop to add
     */
    public void addStop(Stop newStop) {
        stops.add(newStop);
    }

    /**
     * Adds a route to the network.
     *
     * @param newRoute route to add
     */
    public void addRoute(Route newRoute) {
        routes.add(newRoute);
    }

    /**
     * Finds a stop by name, ignoring case.
     *
     * @param stopName name to search for
     * @return the matching stop, or {@code null} when no stop matches
     */
    public Stop findStop(String stopName) {
        Optional<Stop> result = stops.stream()
                .filter(stop -> stopName.equalsIgnoreCase(stop.getName()))
                .findFirst();

        return result.orElse(null);
    }

    /**
     * Finds a route by its short code, ignoring case.
     *
     * @param routeName route code to search for
     * @return the matching route, or {@code null} when no route matches
     */
    public Route findRoute(String routeName) {
        Optional<Route> result = routes.stream()
                .filter(route -> routeName.equalsIgnoreCase(route.getShortName()))
                .findFirst();

        return result.orElse(null);
    }

    /**
     * Gets all stops registered in this network.
     *
     * @return the mutable list of stops
     */
    public List<Stop> getStops() {
        return stops;
    }

    /**
     * Gets all routes registered in this network.
     *
     * @return the mutable list of routes
     */
    public List<Route> getRoutes() {
        return routes;
    }
}
