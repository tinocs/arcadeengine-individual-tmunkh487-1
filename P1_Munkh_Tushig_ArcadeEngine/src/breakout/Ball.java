package breakout;

import engine.Actor;
import engine.Sound;
import engine.World;
import javafx.animation.FadeTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.util.Duration;
import tests.CounterCoin;

public class Ball extends Actor {
    double dx;
    double dy;
    private Sound bounceSnd = new Sound("breakoutresources/ball_bounce.wav");
    private Sound loseLifeSnd = new Sound("breakoutresources/lose_life.wav");
    private Sound brickSnd = new Sound("breakoutresources/brick_hit.wav");

    public Ball() {
        setImage(new Image("file:src/breakoutresources/ball.png"));

        dx = 6;
        dy = 7;
    }

    @Override
    public void act(long now) {
        if (!((BallWorld) getWorld()).getIsPaused()) {
            move(dx, dy);

            Bounds worldBounds = getWorld().getBoundsInLocal();

            if (getX() <= worldBounds.getMinX()) {
                dx = -dx;
                setX(0);
                bounceSnd.play();
            }

            if (getY() <= worldBounds.getMinY()) {
                dy = -dy;
                setY(0);
                bounceSnd.play();
            }

            if (getX() + getWidth() >= worldBounds.getMaxX()) {
                dx = -dx;
                setX(worldBounds.getMaxX() - getWidth());
                bounceSnd.play();
            }

            if (getY() + getHeight() >= worldBounds.getMaxY()) {
                dy = -dy;
                setY(worldBounds.getMaxY() - getHeight());

                Score score = ((BallWorld) getWorld()).getScore();

                score.setScoreVal(score.getScoreVal() - 1000);

                Lives lives = ((BallWorld) getWorld()).getLives();

                lives.setLivesVal(lives.getLivesVal() - 1);

                loseLifeSnd.play();
            }

            if (getOneIntersectingObject(Paddle.class) != null) {
                dy = -dy;
                bounceSnd.play();
            }

            if (getOneIntersectingObject(Brick.class) != null) {
                brickSnd.play();

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

                ((BallWorld) getWorld()).decrementNumBricks();

                FadeTransition fadeTransition = new FadeTransition(new Duration(1e9 * 5), brick);
                fadeTransition.play();

                fadeTransition.setOnFinished(e -> {
                    getWorld().getChildren().remove(brick);
                });

                getWorld().getChildren().remove(brick);
            }
        }
    }
}
