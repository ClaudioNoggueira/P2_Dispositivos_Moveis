package com.example.pastelaria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pastelaria.R;
import com.example.pastelaria.model.bean.Pastel;
import com.example.pastelaria.model.dao.PastelDAO;

public class CadastroPastelActivity extends AppCompatActivity {

    private EditText nome, descricao, preco;
    private PastelDAO dao;
    private Pastel pastel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pastel);

        nome = findViewById(R.id.editNome);
        descricao = findViewById(R.id.editDescricao);
        preco = findViewById(R.id.editPreco);

        dao = new PastelDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("pastel")){
            pastel = (Pastel) it.getSerializableExtra("pastel");
            nome.setText(pastel.getNomePastel());
            descricao.setText(pastel.getDescricao());
            preco.setText(String.valueOf(pastel.getPreco()));
        }
    }

    public void salvarPastel(View view){

        if(pastel == null){
            Pastel pastel = new Pastel();
            pastel.setNomePastel(nome.getText().toString());
            pastel.setDescricao(descricao.getText().toString());
            pastel.setPreco(Double.parseDouble(preco.getText().toString()));

            String status = dao.inserirPastel(pastel);
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        }else{
            pastel.setNomePastel(nome.getText().toString());
            pastel.setDescricao(descricao.getText().toString());
            pastel.setPreco(Double.parseDouble(preco.getText().toString()));
            dao.atualizar(pastel);

            Toast.makeText(this, "Pastel alterado com sucesso", Toast.LENGTH_SHORT).show();
        }

    }
}