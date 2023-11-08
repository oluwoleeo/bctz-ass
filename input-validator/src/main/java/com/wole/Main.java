package com.wole;

public class Main {
    public static void main(String[] args) {
        InputValidator worker = new InputValidator();

        worker.readInputs();
        System.out.println(worker.validateInput());
    }
}