void main() throws Exception {
    TransitNetwork network = TransitNetworkLoader.load("./transit_network.xml");

    System.out.print(network.findRoute("R2"));
}
