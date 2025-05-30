import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.ConditionalFeature.FXML;

public class Starter extends Application {

    public static void main(String[] args) {
        launch();
    }

public void start(Stage stage) throws IOException {
    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/to-do-ui.fxml"))));
    stage.show();
}
}
