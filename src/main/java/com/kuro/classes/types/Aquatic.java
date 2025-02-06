package com.kuro.classes.types;

import com.kuro.classes.Animal;
import com.kuro.classes.enums.AnimalType;

public class Aquatic extends Animal {

    public Aquatic(String name, String onomatopoeia) {
        super(name, onomatopoeia, AnimalType.AQUATIC);
    }

    @Override
    public void move() {
        System.out.println(name + " is swimming in the water.");
    }

}
