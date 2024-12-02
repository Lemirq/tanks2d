package com.tanks2d;

import java.awt.Rectangle;
import java.util.ArrayList;

public class CollisionManager {
    ArrayList<Rectangle> collidables = new ArrayList<>();

    void addCollidable(Rectangle r) {
        collidables.add(r);
    }

    void removeCollidable(Rectangle r) {
        collidables.remove(r);
    }

    public boolean checkCollisions(Rectangle r, ArrayList<Rectangle> newC) {
        for (Rectangle collidable : newC) {
            if (r.intersects(collidable)) {
                return true;
            }
        }
        return false;
    }
}
