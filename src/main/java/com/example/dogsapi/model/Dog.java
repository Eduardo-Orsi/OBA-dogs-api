package com.example.dogsapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dogs")
public class Dog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private Integer idade;
    
    @Column(nullable = false)
    private String sexo;
    
    @Column(nullable = false)
    private String porte;
    
    @Column(nullable = false)
    private String raca;
    
    // Default constructor
    public Dog() {}
    
    // Constructor with parameters
    public Dog(String nome, Integer idade, String sexo, String porte, String raca) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.porte = porte;
        this.raca = raca;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Integer getIdade() {
        return idade;
    }
    
    public void setIdade(Integer idade) {
        this.idade = idade;
    }
    
    public String getSexo() {
        return sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public String getPorte() {
        return porte;
    }
    
    public void setPorte(String porte) {
        this.porte = porte;
    }
    
    public String getRaca() {
        return raca;
    }
    
    public void setRaca(String raca) {
        this.raca = raca;
    }
    
    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", sexo='" + sexo + '\'' +
                ", porte='" + porte + '\'' +
                ", raca='" + raca + '\'' +
                '}';
    }
}