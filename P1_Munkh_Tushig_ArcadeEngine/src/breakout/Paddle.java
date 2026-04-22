package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Paddle extends Actor {

    public Paddle() {
        setImage(new Image("file:src/breakoutresources/paddle.png"));
    }

    @Override
    public void act(long now) {
        //blank
    }
}
