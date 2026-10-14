package id.klacak.bus_navigator;

import javafx.geometry.Pos;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.SearchableComboBox;

/** Displays every stop served by a selected route. */
public class RouteStopsController {
    @FXML
    private SearchableComboBox<String> routeSelector;
    @FXML
    private Label routeCodeLabel;
    @FXML
    private Label routeNameLabel;
    @FXML
    private Label routeSummaryLabel;
    @FXML
    private VBox stopsContainer;

    private MainApp mainApp;
    private TransitNetwork network;

    /** Loads available routes and displays the first route by default. */
    @FXML
    public void initialize() {
        try {
            network = TransitNetworkLoader.load("id/klacak/bus_navigator/transit_network.xml");
            network.getRoutes().forEach(route -> routeSelector.getItems().add(route.getShortName()));
            routeSelector.setOnAction(event -> displayRoute(routeSelector.getValue()));
            if (!network.getRoutes().isEmpty()) {
                routeSelector.setValue(network.getRoutes().getFirst().getShortName());
            }
        } catch (Exception e) {
            routeSummaryLabel.setText("Transit network could not be loaded.");
        }
    }

    /** Gives this controller access to the application's view navigation. */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void goHome() {
        mainApp.showView("main-view.fxml");
    }

    private void displayRoute(String shortName) {
        if (shortName == null || network == null) {
            return;
        }

        Route route = network.findRoute(shortName);
        if (route == null) {
            return;
        }

        routeCodeLabel.setText(route.getShortName());
        routeCodeLabel.setAlignment(Pos.CENTER);
        routeCodeLabel.setMinWidth(35);
        routeCodeLabel.getStyleClass().removeAll("route-badge", "feeder-badge");
        if (route instanceof Feeder) {
            routeCodeLabel.getStyleClass().add("feeder-badge");
            routeCodeLabel.setStyle("-fx-border-color: " + route.getColor() + "; "
                + "-fx-background-color: " + route.getTextColor() + "; "
                + "-fx-text-fill: " + route.getColor() + ";");
        } else {
            routeCodeLabel.getStyleClass().add("route-badge");
            routeCodeLabel.setStyle("-fx-border-color: " + route.getColor() + "; "
                + "-fx-background-color: " + route.getColor() + "; "
                + "-fx-text-fill: " + route.getTextColor() + ";");
        }
        routeNameLabel.setText(route.getLongName());
        routeSummaryLabel.setText(route.getStops().size() + " stops along this route");
        stopsContainer.getChildren().clear();

        for (Stop stop : route.getStops()) {
            Label stopMarker = new Label("●");
            stopMarker.setStyle("-fx-text-fill: " + route.getColor() + ";");
            HBox row = new HBox(12.0, stopMarker, new Label(stop.getName()));
            row.getStyleClass().add("list-group-item");
            stopsContainer.getChildren().add(row);
        }
    }
}