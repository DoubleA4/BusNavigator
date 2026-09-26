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

        NodeList routeNodes = document.getElementsByTagName("route");
        for (int i = 0; i < routeNodes.getLength(); i++) {
            Element routeElement = (Element) routeNodes.item(i);

            String shortName = routeElement.getAttribute("shortName");
            String longName = routeElement.getAttribute("longName");
            String color = routeElement.getAttribute("color");
            String textColor = routeElement.getAttribute("textColor");

            Route route = new Route(shortName, longName, color, textColor);
            routesByShortName.put(shortName, route);
            network.addRoute(route);

            NodeList stopNodes = routeElement.getElementsByTagName("stop");
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

        return network;
    }

    public static void main(String[] args) throws Exception {
        TransitNetwork network = load("transit_network.xml");

        for (Route route : network.getRoutes()) {
            System.out.println(route.getShortName() + " - " + route.getLongName());
            for (Stop stop : route.getStops()) {
                System.out.println("  " + stop.getName());
            }
        }
    }
}
