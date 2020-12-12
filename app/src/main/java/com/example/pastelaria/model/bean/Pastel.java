package com.example.pastelaria.model.bean;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Pastel implements Serializable {
    private int idPastel;
    private String nomePastel, descricao;
    private double preco;

    public Pastel() {
    }

    public Pastel(int idPastel) {
        this.idPastel = idPastel;
    }

    public int getIdPastel() {
        return idPastel;
    }

    public void setIdPastel(int idPastel) {
        this.idPastel = idPastel;
    }

    public String getNomePastel() {
        return nomePastel;
    }

    public void setNomePastel(String nomePastel) {
        this.nomePastel = nomePastel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @NonNull
    @Override
    public String toString() {
        return nomePastel;
    }
}
