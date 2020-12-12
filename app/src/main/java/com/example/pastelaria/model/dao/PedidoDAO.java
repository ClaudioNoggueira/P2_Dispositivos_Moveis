package com.example.pastelaria.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pastelaria.model.bean.Pedido;
import com.example.pastelaria.util.Conexao;

import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private Conexao con;
    private SQLiteDatabase banco;

    public PedidoDAO(Context context){
        con = new Conexao(context);
        banco = con.getWritableDatabase();
    }

    public String inserirPedido(Pedido pedido){
        ContentValues values = new ContentValues();
        values.put("idPastel", pedido.getIdPastel());
        values.put("idBebida",pedido.getIdBebida());
        values.put("qtdePastel", pedido.getQtdePastel());
        values.put("qtdeBebida", pedido.getQtdeBebida());
        long id = banco.insert("pedido",null, values);

        return "Pedido nº " + id + " inserido com sucesso";
    }

    public Pedido consultarID(Pedido pedido){
        Cursor cursor = banco.query("pedido",new String[]{"idPedido", "idPastel", "idBebida","qtdePastel","qtdeBebida"},
                "idPedido = ?", new String[]{String.valueOf(pedido.getIdPedido())},null, null, null);
        if(cursor != null){
            while(cursor.moveToFirst()){
                pedido.setIdPedido(cursor.getInt(0));
                pedido.setIdPastel(cursor.getInt(1));
                pedido.setIdBebida(cursor.getInt(2));
                pedido.setQtdePastel(cursor.getInt(3));
                pedido.setQtdeBebida(cursor.getInt(4));
            }
        }
        return pedido;
    }

    public List<Pedido> listar(){
        List<Pedido> pedidos = new ArrayList<>();
        Cursor cursor = banco.query("pedido",
                new String[]{"idPedido", "idPastel", "idBebida","qtdePastel","qtdeBebida"},
                null, null, null, null, null);
        while(cursor.moveToNext()){
            Pedido pedido = new Pedido();
            pedido.setIdPedido(cursor.getInt(0));
            pedido.setIdPastel(cursor.getInt(1));
            pedido.setIdBebida(cursor.getInt(2));
            pedido.setQtdePastel(cursor.getInt(3));
            pedido.setQtdeBebida(cursor.getInt(4));

            pedidos.add(pedido);
        }
        return pedidos;
    }
    public void atualizar(Pedido pedido){
        ContentValues values = new ContentValues();
        values.put("idPastel", pedido.getIdPastel());
        values.put("idBebida",pedido.getIdBebida());
        values.put("qtdePastel", pedido.getQtdePastel());
        values.put("qtdeBebida", pedido.getIdBebida());

        banco.update("pedido",values, "idPedido = ?", new String[]{String.valueOf(pedido.getIdPedido())});
    }

    public void excluir(Pedido pedido){
        banco.delete("pedido", "idPedido = ?", new String[]{String.valueOf(pedido.getIdPedido())});
    }
}
