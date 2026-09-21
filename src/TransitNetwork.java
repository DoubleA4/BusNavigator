import java.util.ArrayList;
import java.util.List;

public class TransitNetwork {
    private final List<Stop> stops;
    private final List<Route> routes;

    public TransitNetwork() {
        stops = new ArrayList<>();
        routes = new ArrayList<>();
    }

    public void addStop(Stop newStop) {
        stops.add(newStop);
    }

    public void addRoute(Route newRoute) {
        routes.add(newRoute);
    }

    public List<Stop> getStops() {
        return stops;
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
