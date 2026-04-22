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
        setImage(new Image(Ball.class.getResource("/breakoutresources/ball.png").toString()));

        dx = 6;
        dy = 7;
    }

    @Override
    public void act(long now) {
        move(dx, dy);

        Bounds worldBounds = getWorld().getBoundsInLocal();

        boolean isBounce = false;

        if (getX() <= worldBounds.getMinX()) {
            isBounce = true;
            setX(0);
        }

        if (getY() <= worldBounds.getMinY()) {
            isBounce = true;
            setY(0);
        }

        if (getX() + getWidth() >= worldBounds.getMaxX()) {
            isBounce = true;
            setX(worldBounds.getMaxX() - getWidth());
        }

        if (getY() + getHeight() >= worldBounds.getMaxY()) {
            isBounce = true;
            setY(worldBounds.getMaxY() - getHeight());
        }

        if (isBounce) {
            dx = -dx;
            dy = -dy;
        }
    }
}
