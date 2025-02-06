package com.kuro.classes.types;

import com.kuro.classes.Animal;
import com.kuro.classes.enums.AnimalType;

public class Aerial extends Animal {

    public Aerial(String name, String onomatopoeia) {
        super(name, onomatopoeia, AnimalType.AERIAL);
    }
    @Override
    public void move() {
        System.out.println(name + " is flying in the sky.");
    }

}
