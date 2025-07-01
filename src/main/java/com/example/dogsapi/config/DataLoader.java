package com.example.dogsapi.config;

import com.example.dogsapi.model.Dog;
import com.example.dogsapi.repository.DogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final DogRepository dogRepository;

    public DataLoader(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (dogRepository.count() == 0) {
            List<Dog> dogs = Arrays.asList(
                new Dog("Apolo", 2, "Masculino", "Médio", "Vira-lata"),
                new Dog("Cacau", 7, "Feminino", "Pequeno", "Pinscher"),
                new Dog("Garfield", 3, "Masculino", "Médio", "Vira-lata"),
                new Dog("Bella", 4, "Feminino", "Grande", "Golden Retriever"),
                new Dog("Max", 1, "Masculino", "Pequeno", "Chihuahua"),
                new Dog("Luna", 5, "Feminino", "Médio", "Border Collie"),
                new Dog("Rex", 6, "Masculino", "Grande", "Pastor Alemão"),
                new Dog("Mel", 2, "Feminino", "Pequeno", "Yorkshire"),
                new Dog("Thor", 8, "Masculino", "Grande", "Rottweiler"),
                new Dog("Nina", 3, "Feminino", "Médio", "Labrador"),
                new Dog("Buddy", 4, "Masculino", "Grande", "Husky Siberiano"),
                new Dog("Lola", 1, "Feminino", "Pequeno", "Maltês"),
                new Dog("Zeus", 7, "Masculino", "Grande", "Dogue Alemão"),
                new Dog("Chloe", 2, "Feminino", "Médio", "Beagle"),
                new Dog("Rocky", 5, "Masculino", "Grande", "Boxer")
            );
            dogRepository.saveAll(dogs);
        }
    }
} 