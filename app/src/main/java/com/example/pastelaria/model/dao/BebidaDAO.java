package com.example.pastelaria.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pastelaria.model.bean.Bebida;
import com.example.pastelaria.util.Conexao;

import java.util.ArrayList;
import java.util.List;

public class BebidaDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public BebidaDAO(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public String inserirBebida(Bebida bebida){
        ContentValues values = new ContentValues();
        values.put("nomeBebida", bebida.getNomeBebida());
        values.put("descricaoBebida",bebida.getDescricao());
        values.put("precoBebida", bebida.getPreco());
        banco.insert("bebida",null, values);

        return bebida.getNomeBebida() + " inserida com sucesso";
    }

    public Bebida consultarID(int id){
        Bebida bebida = new Bebida();
        Cursor cursor = banco.query("bebida",new String[]{"idBebida", "nomeBebida", "descricaoBebida","precoBebida"},
                "idBebida = ?", new String[]{String.valueOf(id)},null, null, null);
        if(cursor != null){
            while(cursor.moveToFirst()){
                bebida.setIdBebida(cursor.getInt(0));
                bebida.setNomeBebida(cursor.getString(1));
                bebida.setDescricao(cursor.getString(2));
                bebida.setPreco(cursor.getDouble(3));
            }
        }
        return bebida;
    }

    public Bebida consultarNome(Bebida bebida){
        Cursor cursor = banco.query("bebida", new String[]{"idBebida", "nomeBebida","descricaoBebida", "precoBebida"},
                "nomeBebida like ?", new String[]{String.valueOf(bebida.getNomeBebida())}, null, null, null);
        if(cursor != null){
            while(cursor.moveToFirst()){
                bebida.setIdBebida(cursor.getInt(0));
                bebida.setNomeBebida(cursor.getString(1));
                bebida.setDescricao(cursor.getString(2));
                bebida.setPreco(cursor.getDouble(3));
            }
        }
        return bebida;
    }
    public List<Bebida> listar(){
        List<Bebida> bebidas = new ArrayList<>();
        Cursor cursor = banco.query("bebida",
                new String[]{"idBebida", "nomeBebida", "descricaoBebida","precoBebida"},
                null, null, null, null, null);
        while(cursor.moveToNext()){
            Bebida bebida = new Bebida();
            bebida.setIdBebida(cursor.getInt(0));
            bebida.setNomeBebida(cursor.getString(1));
            bebida.setDescricao(cursor.getString(2));
            bebida.setPreco(cursor.getDouble(3));

            bebidas.add(bebida);
        }
        return bebidas;
    }
    public ArrayList<String> listarBebidas(){
        ArrayList<String> bebidas= new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select nomeBebida from bebida", null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                bebidas.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        return bebidas;
    }
    public void atualizar(Bebida bebida){
        ContentValues values = new ContentValues();
        values.put("nomeBebida", bebida.getNomeBebida());
        values.put("descricaoBebida",bebida.getDescricao());
        values.put("precoBebida", bebida.getPreco());

        banco.update("bebida",values, "idBebida = ?", new String[]{String.valueOf(bebida.getIdBebida())});
    }

    public void excluir(Bebida bebida){
        banco.delete("bebida", "idBebida = ?", new String[]{String.valueOf(bebida.getIdBebida())});
    }
}
