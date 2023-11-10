package com.wole;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Main {
    private static final int numberOfUsers = 3;
    private static List<User> users = new ArrayList<>(numberOfUsers);
    private static final int maxPancakesToEat = 5;

    public static void main(String[] args) {
        generateUsers();

        System.out.println("Non-concurrent approach:");
        makeAndEatPancakesBlocking();

        System.out.println("Concurrent approach:");
        makeAndEatPancakesNonBlocking();
    }

    public static void makeAndEatPancakesBlocking(){
        long startTime = System.currentTimeMillis();

        ShopKeeper.makePancakes(startTime);
        eatPancakes(startTime);

        long endTime = System.currentTimeMillis();
        printReport(startTime, endTime);
    }

    public static void makeAndEatPancakesNonBlocking(){
        long startTime = System.currentTimeMillis();

        CompletableFuture<Void> makePancakesTask = CompletableFuture.runAsync(() -> ShopKeeper.makePancakes(startTime));
        CompletableFuture<Void> eatPancakesTask = CompletableFuture.runAsync(() -> eatPancakes(startTime));

        CompletableFuture.allOf(makePancakesTask, eatPancakesTask).join();

        long endTime = System.currentTimeMillis();
        printReport(startTime, endTime);
    }

    public static void printReport(long startTime, long endTime){
        printPancakesEaten();
        System.out.println("Start time: " + getLocalDateTime(startTime));
        System.out.println("End time: " + getLocalDateTime(endTime));
        System.out.println("Number of pancakes made: " + ShopKeeper.getNumberOfPancakesMade());
        System.out.println("Number of pancakes taken according to ShopKeeper: " + ShopKeeper.getNumberOfPancakesTaken());
        System.out.println("Number of pancakes taken according to Users: " + totalNumberOfPancakesEaten());
        System.out.println("Users' orders met: " + ordersMet());
        System.out.println("Number of pancakes wasted: " + ShopKeeper.getNumberOfPancakesWasted());
        System.out.println("Number of orders not met: " + totalOrdersNotMet());
    }

    public static void generateUsers(){
        Random rand = new Random();

        for(int i = 0; i < numberOfUsers; i++){
            int pancakesToEat = 1 + rand.nextInt(maxPancakesToEat);
            System.out.println("User " + (i+1) + " wants to eat " + pancakesToEat + " pancakes");
            users.add(new User(pancakesToEat));
        }
    }

    public static void printPancakesEaten(){
        for(int i = 0; i < numberOfUsers; i++){
            System.out.println("User " + (i+1) + " ate " + users.get(i).getPancakesEaten() + " pancakes");
        }
    }

    public static void eatPancakes(long startTime){
        for(User user: users){
            CompletableFuture.runAsync(() -> user.eatPancakes(startTime));
        }
    }

    public static LocalDateTime getLocalDateTime(long unixTimestamp){
        Instant instant = Instant.ofEpochMilli(unixTimestamp);

        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static int totalNumberOfPancakesEaten(){
        return users.stream()
                .mapToInt(User::getPancakesEaten)
                .sum();
    }

    public static int totalNumberOfPancakesToEat(){
        return users.stream()
                .mapToInt(User::getPancakesToEat)
                .sum();
    }

    public static boolean ordersMet(){
        if (totalNumberOfPancakesToEat() == totalNumberOfPancakesEaten()){
            return true;
        }
        return false;
    }

    public static int totalOrdersNotMet(){
        int total = 0;

        if (!ordersMet()){
            total = users.stream()
                    .mapToInt(u -> u.getPancakesToEat() - u.getPancakesEaten())
                    .sum();
        }

        return total;
    }
}