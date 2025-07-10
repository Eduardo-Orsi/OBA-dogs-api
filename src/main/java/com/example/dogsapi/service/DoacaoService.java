package com.example.dogsapi.service;

import com.example.dogsapi.model.Doacao;
import com.example.dogsapi.repository.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoacaoService {
    @Autowired
    private DoacaoRepository doacaoRepository;

    public Doacao createDoacao(Doacao doacao) {
        return doacaoRepository.save(doacao);
    }

    public List<Doacao> getAllDoacoes() {
        return doacaoRepository.findAll();
    }

    public Optional<Doacao> getDoacaoById(Long id) {
        return doacaoRepository.findById(id);
    }
} 