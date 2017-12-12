package br.com.frajolas.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.frajolas.app.Pizza;
import br.com.frajolas.app.R;

/**
 * Created by 16254849 on 14/11/2017.
 */

public class PizzasAdapter extends ArrayAdapter<Pizza> {

    public PizzasAdapter(Context ctx, ArrayList<Pizza> objects){
        super(ctx, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, null);
        }

        Pizza item = getItem(position);

        TextView txtNome = (TextView) v.findViewById(R.id.txt_nome);
        TextView txtDescricao = (TextView) v.findViewById(R.id.txt_descricao);
        TextView txtPreco = (TextView) v.findViewById(R.id.txt_preco);
        ImageView imgFoto = (ImageView) v.findViewById(R.id.img);

        txtNome.setText(item.getNome());
        txtDescricao.setText(item.getDescricao());
        txtPreco.setText(item.getPreco());

        Picasso.with(getContext())
                .load("http://10.0.2.2/inf3m/Gabriel/Projeto/CMS/"+item.getFoto())
                .into(imgFoto);

        return v;
    }
}
