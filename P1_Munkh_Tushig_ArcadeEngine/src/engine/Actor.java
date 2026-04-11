package engine;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class Actor extends ImageView {

    public Actor() {

    }

    public abstract void act(long now);

    public void addedToWorld() {

    }

    public double getHeight() {
        return getBoundsInParent().getHeight();
    }

    public double getWidth() {
        return getBoundsInParent().getWidth();
    }

    public <A extends Actor>List<A> getIntersectingObjects(Class<A> cls) {
        if (getWorld() == null) return new ArrayList<A>();

        List<A> results = new ArrayList<>();
        List<A> objects = getWorld().getObjects(cls);

        for (A object : objects) {
            if (object != this && object.getBoundsInParent().intersects(this.getBoundsInParent())) {
                results.add(object);
            }
        }

        return results;
    }

    public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {
        List<A> objects = getIntersectingObjects(cls);

        return (objects.size() > 0) ? objects.get(0) : null;
    }

    public World getWorld() {
        return (World) getParent();
    }

    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
