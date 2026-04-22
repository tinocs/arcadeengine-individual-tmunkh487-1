package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor {

    public Brick() {
        setImage(new Image("file:src/breakoutresources/brick.png"));
    }

    @Override
    public void act(long now) {
        //blank
    }
}
