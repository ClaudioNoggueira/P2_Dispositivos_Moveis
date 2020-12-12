package com.example.pastelaria.view;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.pastelaria.R;
import com.example.pastelaria.model.bean.Pedido;
import com.example.pastelaria.model.dao.PedidoDAO;

import java.util.ArrayList;
import java.util.List;

public class ListarPedidosActivity extends AppCompatActivity {

    private ListView listView;
    private PedidoDAO dao;
    private List<Pedido> pedidos;
    private List<Pedido> pedidosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pedidos);

        listView = findViewById(R.id.lista_pedidos);
        dao = new PedidoDAO(this);
        pedidos = dao.listar();
        pedidosFiltrados.addAll(pedidos);

        //ArrayAdapter<Bebida> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bebidasFiltradas);

        AdaptadorPedido adaptador = new AdaptadorPedido(pedidosFiltrados, this);

        listView.setAdapter(adaptador);

        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal_pedido, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.pesquisarPedido).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procurarPedido(newText);
                return false;
            }
        });

        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto_pedido, menu);
    }

    public void procurarPedido(String nome){
        pedidosFiltrados.clear();
        for(Pedido pedido : pedidos){
            try{
                if((pedido.getIdPedido() == Integer.parseInt(nome))){
                    pedidosFiltrados.add(pedido);
                }
            }catch(NullPointerException e){

            }catch(NumberFormatException e){

            }
        }
        listView.invalidateViews();
    }

    public void cadastrarPedido(MenuItem item){
        Intent it = new Intent(this, CadastroPedidoActivity.class);
        startActivity(it);
    }

    public void alterarPedido(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Pedido pedidoAlterar = pedidosFiltrados.get(menuInfo.position);

        Intent it = new Intent(this, CadastroPedidoActivity.class);
        it.putExtra("pedido", pedidoAlterar);
        startActivity(it);
    }

    public void excluirPedido(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Pedido pedidoExcluir = pedidosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja excluir o pedido nº '"+pedidoExcluir.getIdPedido()+"'?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pedidosFiltrados.remove(pedidoExcluir);
                        pedidos.remove(pedidoExcluir);
                        dao.excluir(pedidoExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    @Override
    public void onResume(){
        super.onResume();
        pedidos = dao.listar();
        pedidosFiltrados.clear();
        pedidosFiltrados.addAll(pedidos);
        listView.invalidateViews();
    }
}