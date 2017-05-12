package pdm.ifpb.edu.br.projetofinal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class ConfiguracoesDoControle extends AppCompatActivity {

    private ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes_do_controle);
        setTitle("Configuracões do controle");
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
    }
    public void gravarPorUmMinuto(View view){
        Intent gravacao = new Intent(this, WebCam.class);
        startActivity(gravacao);
    }
    public void verGravacoes(View view){
        Intent gravacoes = new Intent(this, Gravacoes.class);
        startActivity(gravacoes);
    }
    public void assistirAoVivo(View view){
        Intent aovivo = new Intent(this, AoVivo.class);
        startActivity(aovivo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        Log.d("pdm", "Retornando ao ambiente principal.");
        return (super.onOptionsItemSelected(item));
    }

}
