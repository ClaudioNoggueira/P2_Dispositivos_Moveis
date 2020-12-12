package com.example.pastelaria.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pastelaria.R;
import com.example.pastelaria.model.bean.Bebida;
import com.example.pastelaria.model.bean.Pastel;
import com.example.pastelaria.model.bean.Pedido;
import com.example.pastelaria.model.dao.BebidaDAO;
import com.example.pastelaria.model.dao.PastelDAO;
import com.example.pastelaria.model.dao.PedidoDAO;

import java.util.ArrayList;
import java.util.List;

public class CadastroPedidoActivity extends AppCompatActivity {
    private EditText qtdePastel, qtdeBebida, idPastel, idBebida;
    private PedidoDAO dao;
    private Pedido pedido = null;
/*
public void loadAutoTxt(){
        AutoCompleteTextView autoTxtPastel, autoTxtBebida;
        PastelDAO pastelDAO = new PastelDAO(this);
        ArrayList<String> pasteis =  pastelDAO.listarPasteis();
        ArrayAdapter adapterPastel = new ArrayAdapter(this, android.R.layout.select_dialog_item, pasteis);
        autoTxtPastel.setThreshold(1);

        autoTxtPastel.setAdapter(adapterPastel);

        BebidaDAO bebidaDAO = new BebidaDAO(this);
        ArrayList<String> bebidas = bebidaDAO.listarBebidas();
        ArrayAdapter adapterBebida = new ArrayAdapter(this, android.R.layout.select_dialog_item, bebidas);
        autoTxtBebida.setThreshold(1);

        autoTxtBebida.setAdapter(adapterBebida);

    }
 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);

        qtdePastel = findViewById(R.id.editQtdePastel);
        qtdeBebida = findViewById(R.id.editQtdePastel);
        idPastel = findViewById(R.id.editPastelPedido);
        idBebida = findViewById(R.id.editBebidaPedido);

        dao = new PedidoDAO(this);

        //loadAutoTxt();

        Intent it = getIntent();
        if(it.hasExtra("pedido")){
            pedido = (Pedido) it.getSerializableExtra("pedido");
            qtdePastel.setText(pedido.getQtdePastel().toString());
            qtdeBebida.setText(pedido.getQtdeBebida().toString());
            idPastel.setText(pedido.getIdPastel().toString());
            idBebida.setText(pedido.getIdBebida().toString());
        }
    }

    public void salvarPedido(View view){

        if(pedido == null){
            Pedido pedido = new Pedido();

            pedido.setQtdePastel(Integer.parseInt(qtdePastel.getText().toString()));

            pedido.setQtdeBebida(Integer.parseInt(qtdeBebida.getText().toString()));

           // PastelDAO pastelDAO = new PastelDAO(this);
           // Pastel pastel = new Pastel();
           // pastel.setNomePastel(autoTxtPastel.getText().toString());
           // pastel = pastelDAO.consultarNome(pastel);

          //  AlertDialog testePastel = new AlertDialog.Builder(this).setMessage(pastel.getNomePastel() + " ID" + pastel.getIdPastel()).create();
          //  testePastel.show();
            pedido.setIdPastel(Integer.parseInt(idPastel.getText().toString()));

           // BebidaDAO bebidaDAO = new BebidaDAO(this);
           // Bebida bebida = new Bebida();
           // bebida.setNomeBebida(String.valueOf(autoTxtBebida.getText()));;
          //  bebida = bebidaDAO.consultarNome(bebida);
            pedido.setIdBebida(Integer.parseInt(idBebida.getText().toString()));

            String status = dao.inserirPedido(pedido);
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        }else{
            pedido.setQtdePastel(Integer.parseInt(qtdePastel.getText().toString()));
            pedido.setQtdeBebida(Integer.parseInt(qtdeBebida.getText().toString()));

           // PastelDAO pastelDAO = new PastelDAO(this);
          //  Pastel pastel = new Pastel();
          //  pastel.setNomePastel(String.valueOf(autoTxtPastel.getText()));
          //  pastel = pastelDAO.consultarNome(pastel);
            pedido.setIdPastel(Integer.parseInt(idPastel.getText().toString()));

          //  BebidaDAO bebidaDAO = new BebidaDAO(this);
          //  Bebida bebida = new Bebida();
          //  bebida.setNomeBebida(String.valueOf(autoTxtBebida.getText()));;
          //  bebida = bebidaDAO.consultarNome(bebida);
            pedido.setIdBebida(Integer.parseInt(idBebida.getText().toString()));

            dao.atualizar(pedido);

            Toast.makeText(this, "Pedido alterado com sucesso", Toast.LENGTH_SHORT).show();
        }
    }
}