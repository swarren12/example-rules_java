package com.example;

import java.util.Random;

public class Main {

    public static void main(String... args) {
        Object o = createRandomObject();
        switch(o) {
            case Bird b -> System.out.println("Random object was a Bird");
            case Cat c -> System.out.println("Random object was a Cat");
            case Dog d -> System.out.println("Random object was a Dog");
            default -> System.out.println("Random object wasn't recognised!");
	    }
    }


    private static Object createRandomObject() {
        int r = new Random().nextInt(30);
        if (r < 10) return new Bird("Bird");
        if (r < 20) return new Cat("Cat");
        if (r < 30) return new Dog("Dog");
        return null;
    }

    private static record Bird(String name) {}
    private static record Cat(String name) {}
    private static record Dog(String name) {}

}
