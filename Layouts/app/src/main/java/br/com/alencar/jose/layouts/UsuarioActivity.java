package br.com.alencar.jose.layouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Intent i = getIntent();

        String usuario = i.getStringExtra(StorageUsuario.CHAVE_USUARIO);
        Calendar date = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataFormatada = format.format(date.getTime());

        TextView tvUsuario = (TextView) findViewById(R.id.tvUsuario);
        TextView tvDate = (TextView) findViewById(R.id.tvDate);

        tvUsuario.setText(usuario);
        tvDate.setText(dataFormatada);
    }
}
