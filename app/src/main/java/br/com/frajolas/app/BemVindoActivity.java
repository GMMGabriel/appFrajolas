package br.com.frajolas.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class BemVindoActivity extends AppCompatActivity {

    // Essa classe faz o efeito inicial do app,
    // aparecendo a logo da pizzaria por 5 segundos
    // logo quando o app é iniciado para depois
    // a página principal ser aberta.

    TextView txtBemVindo;
    ImageView imgBemVindo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        txtBemVindo = (TextView) findViewById(R.id.txt_bemVindo);
        imgBemVindo = (ImageView) findViewById(R.id.img_bemVindo);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtBemVindo.setVisibility(View.GONE);
                imgBemVindo.setVisibility(View.VISIBLE);
            }
        }, 2500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(BemVindoActivity.this, ListarPizzasActivity.class);
                ActivityOptionsCompat padrao = ActivityOptionsCompat.makeCustomAnimation(BemVindoActivity.this,
                        R.anim.mover_esquerda, R.anim.fade_out);
                ActivityCompat.startActivity(BemVindoActivity.this, intent, padrao.toBundle());
            }
        }, 5000);
    }
}
