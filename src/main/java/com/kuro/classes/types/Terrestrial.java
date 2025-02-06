package com.kuro.classes.types;

import com.kuro.classes.Animal;
import com.kuro.classes.enums.AnimalType;

public class Terrestrial extends Animal {
    public Terrestrial(String name, String sound) {
        super(name, sound, AnimalType.TERRESTRIAL);
    }

    @Override
    public void move() {
        System.out.println(name + " is walking on land.");
    }
}
