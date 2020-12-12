package com.example.pastelaria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pastelaria.R;
import com.example.pastelaria.model.bean.Bebida;
import com.example.pastelaria.model.dao.BebidaDAO;
public class CadastroBebidaActivity extends AppCompatActivity {

    private EditText nome, descricao, preco;
    private BebidaDAO dao;
    private Bebida bebida = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_bebida);

        nome = findViewById(R.id.editNomeBebida);
        descricao = findViewById(R.id.editDescricaoBebida);
        preco = findViewById(R.id.editPrecoBebida);

        dao = new BebidaDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("bebida")){
            bebida = (Bebida) it.getSerializableExtra("bebida");
            nome.setText(bebida.getNomeBebida());
            descricao.setText(bebida.getDescricao());
            preco.setText(String.valueOf(bebida.getPreco()));
        }
    }

    public void salvarBebida(View view){

        if(bebida == null){
            Bebida bebida = new Bebida();
            bebida.setNomeBebida(nome.getText().toString());
            bebida.setDescricao(descricao.getText().toString());
            bebida.setPreco(Double.parseDouble(preco.getText().toString()));

            String status = dao.inserirBebida(bebida);
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        }else{
            bebida.setNomeBebida(nome.getText().toString());
            bebida.setDescricao(descricao.getText().toString());
            bebida.setPreco(Double.parseDouble(preco.getText().toString()));
            dao.atualizar(bebida);

            Toast.makeText(this, "Bebida alterada com sucesso", Toast.LENGTH_SHORT).show();
        }
    }
}