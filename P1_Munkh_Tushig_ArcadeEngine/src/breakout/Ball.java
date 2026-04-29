package breakout;

import engine.Actor;
import engine.Sound;
import engine.World;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Ball extends Actor {
    double dx;
    double dy;
    private Sound bounceSnd = new Sound("breakoutresources/ball_bounce.wav");
    private Sound loseLifeSnd = new Sound("breakoutresources/lose_life.wav");
    private Sound brickSnd = new Sound("breakoutresources/brick_hit.wav");

    public Ball() {
        setImage(new Image("file:src/breakoutresources/ball.png"));

        dx = 5;
        dy = 5;
    }

    @Override
    public void act(long now) {
        if (!((BallWorld) getWorld()).getIsPaused() && !((BallWorld) getWorld()).getIsGameOver()) {
            move(dx, dy);

            if (getX() <= 0) {
                dx = -dx;
                setX(0);
                bounceSnd.play();
            }

            if (getY() <= 0) {
                dy = -dy;
                setY(0);
                bounceSnd.play();
            }

            if (getX() + getWidth() >= getWorld().getWidth()) {
                dx = -dx;
                setX(getWorld().getWidth() - getWidth());
                bounceSnd.play();
            }

            if (getY() + getHeight() >= getWorld().getHeight()) {
                dy = -dy;
                setY(getWorld().getHeight() - getHeight());

                Score score = ((BallWorld) getWorld()).getScore();

                score.setScoreVal(score.getScoreVal() - 1000);

                Lives lives = ((BallWorld) getWorld()).getLives();

                lives.setLivesVal(lives.getLivesVal() - 1);

                ((BallWorld) getWorld()).setIsPaused(true);

                loseLifeSnd.play();
            }

            if (getOneIntersectingObject(Paddle.class) != null) {
                dy = -dy;
                bounceSnd.play();
            }

            if (getOneIntersectingObject(Brick.class) != null) {
                Brick brick = getOneIntersectingObject(Brick.class);

                if (brick.getX() <= getX() && getX() <= brick.getX() + brick.getWidth()) {
                    dy = -dy;
                } else if (brick.getY() <= getY() && getY() <= brick.getY() + brick.getHeight()) {
                    dx = -dx;
                } else {
                    dx = -dx;
                    dy = -dy;
                }

                Score score = ((BallWorld) getWorld()).getScore();
                score.setScoreVal(score.getScoreVal() + 100);

                FadeTransition fadeTransition = new FadeTransition(Duration.millis(300), brick);
                ((BallWorld) getWorld()).addToAnimationList(fadeTransition);
                fadeTransition.setFromValue(1.0);
                fadeTransition.setToValue(0);
                fadeTransition.setOnFinished(e -> {
                    getWorld().getChildren().remove(brick);
                    ((BallWorld) getWorld()).removeFromAnimationList(fadeTransition);
                });
                fadeTransition.play();

                brickSnd.play();
            }
        }
    }
}
