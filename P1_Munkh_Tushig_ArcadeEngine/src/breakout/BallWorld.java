package breakout;

import engine.Sound;
import engine.World;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BallWorld extends World {

    private Score score;
    private Lives lives;
    private Stage stage;
    private boolean isGameOver = false;
    private int numBricks = 100;
    private boolean isPaused = true;
    Text gameOverText = new Text();
    Ball ball;
    Paddle paddle;
    int level;

    private Sound loseSound = new Sound("breakoutresources/game_lost.wav");
    private Sound winSound = new Sound("breakoutresources/game_won.wav");

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
        if (isGameOver && isKeyPressed(KeyCode.SPACE)) {
            gameOverText.setVisible(false);

            level = 1;
            lives.setLivesVal(3);
            getChildren().clear();
            setTitleScreen();
        }

        if (numBricks == 0) {
            level++;
            if (level == 3 && !isGameOver) {
                winSound.play();

                isGameOver = true;

                gameOverText.setText("You Won!");

                getChildren().add(gameOverText);

                gameOverText.setX(getWidth() / 2 - gameOverText.getBoundsInLocal().getWidth() / 2);
                gameOverText.setY(getHeight() / 2 - gameOverText.getBoundsInLocal().getHeight() / 2);

                isPaused = true;
            }
            else {
                getChildren().clear();
                setLevel();
            }
        }

        if (isPaused && isKeyPressed(KeyCode.SPACE)) {
            isPaused = false;
        }

        if (isPaused) {
            ball.setX(paddle.getX() + paddle.getWidth() / 2 - ball.getWidth() / 2);
        }

        if (lives.getLivesVal() == 0 && !isGameOver) {
            loseSound.play();

            isGameOver = true;

            gameOverText.setText("You Lost!");

            getChildren().add(gameOverText);

            gameOverText.setX(getWidth() / 2 - gameOverText.getBoundsInLocal().getWidth() / 2);
            gameOverText.setY(getHeight() / 2 - gameOverText.getBoundsInLocal().getHeight() / 2);

            isPaused = true;
        }
    }

    @Override
    public void onDimensionsInitialized() {
        setTitleScreen();
    }

    public void setLevel() {
        numBricks = 0;

        ball = new Ball();
        getChildren().add(ball);

        paddle = new Paddle();
        getChildren().add(paddle);

        paddle.setX(getWidth() / 2 - paddle.getWidth() / 2);
        paddle.setY(getHeight() - paddle.getHeight() * 4);

        ball.setX(getWidth() / 2 -  ball.getWidth() / 2);
        ball.setY(paddle.getY() - ball.getHeight());

        setOnMouseMoved(e -> {
            paddle.setX(e.getX() - paddle.getWidth() / 2);
        });

        score = new Score();
        score.setX(50);
        score.setY(50);
        getChildren().add(score);

        lives = new Lives();
        lives.setX(getWidth() - 100);
        lives.setY(50);
        getChildren().add(lives);

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
        isGameOver = false;

        stage.setTitle("Breakout");
        stage.setWidth(800);
        stage.setHeight(600);

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

    public Lives getLives() {
        return lives;
    }

    public boolean getIsPaused() {
        return isPaused;
    }

    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }
}
