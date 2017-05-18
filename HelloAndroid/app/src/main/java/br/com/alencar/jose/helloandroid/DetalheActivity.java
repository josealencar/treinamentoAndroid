package br.com.alencar.jose.helloandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Intent i = getIntent();
        String nome = i.getStringExtra("NOME");


        TextView txtLabel = (TextView) findViewById(R.id.txtLabel);
        txtLabel.setText(nome);
    }
}
