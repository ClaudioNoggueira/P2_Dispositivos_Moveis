package com.example.pastelaria.model.bean;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Bebida implements Serializable {
    private Integer idBebida;
    private String nomeBebida, descricao;
    private double preco;

    public Bebida() {
    }

    public Bebida(Integer idBebida) {
        this.idBebida = idBebida;
    }

    public Integer getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(Integer idBebida) {
        this.idBebida = idBebida;
    }

    public String getNomeBebida() {
        return nomeBebida;
    }

    public void setNomeBebida(String nomeBebida) {
        this.nomeBebida = nomeBebida;
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
        return nomeBebida;
    }
}
