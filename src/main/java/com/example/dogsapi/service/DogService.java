package com.example.dogsapi.service;

import com.example.dogsapi.model.Dog;
import com.example.dogsapi.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {
    
    @Autowired
    private DogRepository dogRepository;
    
    // Get all dogs
    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }
    
    // Get dog by ID
    public Optional<Dog> getDogById(Long id) {
        return dogRepository.findById(id);
    }
    
    // Create new dog
    public Dog createDog(Dog dog) {
        // Ensure all fields, including especie and imagem, are set
        return dogRepository.save(dog);
    }
    
    // Update existing dog
    public Dog updateDog(Long id, Dog dogDetails) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        
        if (optionalDog.isPresent()) {
            Dog dog = optionalDog.get();
            dog.setNome(dogDetails.getNome());
            dog.setIdade(dogDetails.getIdade());
            dog.setSexo(dogDetails.getSexo());
            dog.setPorte(dogDetails.getPorte());
            dog.setRaca(dogDetails.getRaca());
            dog.setEspecie(dogDetails.getEspecie());
            dog.setImagem(dogDetails.getImagem());
            return dogRepository.save(dog);
        } else {
            throw new RuntimeException("Dog not found with id: " + id);
        }
    }
    
    // Delete dog
    public void deleteDog(Long id) {
        if (dogRepository.existsById(id)) {
            dogRepository.deleteById(id);
        } else {
            throw new RuntimeException("Dog not found with id: " + id);
        }
    }
    
    // Filter methods
    public List<Dog> getDogsBySexo(String sexo) {
        return dogRepository.findBySexo(sexo);
    }
    
    public List<Dog> getDogsByPorte(String porte) {
        return dogRepository.findByPorte(porte);
    }
    
    public List<Dog> getDogsByRaca(String raca) {
        return dogRepository.findByRaca(raca);
    }
    
    public List<Dog> getDogsByFilters(String sexo, String porte, String raca) {
        return dogRepository.findByFilters(sexo, porte, raca);
    }
    
    // Get dogs by age range
    public List<Dog> getDogsByAgeRange(Integer minAge, Integer maxAge) {
        return dogRepository.findByIdadeBetween(minAge, maxAge);
    }
}