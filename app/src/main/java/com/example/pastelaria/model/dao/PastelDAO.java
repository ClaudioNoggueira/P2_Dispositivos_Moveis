package com.example.pastelaria.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pastelaria.model.bean.Pastel;
import com.example.pastelaria.util.Conexao;

import java.util.ArrayList;
import java.util.List;

public class PastelDAO {
    private Conexao con;
    private SQLiteDatabase banco;

    public PastelDAO(Context context){
        con = new Conexao(context);
        banco = con.getWritableDatabase();
    }

    public String inserirPastel(Pastel pastel){
        ContentValues values = new ContentValues();
        values.put("nomePastel", pastel.getNomePastel());
        values.put("descricaoPastel",pastel.getDescricao());
        values.put("precoPastel", pastel.getPreco());
        long id = banco.insert("pastel",null, values);

        return pastel.getNomePastel() + " inserido com sucesso";
    }

    public Pastel consultarID(int id){
        Pastel pastel = new Pastel();
        Cursor cursor = banco.query("pastel",new String[]{"idPastel", "nomePastel", "descricaoPastel","precoPastel"},
                "idPastel = ?", new String[]{String.valueOf(id)},null, null, null);
        if(cursor != null){
            while(cursor.moveToFirst()){
                pastel.setIdPastel(cursor.getInt(0));
                pastel.setNomePastel(cursor.getString(1));
                pastel.setDescricao(cursor.getString(2));
                pastel.setPreco(cursor.getDouble(3));
            }
        }
        return pastel;
    }
    public Pastel consultarNome(Pastel pastel){
        Cursor cursor = banco.query("pastel",new String[]{"idPastel", "nomePastel", "descricaoPastel","precoPastel"},
                "nomePastel like ?", new String[]{String.valueOf(pastel.getNomePastel())},null, null, null);
        if(cursor != null){
            while(cursor.moveToFirst()){
                pastel.setIdPastel(cursor.getInt(0));
                pastel.setNomePastel(cursor.getString(1));
                pastel.setDescricao(cursor.getString(2));
                pastel.setPreco(cursor.getDouble(3));
            }
        }
        return pastel;
    }
    public List<Pastel> listar(){
        List<Pastel> pasteis = new ArrayList<>();
        Cursor cursor = banco.query("pastel",
                new String[]{"idPastel", "nomePastel", "descricaoPastel","precoPastel"},
                null, null, null, null, null);
        while(cursor.moveToNext()){
            Pastel pastel = new Pastel();
            pastel.setIdPastel(cursor.getInt(0));
            pastel.setNomePastel(cursor.getString(1));
            pastel.setDescricao(cursor.getString(2));
            pastel.setPreco(cursor.getDouble(3));

            pasteis.add(pastel);
        }
        return pasteis;
    }
    public ArrayList<String> listarPasteis(){
        ArrayList<String> pasteis = new ArrayList<>();
        Cursor cursor = banco.rawQuery("Select nomePastel from pastel", null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                pasteis.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        return pasteis;
    }
    public void atualizar(Pastel pastel){
        ContentValues values = new ContentValues();
        values.put("nomePastel", pastel.getNomePastel());
        values.put("descricaoPastel",pastel.getDescricao());
        values.put("precoPastel", pastel.getPreco());

        banco.update("pastel",values, "idPastel = ?", new String[]{String.valueOf(pastel.getIdPastel())});
    }

    public void excluir(Pastel p){
        banco.delete("pastel", "idPastel = ?", new String[]{String.valueOf(p.getIdPastel())});
    }
}
