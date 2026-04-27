package breakout;

import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor {

    private int dx = 10;

    public Paddle() {
        setImage(new Image("file:src/breakoutresources/paddle.png"));
    }

    @Override
    public void act(long now) {
        if (!((BallWorld) getWorld()).getIsGameOver()) {
            if (getWorld().isKeyPressed(KeyCode.LEFT) || getWorld().isKeyPressed(KeyCode.A)) {
                if (0 <= getX() - dx) {
                    move(-dx, 0);

                    if (getX() < getWorld().getWidth() / 2) ((BallWorld) getWorld()).scroll(-dx);
                }
            }

            if (getWorld().isKeyPressed(KeyCode.RIGHT) || getWorld().isKeyPressed(KeyCode.D)) {
                if (getX() + dx <= getWorld().getWidth() - getWidth()) {
                    move(dx, 0);

                    if (getX() > getWorld().getWidth() / 2) ((BallWorld) getWorld()).scroll(dx);
                }
            }
        }
    }
}
