package engine;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public abstract class World extends Pane {

    private AnimationTimer myAnimationTimer;
    private boolean isTimerRunning;
    private ArrayList<KeyCode> keysPressed;
    private boolean isWidthInit;
    private boolean isHeightInit;

    public abstract void act(long now);

    public void add(Actor actor) {

    }

    List<Actor> getObjects(Class<A> cls) {

    }

    public boolean isKeyPressed(KeyCode code) {
        return keysPressed.contains(code);
    }

    public boolean isStopped() {
        return isTimerRunning;
    }

    public abstract void onDimensionsInitialized();

    public void remove(Actor actor) {

    }

    public void start() {
        myAnimationTimer.start();
        isTimerRunning = true;
    }

    public void stop() {
        myAnimationTimer.stop();
        isTimerRunning = false;
    }
}