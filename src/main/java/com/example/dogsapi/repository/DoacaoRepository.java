package com.example.dogsapi.repository;

import com.example.dogsapi.model.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
} 