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
import com.example.pastelaria.model.bean.Bebida;
import com.example.pastelaria.model.dao.BebidaDAO;

import java.util.ArrayList;
import java.util.List;

public class ListarBebidasActivity extends AppCompatActivity {
    private ListView listView;
    private BebidaDAO dao;
    private List<Bebida> bebidas;
    private List<Bebida> bebidasFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_bebidas);

        listView = findViewById(R.id.lista_bebidas);
        dao = new BebidaDAO(this);
        bebidas = dao.listar();
        bebidasFiltradas.addAll(bebidas);

        //ArrayAdapter<Bebida> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bebidasFiltradas);

        AdaptadorBebida adaptador = new AdaptadorBebida(bebidasFiltradas, this);

        listView.setAdapter(adaptador);

        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal_bebida, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.pesquisarBebida).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procurarBebida(newText);
                return false;
            }
        });

        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto_bebida, menu);
    }

    public void procurarBebida(String nome){
        bebidasFiltradas.clear();
        for(Bebida bebida : bebidas){
            if(bebida.getNomeBebida().toLowerCase().contains(nome.toLowerCase())){
                bebidasFiltradas.add(bebida);
            }
        }
        listView.invalidateViews();
    }

    public void cadastrarBebida(MenuItem item){
        Intent it = new Intent(this, CadastroBebidaActivity.class);
        startActivity(it);
    }

    public void alterarBebida(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Bebida bebidaAlterar = bebidasFiltradas.get(menuInfo.position);

        Intent it = new Intent(this, CadastroBebidaActivity.class);
        it.putExtra("bebida", bebidaAlterar);
        startActivity(it);
    }

    public void excluirBebida(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Bebida bebidaExcluir = bebidasFiltradas.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja excluir a bebida '"+bebidaExcluir.getNomeBebida()+"'?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bebidasFiltradas.remove(bebidaExcluir);
                        bebidas.remove(bebidaExcluir);
                        dao.excluir(bebidaExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

@Override
    public void onResume(){
        super.onResume();
        bebidas = dao.listar();
        bebidasFiltradas.clear();
        bebidasFiltradas.addAll(bebidas);
        listView.invalidateViews();
    }
}