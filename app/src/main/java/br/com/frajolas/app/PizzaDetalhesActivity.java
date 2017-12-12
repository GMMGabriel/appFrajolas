package br.com.frajolas.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.Rating;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONObject;

import java.util.HashMap;

public class PizzaDetalhesActivity extends AppCompatActivity {

    ImageView img;
    TextView txtDesccricao, txtPreco;
    RatingBar rtbAvaliacao, rtbMedia;
    Float avaliacao;
    CollapsingToolbarLayout toolbar_layout;
    ProgressBar pgbSalnvado;
    Button btnSalvar;

    TextView txtMedia;

    Float media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detalhes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        img = (ImageView) findViewById(R.id.img);
        txtDesccricao = (TextView) findViewById(R.id.txt_descricao);
        txtPreco = (TextView) findViewById(R.id.txt_preco);
        rtbAvaliacao = (RatingBar) findViewById(R.id.rtb_Avaliacao);
        rtbMedia = (RatingBar) findViewById(R.id.rtb_Media);
        txtMedia = (TextView) findViewById(R.id.txt_Media);
        toolbar_layout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        pgbSalnvado = (ProgressBar) findViewById(R.id.pgbSalvando);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        media = Float.parseFloat(PizzaStatic.piz.getMedia().toString());

        rtbMedia.setRating(media);
        txtMedia.setText(media.toString());

        rtbAvaliacao.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(PizzaDetalhesActivity.this, String.valueOf(rtbAvaliacao.getRating()), Toast.LENGTH_SHORT).show();
                avaliacao = Float.parseFloat(String.valueOf(rtbAvaliacao.getRating()));
            }
        });

        this.setTitle(PizzaStatic.piz.getNome());
        txtDesccricao.setText(PizzaStatic.piz.getDescricao());
        txtPreco.setText(PizzaStatic.piz.getPreco());

        setFotoBackground("http://10.0.2.2/inf3m/Gabriel/Projeto/CMS/"+PizzaStatic.piz.getFoto());

    }

    @Override
    public void finish() {
        super.finish();

        Intent intent = new Intent(PizzaDetalhesActivity.this, ListarPizzasActivity.class);
        ActivityOptionsCompat padrao = ActivityOptionsCompat.makeCustomAnimation(PizzaDetalhesActivity.this,
                R.anim.fade_in, R.anim.mover_direita);
        ActivityCompat.startActivity(PizzaDetalhesActivity.this, intent, padrao.toBundle());

    }

    public void inserir(View view) {

        // Manda os dados do app para a página API.

        final String url = "http://10.0.2.2/inf3m/Gabriel/Projeto/CMS/inserir.php";
        final HashMap<String, String> valores = new HashMap<>();
        valores.put("nota", avaliacao.toString());
        valores.put("idProduto", String.valueOf(PizzaStatic.piz.getId()));

        new AsyncTask<Void, Void, Void>(){

            Boolean sucesso = false;
            String mensagem = null;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pgbSalnvado.setVisibility(View.VISIBLE);
                btnSalvar.setVisibility(View.GONE);
            }
            @Override
            protected Void doInBackground(Void... voids) {

                SystemClock.sleep(2000);

                String resultado = Http.post(url, valores);

                try {
                    // Transformação json para objeto
                    JSONObject jsonObject = new JSONObject(resultado);
                    sucesso = jsonObject.getBoolean("sucesso");
                    mensagem = jsonObject.getString("mensagem");
                } catch (Exception e) {
                    e.printStackTrace();
                    sucesso = false;
                    mensagem = "Erro ao inserir";
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                pgbSalnvado.setVisibility(View.GONE);
                btnSalvar.setVisibility(View.VISIBLE);

                Toast.makeText(PizzaDetalhesActivity.this, mensagem, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(PizzaDetalhesActivity.this, ListarPizzasActivity.class);
                ActivityOptionsCompat padrao = ActivityOptionsCompat.makeCustomAnimation(PizzaDetalhesActivity.this,
                        R.anim.mover_esquerda, R.anim.fade_out);
                ActivityCompat.startActivity(PizzaDetalhesActivity.this, intent, padrao.toBundle());

                finish();
            }
        }.execute();

    }

    // Coloca a imagem inteira na caixa de titulo

    private void setFotoBackground(String linkFoto){

        Picasso.with(PizzaDetalhesActivity.this).load(linkFoto).into(new Target(){

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                toolbar_layout.setBackground(new BitmapDrawable(PizzaDetalhesActivity.this.getResources(),
                        bitmap));
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                Log.d("TAG", "FAILED");
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {
                Log.d("TAG", "Prepare Load");
            }
        });
    }

    public void Ligar(View view) {

        String url="tel:40028922";
        if (url.startsWith("tel:"))
        {
            Intent intent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse(url));
            startActivity(intent);
        }

    }
}
