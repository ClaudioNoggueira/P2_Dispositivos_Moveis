package com.example.pastelaria.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pastelaria.R;
import com.example.pastelaria.model.bean.Bebida;
import com.example.pastelaria.model.bean.Pastel;
import com.example.pastelaria.model.bean.Pedido;
import com.example.pastelaria.model.dao.BebidaDAO;
import com.example.pastelaria.model.dao.PastelDAO;

import java.util.List;

public class AdaptadorPedido extends BaseAdapter {
    private List<Pedido> pedidos;
    private Activity activity;

    public AdaptadorPedido(List<Pedido> pedidos, Activity activity) {
        this.pedidos = pedidos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return pedidos.size();
    }

    @Override
    public Object getItem(int position) {
        return pedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pedidos.get(position).getIdBebida();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_pedido, parent, false);

        TextView idPedido = view.findViewById(R.id.txtIDPedido);
        TextView nomePastel = view.findViewById(R.id.txtPastel);
        TextView nomeBebida = view.findViewById(R.id.txtBebida);
        TextView qtdePastel = view.findViewById(R.id.txtQtdePastel);
        TextView qtdeBebida = view.findViewById(R.id.txtQtdeBebida);

        Pedido pedido = pedidos.get(position);

        idPedido.setText(String.valueOf(pedido.getIdPedido()));

        nomePastel.setText(String.valueOf(pedido.getIdPastel()));

        nomeBebida.setText(String.valueOf(pedido.getIdBebida()));

        qtdePastel.setText(String.valueOf(pedido.getQtdePastel()));
        qtdeBebida.setText(String.valueOf(pedido.getQtdeBebida()));

        return view;
    }
}
