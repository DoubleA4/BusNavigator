package id.klacak.bus_navigator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import org.controlsfx.control.SearchableComboBox;

import java.util.List;

/** Coordinates the route-finder view with transit data and itinerary results. */
public class RouteFinderController {

    /** Creates a controller for the FXML route-finder view. */
    public RouteFinderController() {
    }

    /** Container populated with the visual rows of the selected itinerary. */
    public VBox itineraryContainer;
    @FXML
    private SearchableComboBox<String> startField;
    @FXML
    private SearchableComboBox<String> endField;
    @FXML
    private ListView<String> itineraryListView;

    private TransitNetwork network;

    /** Loads the transit network and populates both stop selectors. */
    @FXML
    public void initialize() {
        try {
            network = TransitNetworkLoader.load("id/klacak/bus_navigator/transit_network.xml");
            network.getStops().forEach(stop -> {
                startField.getItems().add(stop.getName());
                endField.getItems().add(stop.getName());
            });
        } catch (Exception e) {
            itineraryListView.getItems().add("Error loading transit network: " + e.getMessage());
        }
    }

    /** Finds and displays a journey for the currently selected stops. */
    @FXML
    private void handleFindRoute() {
        String startName = startField.getValue();
        String endName = endField.getValue();


        if (network == null) {
            return;
        }

        Stop start = network.findStop(startName);
        Stop end = network.findStop(endName);

        List<Leg> journey = RouteFinder.findRoute(start, end);

        displayRoute(journey);
    }

    /**
     * Replaces the displayed itinerary with rows for the supplied ride legs.
     *
     * @param steps ordered ride legs to display
     */
    @FXML
    public void displayRoute(List<Leg> steps) {
        itineraryContainer.getChildren().clear();
        for (Leg step : steps) {
            Leg previous;
            if (steps.size() > 1) {
                if (steps.getFirst() == step) {
                    itineraryContainer.getChildren().add(new LegRowNode(step));
                } else if (steps.getLast() == step) {
                    previous = steps.get(steps.indexOf(step) - 1);
                    itineraryContainer.getChildren().add(new LegRowNode(step, 2, previous));
                } else {
                    previous = steps.get(steps.indexOf(step) - 1);
                    itineraryContainer.getChildren().add(new LegRowNode(step, 1, previous));
                }
            } else {
                itineraryContainer.getChildren().add(new LegRowNode(step, -1));
            }
        }
    }
}