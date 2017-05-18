package br.com.alencar.jose.layouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BotoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botoes);
    }

    public void redirecionarMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void redirecionarExercicio2(View view) {
        Intent i = new Intent(this, Exercicio2Activity.class);
        startActivity(i);
    }

    public void redirecionarExercicio3(View view) {
        Intent i = new Intent(this, Exercicio3Activity.class);
        startActivity(i);
    }

    public void redirecionarRelativo(View view) {
        Intent i = new Intent(this, RelativoActivity.class);
        startActivity(i);
    }
}
