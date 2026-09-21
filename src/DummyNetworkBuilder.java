import java.util.*;

public class DummyNetworkBuilder {

    public static TransitNetwork createDummyNetwork() {
        // 1. Create Dummy Stops
        Stop s1 = new Stop("Central Station");
        Stop s2 = new Stop("City Park");
        Stop s3 = new Stop("Market Square");
        Stop s4 = new Stop("Airport");

        Map<String, Stop> stopsById = Map.of(
                s1.id(), s1,
                s2.id(), s2,
                s3.id(), s3,
                s4.id(), s4
        );

        // 2. Create Dummy Routes
        Route r101 = new Route("R101", "101", "Red Line Express", "000000", "FFFFFF");
        Route r202 = new Route("R202", "202", "Blue Line Local", "FFFFFF", "000000");

        Map<String, Route> routesById = Map.of(
                r101.id(), r101,
                r202.id(), r202
        );

        // 3. Define Route Sequences (routeToStops)
        Map<Route, List<Stop>> routeToStops = Map.of(
                r101, List.of(s1, s2, s3), // Red Line: Central -> Park -> Market
                r202, List.of(s2, s4)      // Blue Line: Park -> Airport
        );

        // 4. Build Stop-to-Routes Map (stopToRoutes)
        Map<Stop, List<Route>> stopToRoutes = new HashMap<>();
        for (var entry : routeToStops.entrySet()) {
            Route route = entry.getKey();
            for (Stop stop : entry.getValue()) {
                stopToRoutes
                        .computeIfAbsent(stop, k -> new ArrayList<>())
                        .add(route);
            }
        }

        return new TransitNetwork(stopsById, stopToRoutes, routeToStops);
    }
}