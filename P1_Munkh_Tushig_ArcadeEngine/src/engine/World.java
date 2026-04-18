package engine;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
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
    private boolean isDimensionsInit;
    private long time = 0;
    private long delay = 1;

    public World() {
        keysPressed = new HashSet<>();
        isWidthInit = false;
        isHeightInit = false;
        isDimensionsInit = false;
        isTimerRunning = false;

        widthProperty().addListener((ov, oldVal, newVal) -> {
            isWidthInit = true;
            if (isHeightInit && !isDimensionsInit) {
                onDimensionsInitialized();
                isDimensionsInit = true;
            }
        });

        heightProperty().addListener((ov, oldVal, newVal) -> {
            isHeightInit = true;
            if (isWidthInit && !isDimensionsInit) {
                onDimensionsInitialized();
                isDimensionsInit = true;
            }
        });

        sceneProperty().addListener((ov, oldVal, newVal) -> {
            if (getScene() != null) {
                requestFocus();
            }
        });

        setOnKeyPressed(e -> {
            keysPressed.add(e.getCode());
        });

        setOnKeyReleased(e -> {
            keysPressed.remove(e.getCode());
        });

        myAnimationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - time >= delay) {
                    time = now;

                    act(now);

                    for (Actor actor : getObjects(Actor.class)) {
                        if (actor.getWorld() == World.this) actor.act(now);
                    }
                }
            }
        };
    }

    public abstract void act(long now);

    public void add(Actor actor) {
        getChildren().add(actor);
        actor.addedToWorld();
    }

    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        List<A> objects = new ArrayList<>();
        for (Node node : getChildren()) {
            if (cls.isInstance(node)) {
                objects.add(cls.cast(node));
            }
        }

        return objects;
    }

    public <A extends Actor> List<A> getObjectsAt(double x, double y,  Class<A> cls) {
        List<A> objects = new ArrayList<>();
        for (Actor actor : getObjects(cls)) {
            if (cls.isInstance(actor)) {
                if (actor.getBoundsInParent().contains(x, y)) {
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
        return !isTimerRunning;
    }

    public abstract void onDimensionsInitialized();

    public void remove(Actor actor) {
        getChildren().remove(actor);
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