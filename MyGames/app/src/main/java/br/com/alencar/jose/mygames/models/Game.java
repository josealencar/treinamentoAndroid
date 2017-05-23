package br.com.alencar.jose.mygames.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jose on 22/05/17.
 */

public class Game implements Serializable {
    private String nome;
    private Calendar dataLancamento;
    private String urlImage;
    private String descricao;
    private boolean favorito;

    public Game(String nome, Calendar dataLancamento, String urlImage) {
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.urlImage = urlImage;
    }

    public Game(String nome, Calendar dataLancamento, String urlImage, String descricao) {
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.urlImage = urlImage;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public Calendar getDataLancamento() {
        return dataLancamento;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public String getDataFormatada() {
        if (getDataLancamento() == null) {
            return "Data não informada";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(getDataLancamento().getTime());
    }

    public String getCSV() {
        return String.format("%s,%s,%s,%b", getNome(), getDataFormatada(), getUrlImage(), isFavorito());
    }

    @Override
    public String toString() {
        return getNome() + " - " + getDataFormatada();
    }
}
