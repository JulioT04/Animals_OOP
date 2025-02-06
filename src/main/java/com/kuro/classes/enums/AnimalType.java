package com.kuro.classes.enums;

import java.util.Arrays;
import java.util.Optional;

public enum AnimalType {
    TERRESTRIAL,
    AQUATIC,
    AERIAL;

    public static Optional<AnimalType> getAnimalType(String animalType) {
        return Arrays.stream(values()).filter(t->t.name().equalsIgnoreCase(animalType)).findFirst();
    }


}
