package donk.controller.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * JavaFX application entry point for the UI.
 * <p>
 * This class loads the main window FXML, wires the controller with the
 * application state, and shows the primary stage.
 * </p>
 */
public class Main extends Application {
    private final Donk donk = new Donk();

    /**
     * Initializes and displays the primary stage.
     *
     * @param stage the primary stage provided by the JavaFX runtime. */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDonk(donk);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error loading MainWindow FXML: " + e.getMessage());
        }
    }
}
