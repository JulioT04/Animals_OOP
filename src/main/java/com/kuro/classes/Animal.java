package com.kuro.classes;

import com.kuro.classes.enums.AnimalType;

public abstract class Animal {
    public String name;
    public String onomatopoeia;
    public AnimalType type;

    public Animal(String name, String onomatopoeia, AnimalType type) {
        this.name = name;
        this.onomatopoeia = onomatopoeia;
        this.type = type;
    }

    public void getSound() {
        System.out.println(name + " says: " + onomatopoeia);
    }


    public String getName() {
        return name;
    }


    public AnimalType getType() {
        return type;
    }


    public String makeSound() {
        return onomatopoeia;
    }
    public abstract void move();
}
