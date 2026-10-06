package id.klacak.bus_navigator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.List;

public class RouteFinderController {

    @FXML private TextField startField;
    @FXML private TextField endField;
    @FXML private ListView<String> itineraryListView;
    @FXML private Label statusBadge;

    private TransitNetwork network;

    @FXML
    public void initialize() {
        try {
            network = TransitNetworkLoader.load("id/klacak/bus_navigator/transit_network.xml");
            updateBadge("Network Loaded", "lbl-success");
        } catch (Exception e) {
            updateBadge("Network Load Failed", "lbl-danger");
            itineraryListView.getItems().add("Error loading transit network: " + e.getMessage());
        }
    }

    @FXML
    private void handleFindRoute() {
        itineraryListView.getItems().clear();

        String startName = startField.getText().trim();
        String endName = endField.getText().trim();

        if (startName.isEmpty() || endName.isEmpty()) {
            updateBadge("Missing Inputs", "lbl-warning");
            itineraryListView.getItems().add("Please enter both start and end stops.");
            return;
        }

        if (network == null) {
            updateBadge("Network Unavailable", "lbl-danger");
            return;
        }

        Stop start = network.findStop(startName);
        Stop end = network.findStop(endName);

        if (start == null || end == null) {
            updateBadge("Stops Not Found", "lbl-warning");
            itineraryListView.getItems().add("Could not find one or both specified stops.");
            return;
        }

        List<Leg> journey = RouteFinder.findRoute(start, end);

        if (journey == null || journey.isEmpty()) {
            updateBadge("No Route Found", "lbl-danger");
            itineraryListView.getItems().add("No route found between specified stops.");
        } else {
            updateBadge(journey.size() + " Steps Found", "lbl-success");
            for (int i = 0; i < journey.size(); i++) {
                itineraryListView.getItems().add(String.format("Step %d:", i + 1));
                itineraryListView.getItems().add("Naik rute " + journey.get(i).route().getShortName() + " (" + journey.get(i).route().getLongName() + ")");
                itineraryListView.getItems().add("Dari halte " + journey.get(i).boardStop().getName());
                itineraryListView.getItems().add("Turun di halte " + journey.get(i).alightStop().getName());
            }
        }
    }

    private void updateBadge(String text, String styleClass) {
        statusBadge.setText(text);
        statusBadge.getStyleClass().removeAll("lbl-default", "lbl-primary", "lbl-success", "lbl-info", "lbl-warning", "lbl-danger");
        statusBadge.getStyleClass().add(styleClass);
    }
}