package com.example.pastelaria.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pastelaria.R;
import com.example.pastelaria.model.bean.Bebida;
import com.example.pastelaria.model.bean.Pastel;

import java.util.List;

public class AdaptadorBebida extends BaseAdapter {
    private List<Bebida> bebidas;
    private Activity activity;

    public AdaptadorBebida(List<Bebida> bebidas, Activity activity) {
        this.bebidas = bebidas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return bebidas.size();
    }

    @Override
    public Object getItem(int position) {
        return bebidas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return bebidas.get(position).getIdBebida();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item, parent, false);

        TextView id = view.findViewById(R.id.txtID);
        TextView nome = view.findViewById(R.id.txtNome);
        TextView descricao = view.findViewById(R.id.txtDescricao);
        TextView preco = view.findViewById(R.id.txtPreco);

        Bebida bebida = bebidas.get(position);

        id.setText(String.valueOf(bebida.getIdBebida()));
        nome.setText(bebida.getNomeBebida());
        descricao.setText(bebida.getDescricao());
        preco.setText("R$ " + String.valueOf(bebida.getPreco()).replace(".",","));

        return view;
    }
}
