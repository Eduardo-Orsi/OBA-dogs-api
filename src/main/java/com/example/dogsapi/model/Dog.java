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

    @Column(nullable = false)
    private String especie;

    @Lob
    @Column(nullable = true)
    private byte[] imagem;
    
    // Default constructor
    public Dog() {}
    
    // Constructor with parameters
    public Dog(String nome, Integer idade, String sexo, String porte, String raca, String especie, byte[] imagem) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.porte = porte;
        this.raca = raca;
        this.especie = especie;
        this.imagem = imagem;
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
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
                ", especie='" + especie + '\'' +
                '}';
    }
}