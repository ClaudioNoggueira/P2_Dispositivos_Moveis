package com.example.pastelaria.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "pastelaria.db";
    private static final int version = 1;

    public Conexao(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table pastel(idPastel integer primary key autoincrement," +
                " nomePastel varchar(50)," +
                " descricaoPastel varchar(150), " +
                " precoPastel decimal(5,2))");

        db.execSQL("create table bebida(idBebida integer primary key autoincrement," +
                " nomeBebida varchar(50)," +
                " descricaoBebida varchar(150)," +
                " precoBebida decimal(5,2));");

        db.execSQL("create table pedido(idPedido integer primary key autoincrement," +
                " qtdePastel integer," +
                " qtdeBebida integer," +
                " idPastel integer," +
                " idBebida integer," +
                " foreign key(idPastel) references pastel(idPastel)," +
                " foreign key(idBebida) references bebida(idBebida))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
