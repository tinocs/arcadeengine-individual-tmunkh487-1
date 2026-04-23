package breakout;

import engine.Actor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor {

    public Paddle() {
        setImage(new Image("file:src/breakoutresources/paddle.png"));
    }

    @Override
    public void act(long now) {
        if (getWorld().isKeyPressed(KeyCode.LEFT) || getWorld().isKeyPressed(KeyCode.A)) {
            move(-10, 0);
        }

        if (getWorld().isKeyPressed(KeyCode.RIGHT) || getWorld().isKeyPressed(KeyCode.D)) {
            move(10, 0);
        }
    }
}
