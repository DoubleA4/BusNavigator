package id.klacak.bus_navigator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

/** JavaFX application entry point for the Bus Navigator route-finder interface. */
public class MainApp extends Application {
    /** Creates the Bus Navigator JavaFX application. */
    public MainApp() {
    }

    /** Loads the application's custom fonts before creating the interface. */
    @Override
    public void init() throws Exception {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Sora-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Sora-Bold.ttf"), 12);
    }

    /**
     * Loads the transit data and displays the route-finder scene.
     *
     * @param stage primary application window
     * @throws Exception if the transit data or FXML view cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        TransitNetwork network = TransitNetworkLoader.load("id/klacak/bus_navigator/transit_network.xml");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/id/klacak/bus_navigator/route-finder-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 500, 900);

        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        scene.getStylesheets().add("id/klacak/bus_navigator/stylesheet.css");

        stage.setTitle("Bus Navigator");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments passed to JavaFX
     */
    public static void main(String[] args) {
        launch(args);
    }
}