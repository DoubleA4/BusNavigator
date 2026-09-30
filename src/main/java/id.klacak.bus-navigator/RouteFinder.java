import java.util.*;

public class RouteFinder {
    public static List<Leg> findRoute(Stop startStop, Stop endStop) {
        if (startStop == null || endStop == null) {
            return Collections.emptyList();
        }

        Queue<QueueItem> queue = new ArrayDeque<>();
        queue.add(new QueueItem(startStop, List.of()));

        Set<Stop> visitedStops = new HashSet<>(Set.of(startStop));
        Set<Route> visitedRoutes = new HashSet<>();

        while (!queue.isEmpty()) {
            QueueItem current = queue.poll();
            Stop currentStop = current.currentStop();

            List<Route> routes = currentStop.getRoutes();
            for (Route route : routes) {
                if (visitedRoutes.contains(route)) continue;
                visitedRoutes.add(route);

                List<Stop> stops = route.getStops();
                int boardIdx = route.stopPosition(currentStop);
                if (boardIdx == -1) continue;

                int totalStops = stops.size();

                // Scan downstream with circular wrap-around (modulo)
                for (int offset = 1; offset < totalStops; offset++) {
                    int nextIdx = (boardIdx + offset) % totalStops;
                    Stop nextStop = stops.get(nextIdx);

                    List<Leg> newPath = new ArrayList<>(current.path());
                    newPath.add(new Leg(currentStop, route, nextStop));

                    if (nextStop.equals(endStop)) {
                        return newPath;
                    }

                    if (!visitedStops.contains(nextStop)) {
                        visitedStops.add(nextStop);
                        queue.add(new QueueItem(nextStop, newPath));
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    private record QueueItem(Stop currentStop, List<Leg> path) {}
}