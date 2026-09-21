public record Leg(
        Stop boardStop,
        Route route,
        Stop alightStop
) {
    @Override
    public String toString() {
        return String.format("Board Route %s at %s ➔ Alight at %s",
                route, boardStop.getName(), alightStop.getName());
    }
}