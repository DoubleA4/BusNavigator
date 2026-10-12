package id.klacak.bus_navigator;

/**
 * A single ride on a route from one stop to another.
 *
 * @param boardStop stop where the passenger boards
 * @param route route used for the ride
 * @param alightStop stop where the passenger leaves the route
 */
public record Leg(
        Stop boardStop,
        Route route,
        Stop alightStop
) {
    /**
     * Formats this ride as a short Indonesian-language itinerary description.
     *
     * @return formatted boarding stop, route, and alighting stop
     */
    @Override
    public String toString() {
        return "Dari Halte " + boardStop.getName() + "\n" +
                "Naik Rute" + "\n" +
                route.getNameFormatted() +
                "Turun di Halte " + alightStop.getName() + "\n";
    }
}