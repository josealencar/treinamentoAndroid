package br.com.alencar.jose.pokedex.models;

import java.util.List;

/**
 * Created by jose on 25/05/17.
 */

public class Pokemon {
    private int id;
    private String nome;
    private String sprite;
    private int altura;
    private Double peso;
    private List<String> tipos;
    private List<Estatistica> estatisticas;

    public Pokemon(int id, String nome, String sprite, int altura, Double peso, List<String> tipos, List<Estatistica> estatisticas) {
        this.id = id;
        this.nome = nome;
        this.sprite = sprite;
        this.altura = altura;
        this.peso = peso;
        this.tipos = tipos;
        this.estatisticas = estatisticas;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSprite() {
        return sprite;
    }

    public int getAltura() {
        return altura;
    }

    public Double getPeso() {
        return peso;
    }

    public List<String> getTipos() {
        return tipos;
    }

    public List<Estatistica> getEstatisticas() {
        return estatisticas;
    }
}
