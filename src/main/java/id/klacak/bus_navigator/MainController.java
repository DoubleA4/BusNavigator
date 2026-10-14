package id.klacak.bus_navigator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/** Coordinates the main view and its navigation actions. */
public class MainController {
    @FXML
    private Label networkSummary;

    private MainApp mainApp;

    /** Loads the network summary shown on the main view. */
    @FXML
    public void initialize() {
        try {
            TransitNetwork network = TransitNetworkLoader.load(
                    "id/klacak/bus_navigator/transit_network.xml");
                networkSummary.setText(network.getRoutes().size() + " routes and feeder services");
        } catch (Exception e) {
            networkSummary.setText("Transit network could not be loaded.");
        }
    }

    /** Gives this controller access to the application's view navigation. */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void showRouteFinder() {
        mainApp.showView("route-finder-view.fxml");
    }

    @FXML
    private void showRouteStops() {
        mainApp.showView("route-stops-view.fxml");
    }

    @FXML
    private void showStopRoutes() {
        mainApp.showView("stop-routes-view.fxml");
    }
}