import id.klacak.bus_navigator.TransitNetwork;
import id.klacak.bus_navigator.TransitNetworkLoader;

/** Loads the sample network and prints the stop selected in this example. */
void main() throws Exception {
    TransitNetwork network = TransitNetworkLoader.load("id/klacak/bus_navigator/transit_network.xml");

    System.out.print(network.findStop("ITS"));
}
