package id.klacak.bus_navigator;

import javafx.geometry.Pos;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.SearchableComboBox;

/** Displays the routes and feeder services serving a selected stop. */
public class StopRoutesController {
    @FXML
    private SearchableComboBox<String> stopSelector;
    @FXML
    private Label stopNameLabel;
    @FXML
    private Label stopSummaryLabel;
    @FXML
    private VBox routesContainer;

    private MainApp mainApp;
    private TransitNetwork network;

    /** Loads available stops and displays the first stop by default. */
    @FXML
    public void initialize() {
        try {
            network = TransitNetworkLoader.load("id/klacak/bus_navigator/transit_network.xml");
            network.getStops().forEach(stop -> stopSelector.getItems().add(stop.getName()));
            stopSelector.setOnAction(event -> displayStop(stopSelector.getValue()));
            if (!network.getStops().isEmpty()) {
                String firstStopName = network.getStops().getFirst().getName();
                stopSelector.setValue(firstStopName);
                displayStop(firstStopName);
            }
        } catch (Exception e) {
            stopSummaryLabel.setText("Transit network could not be loaded.");
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

    private void displayStop(String stopName) {
        if (stopName == null || network == null) {
            return;
        }

        Stop stop = network.findStop(stopName);
        if (stop == null) {
            return;
        }

        stopNameLabel.setText(stop.getName());
        stopSummaryLabel.setText(stop.getRoutes().size() + " services serve this stop");
        routesContainer.getChildren().clear();

        for (Route route : stop.getRoutes()) {
            Label routeCode = new Label(route.getShortName());
            routeCode.setAlignment(Pos.CENTER);
            routeCode.setMinWidth(35);
            if (route instanceof Feeder) {
                routeCode.getStyleClass().add("feeder-badge");
                routeCode.setStyle("-fx-border-color: " + route.getColor() + "; "
                        + "-fx-background-color: " + route.getTextColor() + "; "
                        + "-fx-text-fill: " + route.getColor() + ";");
            } else {
                routeCode.getStyleClass().add("route-badge");
                routeCode.setStyle("-fx-border-color: " + route.getColor() + "; "
                        + "-fx-background-color: " + route.getColor() + "; "
                        + "-fx-text-fill: " + route.getTextColor() + ";");
            }

            Label routeName = new Label(route.getLongName());
            routeName.setWrapText(true);
            routeName.getStyleClass().add("h4");

            HBox heading = new HBox(10.0, routeCode, routeName);
            heading.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

            Label serviceType = new Label(route instanceof Feeder ? "Feeder service" : "Bus route");
            serviceType.getStyleClass().add("text-muted");

            VBox body = new VBox(6.0, heading, serviceType);
            body.getStyleClass().add("panel-body");

            VBox card = new VBox(body);
            card.getStyleClass().add("panel");
            routesContainer.getChildren().add(card);
        }
    }
}