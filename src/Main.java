import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Load dummy network
        TransitNetwork network = DummyNetworkBuilder.createDummyNetwork();

        RouteFinder router = new RouteFinder(network);

        // Find route from Central Station (S1) to Airport (S4)
        List<Leg> journey = router.findRoute("S4", "S1");

        if (journey.isEmpty()) {
            System.out.println("No route found between specified stops.");
        } else {
            System.out.println("--- Journey Itinerary ---");
            for (int i = 0; i < journey.size(); i++) {
                System.out.printf("Step %d: %s%n", i + 1, journey.get(i));
            }
        }
    }
}