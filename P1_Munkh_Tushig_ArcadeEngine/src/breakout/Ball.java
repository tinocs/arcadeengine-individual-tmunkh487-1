package breakout;

import engine.Actor;
import engine.World;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import tests.CounterCoin;

public class Ball extends Actor {
    double dx;
    double dy;

    public Ball() {
        setImage(new Image("file:src/breakoutresources/ball.png"));

        dx = 6;
        dy = 7;
    }

    @Override
    public void act(long now) {
        move(dx, dy);

        Bounds worldBounds = getWorld().getBoundsInLocal();

        if (getX() <= worldBounds.getMinX()) {
            dx = -dx;
            setX(0);
        }

        if (getY() <= worldBounds.getMinY()) {
            dy = -dy;
            setY(0);
        }

        if (getX() + getWidth() >= worldBounds.getMaxX()) {
            dx = -dx;
            setX(worldBounds.getMaxX() - getWidth());
        }

        if (getY() + getHeight() >= worldBounds.getMaxY()) {
            dy = -dy;
            setY(worldBounds.getMaxY() - getHeight());
        }

        if (getIntersectingObjects(Paddle.class).size() > 0) {
            dy = -dy;
        }
    }
}
