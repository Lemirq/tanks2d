package com.tanks2d;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class AllCars {
    ArrayList<Car> cars = new ArrayList<>();
    Random random = new Random();

    AllCars() {
        for (int i = 0; i < 100; i++) {
            int img = random.nextInt(4);
            int x = (int) (Math.random() * (GamePanel.X_Bounds[1] - GamePanel.X_Bounds[0])) + GamePanel.X_Bounds[0];
            int y = (int) (Math.random() * (GamePanel.Y_Bounds[1] - GamePanel.Y_Bounds[0])) + GamePanel.Y_Bounds[0];
            cars.add(new Car(Images.car[img], x, y));
        }

    }

    public void draw(Graphics2D g2d) {
        for (Car car : cars) {
            car.draw(g2d);
        }
    }
}
