package com.example.pastelaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pastelaria.view.ListarBebidasActivity;
import com.example.pastelaria.view.ListarPastelActivity;
import com.example.pastelaria.view.ListarPedidosActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pasteis(View view){
        Intent it = new Intent(this, ListarPastelActivity.class);
        startActivity(it);
    }
    public void bebidas(View view){
        Intent it = new Intent(this, ListarBebidasActivity.class);
        startActivity(it);
    }

    public void pedidos(View view){
        Intent it = new Intent(this, ListarPedidosActivity.class);
        startActivity(it);
    }

}