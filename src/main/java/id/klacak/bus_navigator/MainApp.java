package id.klacak.bus_navigator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        TransitNetwork network = TransitNetworkLoader.load("id/klacak/bus_navigator/transit_network.xml");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/id/klacak/bus_navigator/route-finder-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Bus Navigator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}