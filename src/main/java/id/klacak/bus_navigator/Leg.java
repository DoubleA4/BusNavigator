package id.klacak.bus_navigator;

public record Leg(
        Stop boardStop,
        Route route,
        Stop alightStop
) {
    @Override
    public String toString() {
        return "Dari Halte " + boardStop.getName() + "\n" +
                "Naik Rute" + "\n" +
                route.getNameFormatted() +
                "Turun di Halte " + alightStop.getName() + "\n";
    }
}