package br.com.alencar.jose.mygames.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jose on 22/05/17.
 */

public class Game {
    private String nome;
    private Calendar dataLancamento;
    private String urlImage;

    public Game(String nome, Calendar dataLancamento, String urlImage) {
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.urlImage = urlImage;
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

    public String getDataFormatada() {
        if (getDataLancamento() == null) {
            return "Data não informada";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(getDataLancamento().getTime());
    }

    @Override
    public String toString() {
        return getNome() + " - " + getDataFormatada();
    }
}
