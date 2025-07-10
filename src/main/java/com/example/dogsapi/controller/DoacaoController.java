package com.example.dogsapi.controller;

import com.example.dogsapi.model.Doacao;
import com.example.dogsapi.service.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doacoes")
@CrossOrigin(origins = "*")
public class DoacaoController {
    @Autowired
    private DoacaoService doacaoService;

    // POST /api/doacoes - Create new donation
    @PostMapping
    public ResponseEntity<Doacao> createDoacao(@RequestBody Doacao doacao) {
        try {
            Doacao created = doacaoService.createDoacao(doacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // GET /api/doacoes - List all donations
    @GetMapping
    public ResponseEntity<List<Doacao>> getAllDoacoes() {
        List<Doacao> doacoes = doacaoService.getAllDoacoes();
        return ResponseEntity.ok(doacoes);
    }

    // GET /api/doacoes/{id} - Get donation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doacao> getDoacaoById(@PathVariable Long id) {
        Optional<Doacao> doacao = doacaoService.getDoacaoById(id);
        return doacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT /api/doacoes/{id} - Atualizar doação existente
    @PutMapping("/{id}")
    public ResponseEntity<Doacao> updateDoacao(@PathVariable Long id, @RequestBody Doacao doacaoDetails) {
        try {
            Doacao updatedDoacao = doacaoService.updateDoacao(id, doacaoDetails);
            return ResponseEntity.ok(updatedDoacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE /api/doacoes/{id} - Excluir doação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoacao(@PathVariable Long id) {
        try {
            doacaoService.deleteDoacao(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 