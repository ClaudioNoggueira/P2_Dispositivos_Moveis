package com.example.pastelaria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.pastelaria.R;
import com.example.pastelaria.model.bean.Pastel;
import com.example.pastelaria.model.dao.PastelDAO;

import java.util.ArrayList;
import java.util.List;

public class ListarPastelActivity extends AppCompatActivity {

    private ListView listView;
    private PastelDAO dao;
    private List<Pastel> pasteis;
    private List<Pastel> pasteisFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pastel);

        listView = findViewById(R.id.lista_pasteis);
        dao = new PastelDAO(this);
        pasteis = dao.listar();
        pasteisFiltrados.addAll(pasteis);

        AdaptadorPastel adaptador = new AdaptadorPastel(pasteisFiltrados, this);
        listView.setAdapter(adaptador);

        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal_pastel, menu);
        
        SearchView sv = (SearchView) menu.findItem(R.id.pesquisarPastel).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procurarPastel(newText);
                return false;
            }
        });

        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto_pastel, menu);
    }

    public void procurarPastel(String nome){
        pasteisFiltrados.clear();
        for(Pastel pastel : pasteis){
            if(pastel.getNomePastel().toLowerCase().contains(nome.toLowerCase())){
                pasteisFiltrados.add(pastel);
            }
        }
        listView.invalidateViews();
    }

    public void cadastrarPastel(MenuItem item){
        Intent it = new Intent(this, CadastroPastelActivity.class);
        startActivity(it);
    }

    public void alterarPastel(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Pastel pastelAlterar = pasteisFiltrados.get(menuInfo.position);

        Intent it = new Intent(this, CadastroPastelActivity.class);
        it.putExtra("pastel", pastelAlterar);
        startActivity(it);
    }

    public void excluirPastel(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Pastel pastelExcluir = pasteisFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja excluir o pastel '"+pastelExcluir.getNomePastel()+"'?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pasteisFiltrados.remove(pastelExcluir);
                        pasteis.remove(pastelExcluir);
                        dao.excluir(pastelExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    @Override
    public void onResume(){
        super.onResume();
        pasteis = dao.listar();
        pasteisFiltrados.clear();
        pasteisFiltrados.addAll(pasteis);
        listView.invalidateViews();
    }
}