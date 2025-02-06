package com.kuro;

import com.kuro.classes.Animal;
import com.kuro.classes.enums.AnimalType;
import com.kuro.classes.types.Aerial;
import com.kuro.classes.types.Aquatic;
import com.kuro.classes.types.Terrestrial;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
class MainTest {
    @Test
    void testValidInputParsing() {
        Optional<Animal> animal = Main.parseInput("Dog|Terrestrial|Woof");
        assertTrue(animal.isPresent(), "Animal should be created for valid input.");
        assertEquals("Dog", animal.get().getName(), "Animal name mismatch.");
        assertEquals(AnimalType.TERRESTRIAL, animal.get().getType(), "Animal type mismatch.");
    }

    @Test
    void testAnimalCreationTerrestrial() {
        Optional<Animal> animal = Main.createAnimal("Dog", "terrestrial", "Woof");
        assertTrue(animal.isPresent(), "Terrestrial animal should be created.");
        assertTrue(animal.get() instanceof Terrestrial, "Animal should be an instance of Terrestrial.");
    }

    @Test
    void testAnimalCreationAquatic() {
        Optional<Animal> animal = Main.createAnimal("Dolphin", "aquatic", "E E E E");
        assertTrue(animal.isPresent(), "Aquatic animal should be created.");
        assertTrue(animal.get() instanceof Aquatic, "Animal should be an instance of Aquatic.");
    }

    @Test
    void testAnimalCreationAerial() {
        Optional<Animal> animal = Main.createAnimal("Hawk", "aerial", "Tuah");
        assertTrue(animal.isPresent(), "Aerial animal should be created.");
        assertTrue(animal.get() instanceof Aerial, "Animal should be an instance of Aerial.");
    }

    @Test
    void testAnimalGrouping() {
        List<Animal> animals = List.of(
                new Terrestrial("Dog", "Woof"),
                new Aquatic("Dolphin", "E E E E"),
                new Aerial("Hawk", "Tuah"),
                new Terrestrial("Cat", "Meow")
        );

        Map<AnimalType, List<Animal>> grouped = Main.groupAnimals(animals);

        assertEquals(2, grouped.get(AnimalType.TERRESTRIAL).size(), "Should be 2 terrestrial animals.");
        assertEquals(1, grouped.get(AnimalType.AQUATIC).size(), "Should be 1 aquatic animal.");
        assertEquals(1, grouped.get(AnimalType.AERIAL).size(), "Should be 1 aerial animal.");
    }
}