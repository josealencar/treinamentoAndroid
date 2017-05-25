package br.com.alencar.jose.pokedex.models;

/**
 * Created by jose on 25/05/17.
 */

public class Estatistica {
    private String descricao;
    private int valor;

    public Estatistica(String descricao, int valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getValor() {
        return valor;
    }
}
