package pdm.ifpb.edu.br.projetofinal;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class Gravacoes extends AppCompatActivity {

    private ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravacoes);
        setTitle("Gravações");
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
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

    public void abrirGravacao(View view){
        Intent intent = new Intent(this,Gravacao.class);
        startActivity(intent);
    }
}
