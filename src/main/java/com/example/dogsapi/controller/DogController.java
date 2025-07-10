package com.example.dogsapi.controller;

import com.example.dogsapi.model.Dog;
import com.example.dogsapi.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/dogs")
@CrossOrigin(origins = "*") // Allow all origins for development
public class DogController {
    
    @Autowired
    private DogService dogService;
    
    // DTO for Dog responses
    public static class DogDTO {
        public Long id;
        public String nome;
        public Integer idade;
        public String sexo;
        public String porte;
        public String raca;
        public String especie;
        public String imagemUrl;

        public DogDTO(Dog dog) {
            this.id = dog.getId();
            this.nome = dog.getNome();
            this.idade = dog.getIdade();
            this.sexo = dog.getSexo();
            this.porte = dog.getPorte();
            this.raca = dog.getRaca();
            this.especie = dog.getEspecie();
            this.imagemUrl = "/api/dogs/" + dog.getId() + "/imagem";
        }
    }
    
    // GET /api/dogs - Get all dogs
    @GetMapping
    public ResponseEntity<List<DogDTO>> getAllDogs(
            @RequestParam(required = false) String sexo,
            @RequestParam(required = false) String porte,
            @RequestParam(required = false) String raca) {
        
        List<Dog> dogs;
        
        // If any filter is provided, use filtered search
        if (sexo != null || porte != null || raca != null) {
            dogs = dogService.getDogsByFilters(sexo, porte, raca);
        } else {
            dogs = dogService.getAllDogs();
        }
        
        List<DogDTO> dogDTOs = dogs.stream().map(DogDTO::new).toList();
        return ResponseEntity.ok(dogDTOs);
    }
    
    // GET /api/dogs/{id} - Get dog by ID
    @GetMapping("/{id}")
    public ResponseEntity<DogDTO> getDogById(@PathVariable Long id) {
        Optional<Dog> dog = dogService.getDogById(id);
        
        if (dog.isPresent()) {
            return ResponseEntity.ok(new DogDTO(dog.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // POST /api/dogs - Create new dog
    @PostMapping
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
        try {
            Dog createdDog = dogService.createDog(dog);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDog);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // PUT /api/dogs/{id}/imagem - Upload or update dog image
    @PutMapping(value = "/{id}/imagem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDogImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Optional<Dog> optionalDog = dogService.getDogById(id);
            if (optionalDog.isPresent()) {
                Dog dog = optionalDog.get();
                dog.setImagem(file.getBytes());
                dogService.createDog(dog); // save updated dog
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // PUT /api/dogs/{id} - Update existing dog
    @PutMapping("/{id}")
    public ResponseEntity<Dog> updateDog(@PathVariable Long id, @RequestBody Dog dogDetails) {
        try {
            Dog updatedDog = dogService.updateDog(id, dogDetails);
            return ResponseEntity.ok(updatedDog);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // DELETE /api/dogs/{id} - Delete dog
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDog(@PathVariable Long id) {
        try {
            dogService.deleteDog(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // GET /api/dogs/filter/sexo/{sexo} - Get dogs by sexo
    @GetMapping("/filter/sexo/{sexo}")
    public ResponseEntity<List<Dog>> getDogsBySexo(@PathVariable String sexo) {
        List<Dog> dogs = dogService.getDogsBySexo(sexo);
        return ResponseEntity.ok(dogs);
    }
    
    // GET /api/dogs/filter/porte/{porte} - Get dogs by porte
    @GetMapping("/filter/porte/{porte}")
    public ResponseEntity<List<Dog>> getDogsByPorte(@PathVariable String porte) {
        List<Dog> dogs = dogService.getDogsByPorte(porte);
        return ResponseEntity.ok(dogs);
    }
    
    // GET /api/dogs/filter/raca/{raca} - Get dogs by raca
    @GetMapping("/filter/raca/{raca}")
    public ResponseEntity<List<Dog>> getDogsByRaca(@PathVariable String raca) {
        List<Dog> dogs = dogService.getDogsByRaca(raca);
        return ResponseEntity.ok(dogs);
    }
    
    // GET /api/dogs/filter/age - Get dogs by age range
    @GetMapping("/filter/age")
    public ResponseEntity<List<Dog>> getDogsByAge(
            @RequestParam Integer minAge,
            @RequestParam Integer maxAge) {
        List<Dog> dogs = dogService.getDogsByAgeRange(minAge, maxAge);
        return ResponseEntity.ok(dogs);
    }

    // GET /api/dogs/{id}/imagem - Get dog image by ID
    @GetMapping(value = "/{id}/imagem", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getDogImage(@PathVariable Long id) {
        Optional<Dog> dog = dogService.getDogById(id);
        if (dog.isPresent() && dog.get().getImagem() != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(dog.get().getImagem());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}