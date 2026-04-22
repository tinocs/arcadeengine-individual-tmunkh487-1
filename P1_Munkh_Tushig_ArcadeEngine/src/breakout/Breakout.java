package breakout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Breakout extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Breakout");

        BorderPane  root = new BorderPane();
        BallWorld ballWorld = new BallWorld();
        root.setCenter(ballWorld);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        ballWorld.start();
        stage.show();
    }
}
