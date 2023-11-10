package com.wole;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int pancakesToEat;
    private final List<Pancake> pancakesEaten = new ArrayList<>();

    public User(int pancakesToEat){
        this.pancakesToEat = pancakesToEat;
    }

    public void eatPancakes(long startTime){
        while (System.currentTimeMillis() - startTime <= 30000L && pancakesEaten.size() < pancakesToEat){
            Pancake pancake = ShopKeeper.takePancake();

            if (pancake != null){
                pancakesEaten.add(pancake);
            }
        }
    }

    public int getPancakesToEat() {
        return pancakesToEat;
    }

    public int getPancakesEaten() {
        return pancakesEaten.size();
    }
}
