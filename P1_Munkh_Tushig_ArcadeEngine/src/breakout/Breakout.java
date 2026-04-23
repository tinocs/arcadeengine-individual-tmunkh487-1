package breakout;

import javafx.application.Application;
import javafx.stage.Stage;

public class Breakout extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BallWorld ballWorld = new BallWorld(stage);
    }
}
