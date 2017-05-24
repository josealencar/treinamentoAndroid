package br.com.alencar.jose.pokedex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.alencar.jose.pokedex.asynctasks.GetPokemonAsyncTask;

public class MainActivity extends AppCompatActivity {

    private TextView tvNome;
    private EditText etNumber;
    private Button btSearch;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        tvNome = (TextView) findViewById(R.id.tv_nome);
        etNumber = (EditText) findViewById(R.id.et_number);
        btSearch = (Button) findViewById(R.id.bt_search);
        spinner = (ProgressBar) findViewById(R.id.spinner);
    }

    public void renderizarNome(String nomePokemon) {
        tvNome.setText(nomePokemon);
        esconderSpinner();
    }

    public void mostrarSpinner() {
        spinner.setVisibility(View.VISIBLE);
    }

    public void esconderSpinner() {
        spinner.setVisibility(View.GONE);
    }

    public void pesquisar(View view) {
        Integer number = Integer.parseInt(etNumber.getText().toString());
        new GetPokemonAsyncTask(this).execute(number);
        etNumber.setText(null);
    }
}
