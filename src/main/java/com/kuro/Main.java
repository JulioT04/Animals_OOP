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

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] strings = input.split("\\|");
            if (strings.length != 3) {
                System.out.println("Incorrect format. Use: name|type|onomatopoeia");
                continue;
            }

            String name = strings[0];
            String typeStr = strings[1].toLowerCase();
            String onomatopoeia = strings[2];

            AnimalType type = AnimalType.getAnimalType(typeStr).orElse(null);
            if (type == null) {
                System.out.println("Unknown type. Use: Terrestrial, Aquatic, or Aerial");
                continue;
            }

            Animal animal = switch (type) {
                case TERRESTRIAL -> new Terrestrial(name, onomatopoeia);
                case AQUATIC -> new Aquatic(name, onomatopoeia);
                case AERIAL -> new Aerial(name, onomatopoeia);
            };

            animals.add(animal);
        }


        Map<AnimalType, List<Animal>> groupedAnimals = animals.stream()
                .collect(Collectors.groupingBy(Animal::getType));

        System.out.println("List of Animals by Type\n");
        groupedAnimals.forEach((type, animalList) -> {
            System.out.println("Type: ");
            animalList.forEach(animal -> {
                System.out.println("  - " + animal.getName() + " makes: " + animal.makeSound());
                animal.move();
            });
        });

        System.out.println("Program finished.");
    }
}