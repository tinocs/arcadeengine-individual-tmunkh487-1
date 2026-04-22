package breakout;

import engine.World;

public class BallWorld extends World {

    public BallWorld() {
        setPrefHeight(600);
        setPrefWidth(800);
    }

    @Override
    public void act(long now) {
        //blank
    }

    @Override
    public void onDimensionsInitialized() {
        Ball ball = new Ball();
        getChildren().add(ball);

        ball.setX(getPrefWidth() / 2 -  ball.getWidth() / 2);
        ball.setY(getPrefHeight() / 2 - ball.getHeight() / 2);

        Paddle paddle = new Paddle();
        getChildren().add(paddle);

        paddle.setX(getPrefWidth() / 2 - paddle.getWidth() / 2);
        paddle.setY(getPrefHeight() / 2);
    }
}
