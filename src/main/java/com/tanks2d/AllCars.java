package com.tanks2d;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class AllCars {
    ArrayList<Car> cars = new ArrayList<>();

    AllCars(CollisionManager cm) {
        for (int i = 0; i < 50; i++) {
            int x = (int) (Math.random() * (GamePanel.X_Bounds[1] - GamePanel.X_Bounds[0])) + GamePanel.X_Bounds[0];
            int y = (int) (Math.random() * (GamePanel.Y_Bounds[1] - GamePanel.Y_Bounds[0])) + GamePanel.Y_Bounds[0];
            cars.add(new Car(x, y));
        }

        for (Car car : cars) {
            cm.addCollidable(car);
        }

    }

    public void draw(Graphics2D g2d) {
        for (Car car : cars) {
            car.draw(g2d);
        }
    }
}
