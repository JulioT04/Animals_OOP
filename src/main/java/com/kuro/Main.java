package com.kuro;
import com.kuro.classes.Animal;
import com.kuro.classes.config.AnimalConfig;
import com.kuro.classes.enums.AnimalType;
import com.kuro.classes.types.Aquatic;
import com.kuro.classes.types.Aerial;
import com.kuro.classes.types.Terrestrial;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AnimalConfig.class);
        Scanner scanner = context.getBean(Scanner.class);;
        List<Animal> animals = new ArrayList<>();

        System.out.println("Welcome to the Animals System");
        System.out.println("Please enter you input using the following format: name|type|onomatopoeia");
        System.out.println("Example: Dog|Terrestrial|Woof");
        System.out.println("Type 'exit' to stop the program and see the results\n");

        while (true) {
            System.out.print("Enter an animal: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) break;

            Optional<Animal> animal = parseInput(input);
            animal.ifPresent(animals::add);
        }


        Map<AnimalType, List<Animal>> groupedAnimals = groupAnimals(animals);

        System.out.println("\nList of Animals by Type\n");
        groupedAnimals.forEach((type, animalList) -> {
            System.out.println("■" + type + ":");
            animalList.forEach(animal -> {
                System.out.println("  - " + animal.getName() + " makes: " + animal.makeSound());
                animal.move();
            });
        });

        System.out.println("\nProgram finished.");
    }


    public static Optional<Animal> parseInput(String input) {
        String[] parts = input.split("\\|");
        if (parts.length != 3) {
            System.out.println("Incorrect format. Use: name|type|onomatopoeia");
            return Optional.empty();
        }

        String name = parts[0];
        String typeStr = parts[1].toLowerCase();
        String onomatopoeia = parts[2];

        return createAnimal(name, typeStr, onomatopoeia);
    }

    public static Optional<Animal> createAnimal(String name, String typeStr, String onomatopoeia) {
        AnimalType type = AnimalType.getAnimalType(typeStr).orElse(null);
        if (type == null) {
            System.out.println("Unknown type. Use: Terrestrial, Aquatic, or Aerial.");
            return Optional.empty();
        }

        return Optional.of(switch (type) {
            case TERRESTRIAL -> new Terrestrial(name, onomatopoeia);
            case AQUATIC -> new Aquatic(name, onomatopoeia);
            case AERIAL -> new Aerial(name, onomatopoeia);
        });
    }

    public static Map<AnimalType, List<Animal>> groupAnimals(List<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::getType));
    }
}