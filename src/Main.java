void main() throws Exception {
    TransitNetwork network = TransitNetworkLoader.load("./transit_network.xml");

    Stop start = network.findStop("Jembatan Cinta");
    Stop end = network.findStop("Wiguna A");
    List<Leg> journey = RouteFinder.findRoute(start, end);

    if (journey.isEmpty()) {
        IO.println("No route found between specified stops.");
    } else {
        IO.println("--- Journey Itinerary ---");
        for (int i = 0; i < journey.size(); i++) {
            System.out.printf("Step %d: %s%n", i + 1, journey.get(i));
        }
    }
}
