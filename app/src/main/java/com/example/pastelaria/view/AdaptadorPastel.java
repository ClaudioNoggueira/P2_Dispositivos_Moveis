package com.example.pastelaria.view;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pastelaria.R;
import com.example.pastelaria.model.bean.Pastel;

import java.util.List;

public class AdaptadorPastel extends BaseAdapter {
    private List<Pastel> pasteis;
    private Activity activity;

    public AdaptadorPastel(List<Pastel> pasteis, Activity activity) {
        this.pasteis = pasteis;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return pasteis.size();
    }

    @Override
    public Object getItem(int position) {
        return pasteis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pasteis.get(position).getIdPastel();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item, parent, false);

        TextView id = view.findViewById(R.id.txtID);
        TextView nome = view.findViewById(R.id.txtNome);
        TextView descricao = view.findViewById(R.id.txtDescricao);
        TextView preco = view.findViewById(R.id.txtPreco);

        Pastel pastel = pasteis.get(position);

        id.setText(String.valueOf(pastel.getIdPastel()));
        nome.setText(pastel.getNomePastel());
        descricao.setText(pastel.getDescricao());
        preco.setText("R$ " + String.valueOf(pastel.getPreco()).replace(".",","));

        return view;
    }
}
