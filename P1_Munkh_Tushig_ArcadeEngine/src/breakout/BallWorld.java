package breakout;

import engine.World;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BallWorld extends World {

    private Score score;
    private Stage stage;
    private int numBricks = 100;
    int level;

    public BallWorld(Stage stage) {
        this.stage = stage;
        level = 1;
        setHeight(600);
        setWidth(800);
        setPrefHeight(600);
        setPrefWidth(800);
    }

    @Override
    public void act(long now) {
        if (numBricks == 0) {
            level++;
            if (level == 3) {
                setTitleScreen();
                level = 1;
            }
            else {
                getChildren().clear();
                setLevel();
            }
        }
    }

    @Override
    public void onDimensionsInitialized() {
        setTitleScreen();
    }

    public void setLevel() {
        numBricks = 0;

        Ball ball = new Ball();
        getChildren().add(ball);

        Paddle paddle = new Paddle();
        getChildren().add(paddle);

        paddle.setX(getWidth() / 2 - paddle.getWidth() / 2);
        paddle.setY(getHeight() - paddle.getHeight() * 4);

        ball.setX(getWidth() / 2 -  ball.getWidth() / 2);
        ball.setY(paddle.getY() - ball.getHeight() / 2);

        setOnMouseMoved(e -> {
            paddle.setX(e.getX() - paddle.getWidth() / 2);
        });

        score = new Score();
        score.setX(50);
        score.setY(50);
        getChildren().add(score);
        try {
            Scanner in = new Scanner(new File("src/breakoutresources/level" + level + ".txt"));

            Brick brick = new Brick(1);

            int rows = in.nextInt();
            int cols = in.nextInt();
            in.nextLine();

            double ogX = getWidth() / 2 - brick.getWidth() * cols / 2;
            double x = ogX;
            double y = 0;

            for (int i = 0; i < rows; i++) {
                String line = in.nextLine();
                String[] split = line.split("");
                for (int j = 0; j < cols; j++) {
                    int type = Integer.parseInt(split[j]);
                    if (type == 0) continue;

                    brick = new Brick(type);
                    getChildren().add(brick);
                    brick.setX(x);
                    brick.setY(y);
                    x += brick.getWidth();
                    numBricks++;
                }

                x = ogX;
                y += brick.getHeight();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void decrementNumBricks() {
        numBricks--;
    }

    public void setTitleScreen() {
        stage.setTitle("Breakout");
        stage.setWidth(200);
        stage.setHeight(100);

        //title screen
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(20, 20, 20, 20));

        Label titleLbl = new Label("Breakout");
        root.getChildren().add(titleLbl);

        Button playBtn = new Button("Play");
        playBtn.setOnAction(e -> {
            stage.sizeToScene();
            BorderPane rt = new BorderPane();
            rt.setCenter(this);

            Scene scene = new Scene(rt);
            stage.setScene(scene);

            start();
            stage.show();

            setLevel();
        });

        root.getChildren().add(playBtn);

        stage.setScene(new Scene(root));
        stage.show();
    }

    public Score getScore() {
        return score;
    }
}
