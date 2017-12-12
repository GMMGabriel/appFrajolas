package br.com.frajolas.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListarPizzasActivity extends AppCompatActivity {

    ListView list_view;
    PizzasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pizzas);
        this.setTitle("Todas as pizzas");

        list_view = (ListView) findViewById(R.id.list_pizza);
        adapter = new PizzasAdapter(this, new ArrayList<Pizza>());
        list_view.setAdapter(adapter);

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PizzaStatic.piz = adapter.getItem(position);

                Intent intent = new Intent(ListarPizzasActivity.this, PizzaDetalhesActivity.class);
                ActivityOptionsCompat padrao = ActivityOptionsCompat.makeCustomAnimation(ListarPizzasActivity.this,
                        R.anim.mover_esquerda, R.anim.fade_out);
                ActivityCompat.startActivity(ListarPizzasActivity.this, intent, padrao.toBundle());

                finish();
            }
        });

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                String returnJason = Http.get("http://10.0.2.2/inf3m/Gabriel/Projeto/CMS/selecionar.php");
                Log.d("TAG", returnJason);

                try {

                    JSONArray jsonArray = new JSONArray(returnJason);
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject item = jsonArray.getJSONObject(i);

                        adapter.add(Pizza.create(
                                item.getInt("idProduto"),
                                item.getString("nome"),
                                item.getString("descricao"),
                                item.getString("preco"),
                                item.getString("imagem"),
                                item.getDouble("mediaAva")
                                )
                        );
                    }

                } catch (JSONException e) {
                    Log.e("ERRO", e.getMessage());
                }
                return null;
            }
        }.execute();

    }
}