import id.klacak.bus_navigator.*;

void main() throws Exception {
    TransitNetwork network = TransitNetworkLoader.load("id/klacak/bus_navigator/transit_network.xml");

    Stop start = network.findStop("Pens 1 a");
    Stop end = network.findStop("Pelabuhan Tanjung Perak");
    List<Leg> journey = RouteFinder.findRoute(start, end);

    if (journey.isEmpty()) {
        IO.println("No route found between specified stops.");
    } else {
        IO.println("--- Journey Itinerary ---");
        for (int i = 0; i < journey.size(); i++) {
            System.out.printf("Step %d:%n%s%n", i + 1, journey.get(i));
        }
    }
}
