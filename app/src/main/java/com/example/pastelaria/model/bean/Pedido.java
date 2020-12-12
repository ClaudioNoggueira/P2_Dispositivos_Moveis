package com.example.pastelaria.model.bean;

import java.io.Serializable;

public class Pedido implements Serializable {
    private Integer idPedido, idPastel, idBebida, qtdePastel, qtdeBebida;

    public Pedido() {
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdPastel() {
        return idPastel;
    }

    public void setIdPastel(Integer idPastel) {
        this.idPastel = idPastel;
    }

    public Integer getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(Integer idBebida) {
        this.idBebida = idBebida;
    }

    public Integer getQtdePastel() {
        return qtdePastel;
    }

    public void setQtdePastel(Integer qtdePastel) {
        this.qtdePastel = qtdePastel;
    }

    public Integer getQtdeBebida() {
        return qtdeBebida;
    }

    public void setQtdeBebida(Integer qtdeBebida) {
        this.qtdeBebida = qtdeBebida;
    }
}
