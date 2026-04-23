package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor {
    private int type;
    public Brick(int type) {
        this.type = type;
        setImage(new Image("file:src/breakoutresources/brick" + type + ".png"));
    }

    @Override
    public void act(long now) {
        //blank
    }
}
