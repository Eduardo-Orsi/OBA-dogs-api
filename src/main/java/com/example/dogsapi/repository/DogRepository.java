package com.example.dogsapi.repository;

import com.example.dogsapi.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    
    // Find dogs by sexo
    List<Dog> findBySexo(String sexo);
    
    // Find dogs by porte
    List<Dog> findByPorte(String porte);
    
    // Find dogs by raca
    List<Dog> findByRaca(String raca);
    
    // Find dogs by idade range
    List<Dog> findByIdadeBetween(Integer minIdade, Integer maxIdade);
    
    // Custom query to find dogs by multiple criteria
    @Query("SELECT d FROM Dog d WHERE " +
           "(:sexo IS NULL OR d.sexo = :sexo) AND " +
           "(:porte IS NULL OR d.porte = :porte) AND " +
           "(:raca IS NULL OR d.raca = :raca)")
    List<Dog> findByFilters(@Param("sexo") String sexo, 
                           @Param("porte") String porte, 
                           @Param("raca") String raca);
}