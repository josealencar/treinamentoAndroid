package br.com.alencar.jose.pokedex;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.alencar.jose.pokedex.asynctasks.GetPokemonAsyncTask;
import br.com.alencar.jose.pokedex.models.Estatistica;
import br.com.alencar.jose.pokedex.models.Pokemon;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_MAIN = "TAG_MAIN";
    private TextView tvNome, tvIdPokemon, tvNomePokemon, tvAlturaPokemon, tvPesoPokemon, tvTypesPokemon;
    private EditText etNumber;
    private ProgressBar spinner, pbSpeed, pbSpecialDefense, pbSpecialAttack, pbDefense, pbAttack, pbHp;

    private CardView cvPokemon;
    private ImageView ivPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        tvNome = (TextView) findViewById(R.id.tv_nome);
        etNumber = (EditText) findViewById(R.id.et_number);
        spinner = (ProgressBar) findViewById(R.id.spinner);
        tvIdPokemon = (TextView) findViewById(R.id.tv_id_pokemon);
        tvNomePokemon = (TextView) findViewById(R.id.tv_nome_pokemon);
        tvAlturaPokemon = (TextView) findViewById(R.id.tv_altura_pokemon);
        tvPesoPokemon = (TextView) findViewById(R.id.tv_peso_pokemon);
        tvTypesPokemon = (TextView) findViewById(R.id.tv_types_pokemon);
        cvPokemon = (CardView) findViewById(R.id.cv_pokemon);
        ivPokemon = (ImageView) findViewById(R.id.iv_pokemon);
        pbSpeed = (ProgressBar) findViewById(R.id.pb_speed);
        pbSpecialDefense = (ProgressBar) findViewById(R.id.pb_special_defense);
        pbSpecialAttack = (ProgressBar) findViewById(R.id.pb_special_attack);
        pbDefense = (ProgressBar) findViewById(R.id.pb_defense);
        pbAttack = (ProgressBar) findViewById(R.id.pb_attack);
        pbHp = (ProgressBar) findViewById(R.id.pb_hp);
    }

    public void atualizarMensagem(String mensagem) {
        tvNome.setText(mensagem);
        esconderSpinner();
    }

    public void mostrarSpinner() {
        spinner.setVisibility(View.VISIBLE);
    }

    public void esconderSpinner() {
        spinner.setVisibility(View.GONE);
    }

    public void pesquisar(View view) {
        atualizarMensagem(null);
        cvPokemon.setVisibility(View.GONE);
        if (isNetworkAvailable()) {
            try {
                Integer number = Integer.parseInt(etNumber.getText().toString());
                new GetPokemonAsyncTask(this).execute(number);
                etNumber.setText(null);
            } catch (NumberFormatException nfe) {
                Log.e(TAG_MAIN, nfe.getMessage(), nfe);
                atualizarMensagem(getResources().getString(R.string.invalid_number));
            }
        } else {
            atualizarMensagem(getResources().getString(R.string.network_unnavailable));
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void popularPokemon(Pokemon pokemon) {
        Picasso.with(this).load(pokemon.getSprite()).into(ivPokemon);
        tvIdPokemon.setText(montaString(R.string.base_id_text, String.valueOf(pokemon.getId())));
        tvNomePokemon.setText(montaString(R.string.base_name_pokemon, pokemon.getNome()));
        tvPesoPokemon.setText(String.format("%s %.3f", getStringResources(R.string.base_weight_pokemon), pokemon.getPeso()).replace(".", ","));
        tvAlturaPokemon.setText(montaString(R.string.base_height_pokemon, String.valueOf(pokemon.getAltura())));
        tvTypesPokemon.setText(montaString(R.string.base_types_pokemon, concatenaTipos(pokemon.getTipos())));

        for (Estatistica estatistica : pokemon.getEstatisticas()) {
            switch (estatistica.getDescricao()) {
                case "speed":
                    pbSpeed.setProgress(estatistica.getValor());
                    break;
                case "special-defense":
                    pbSpecialDefense.setProgress(estatistica.getValor());
                    break;
                case "special-attack":
                    pbSpecialAttack.setProgress(estatistica.getValor());
                    break;
                case "defense":
                    pbDefense.setProgress(estatistica.getValor());
                    break;
                case "attack":
                    pbAttack.setProgress(estatistica.getValor());
                    break;
                case "hp":
                    pbHp.setProgress(estatistica.getValor());
                    break;
                default:
                    break;
            }
        }
        esconderSpinner();
        cvPokemon.setVisibility(View.VISIBLE);
    }

    private String concatenaTipos(List<String> tipos) {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < tipos.size(); index++) {
            if (tipos.size() - 1 == index) {
                builder.append(tipos.get(index));
            } else {
                builder.append(tipos.get(index)).append(" / ");
            }
        }
        return builder.toString();
    }

    private String getStringResources(int idString) {
        return getResources().getString(idString);
    }

    private String montaString(int idString, String campo) {
        return getStringResources(idString) + " " + campo;
    }
}
