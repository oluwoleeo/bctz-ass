package com.wole;

import java.util.ArrayList;
import java.util.List;

public class ShopKeeper {
    private static final int maxPancakesToMake = 12;
    private static int pancakesMade = 0;
    private static int pancakesTaken = 0;
    private static final List<Pancake> pancakes = new ArrayList<>(maxPancakesToMake);

    public static synchronized Pancake takePancake(){
        if(pancakesTaken < pancakes.size()){
            return pancakes.get(pancakesTaken++);
        }
        return null;
    }

    public static void makePancakes(long startTime){
        while (System.currentTimeMillis() - startTime <= 30000L){
            if (pancakesMade < maxPancakesToMake){
                Pancake pancake = new Pancake();
                pancakes.add(pancake);

                pancakesMade++;
            }
        }
    }

    public static int getNumberOfPancakesMade(){
        return pancakesMade;
    }

    public static int getNumberOfPancakesTaken(){
        return pancakesTaken;
    }

    public static int getNumberOfPancakesWasted(){
        return pancakesMade - pancakesTaken;
    }
}
