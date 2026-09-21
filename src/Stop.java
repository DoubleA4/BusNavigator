import java.util.ArrayList;
import java.util.List;

public class Stop {
    private final String name;
    private final List<Route> route;

    public Stop(String name) {
        this.name = name;
        this.route = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Route> getRoute() {
        return route;
    }

    public void addRoute(Route newRoute) {
        if (!route.contains(newRoute)) {
            route.add(newRoute);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
