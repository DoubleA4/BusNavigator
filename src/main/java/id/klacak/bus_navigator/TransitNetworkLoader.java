package id.klacak.bus_navigator;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TransitNetworkLoader {
    public static TransitNetwork load(String xmlPath) throws Exception {
        // Strip leading "./" if present for classpath lookup
        String resourcePath = xmlPath.startsWith("./") ? xmlPath.substring(2) : xmlPath;

        InputStream inputStream = TransitNetworkLoader.class
                .getClassLoader()
                .getResourceAsStream(resourcePath);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found on classpath: " + resourcePath);
        }

        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(inputStream);

        document.getDocumentElement().normalize();

        TransitNetwork network = new TransitNetwork();
        Map<String, Route> routesByShortName = new HashMap<>();
        Map<String, Stop> stopsByName = new HashMap<>();

        // Load standard <route> elements
        parseRouteElements(document.getElementsByTagName("route"), false, routesByShortName, stopsByName, network);

        // Load <feeder> elements
        parseRouteElements(document.getElementsByTagName("feeder"), true, routesByShortName, stopsByName, network);

        return network;
    }

    private static void parseRouteElements(NodeList elements, boolean isFeeder,
                                           Map<String, Route> routesByShortName, Map<String, Stop> stopsByName, TransitNetwork network) {
        for (int i = 0; i < elements.getLength(); i++) {
            Element element = (Element) elements.item(i);

            String shortName = element.getAttribute("shortName");
            String longName = element.getAttribute("longName");
            String color = element.getAttribute("color");
            String textColor = element.getAttribute("textColor");

            // Instantiate Feeder if it's a feeder element, otherwise Route
            Route route;
            if (isFeeder) {
                route = new Feeder(shortName, longName, color, textColor);
            } else {
                route = new Route(shortName, longName, color, textColor);
            }

            routesByShortName.put(shortName, route);
            network.addRoute(route);

            NodeList stopNodes = element.getElementsByTagName("stop");
            for (int j = 0; j < stopNodes.getLength(); j++) {
                String stopName = stopNodes.item(j).getTextContent().trim();
                if (stopName.isEmpty()) {
                    continue;
                }

                Stop stop = stopsByName.get(stopName);
                if (stop == null) {
                    stop = new Stop(stopName);
                    stopsByName.put(stopName, stop);
                    if (!network.getStops().contains(stop)) {
                        network.addStop(stop);
                    }
                }

                route.addStop(stop);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        TransitNetwork network = load("transit_network.xml");

        for (Route route : network.getRoutes()) {
            // instanceof check if you want to distinguish them in output,
            // otherwise polymorphism handles them seamlessly[cite: 1]
            String type = (route instanceof Feeder) ? "[Feeder]" : "[Route]";
            System.out.println(type + " " + route.getShortName() + " - " + route.getLongName());

            for (Stop stop : route.getStops()) {
                System.out.println("  " + stop.getName());
            }
        }
    }
}