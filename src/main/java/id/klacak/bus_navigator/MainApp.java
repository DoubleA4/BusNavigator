package id.klacak.bus_navigator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

/** JavaFX application entry point for the Bus Navigator route-finder interface. */
public class MainApp extends Application {
    private Stage primaryStage;

    /** Creates the Bus Navigator JavaFX application. */
    public MainApp() {
    }

    /** Loads the application's custom fonts before creating the interface. */
    @Override
    public void init() throws Exception {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Sora-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Sora-Bold.ttf"), 12);
    }

    /** Opens the main view in the primary application window. */
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        stage.setTitle("Bus Navigator");
        showView("main-view.fxml");
        stage.show();
    }

    /** Loads a view, connects its controller, and displays it in the primary stage. */
    public void showView(String viewName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/id/klacak/bus_navigator/" + viewName));
            Parent root = loader.load();
            Object controller = loader.getController();

            if (controller instanceof MainController mainController) {
                mainController.setMainApp(this);
            } else if (controller instanceof RouteFinderController routeFinderController) {
                routeFinderController.setMainApp(this);
            } else if (controller instanceof RouteStopsController routeStopsController) {
                routeStopsController.setMainApp(this);
            } else if (controller instanceof StopRoutesController stopRoutesController) {
                stopRoutesController.setMainApp(this);
            }

            Scene scene = new Scene(root, 500, 900);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
            scene.getStylesheets().add("id/klacak/bus_navigator/stylesheet.css");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load view: " + viewName, e);
        }
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