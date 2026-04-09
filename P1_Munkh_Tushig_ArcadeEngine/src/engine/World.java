package engine;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class World extends Pane {

    private AnimationTimer myAnimationTimer;
    private boolean isTimerRunning;
    private Set<KeyCode> keysPressed;
    private boolean isWidthInit;
    private boolean isHeightInit;
    private Set<Actor> actors;

    public World() {
        keysPressed = new HashSet<>();
        isWidthInit = false;
        isHeightInit = false;
        actors = new HashSet<>();
        myAnimationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };

        widthProperty().addLis
    }

    public abstract void act(long now);

    public void add(Actor actor) {

    }

    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        List<A> objects = new ArrayList<>();
        for (Actor actor : actors) {
            if (cls.isInstance(actor)) {
                objects.add(cls.cast(actor));
            }
        }

        return objects;
    }

    public <A extends Actor> List<A> getObjectsAt(double x, double y,  Class<A> cls) {
        List<A> objects = new ArrayList<>();
        for (Actor actor : actors) {
            if (cls.isInstance(actor)) {
                int minX = actor.getX() - actor.getWidth() / 2;
                int maxX = actor.getX() + actor.getWidth() / 2;
                int minY = actor.getY() - actor.getHeight() / 2;
                int maxY = actor.getY() + actor.getHeight() / 2;
                if (minX <= x && x <= maxX && minY <= y && y <= maxY) {
                    objects.add(cls.cast(actor));
                }
            }
        }

        return objects;
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