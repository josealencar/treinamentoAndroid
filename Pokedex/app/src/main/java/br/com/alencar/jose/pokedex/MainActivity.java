package br.com.alencar.jose.pokedex;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.com.alencar.jose.pokedex.asynctasks.GetPokemonAsyncTask;
import br.com.alencar.jose.pokedex.models.Estatistica;
import br.com.alencar.jose.pokedex.models.Pokemon;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_MAIN = "TAG_MAIN";
    private static final String COLOR_WHITE = "#ffffff";
    private static final String COLOR_CYAN = "#01baef";
    private TextView tvNome, tvNomePokemon, tvAlturaPokemon, tvPesoPokemon, tvTypesPokemon, tvValueSpeed, tvValueSpecialDefense, tvValueSpecialAttack, tvValueDefense, tvValueAttack, tvValueHp;
    private EditText etNumber;
    private ProgressBar spinner;
    private RoundCornerProgressBar pbSpeed, pbSpecialDefense, pbSpecialAttack, pbDefense, pbAttack, pbHp;
    private Button btnSearch, btnLuck;
    private FloatingActionButton btnSync;

    private CardView cvPokemon;
    private ImageView ivPokemon;

    private Map<Integer, Pokemon> cachePokemons = new HashMap<>();
    private Integer idShowing;

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
        //tvIdPokemon = (TextView) findViewById(R.id.tv_id_pokemon);
        tvNomePokemon = (TextView) findViewById(R.id.tv_nome_pokemon);
        tvAlturaPokemon = (TextView) findViewById(R.id.tv_altura_pokemon);
        tvPesoPokemon = (TextView) findViewById(R.id.tv_peso_pokemon);
        tvTypesPokemon = (TextView) findViewById(R.id.tv_types_pokemon);

        tvValueSpeed = (TextView) findViewById(R.id.tv_value_speed);
        tvValueSpecialDefense = (TextView) findViewById(R.id.tv_value_special_defense);
        tvValueSpecialAttack = (TextView) findViewById(R.id.tv_value_special_attack);
        tvValueDefense = (TextView) findViewById(R.id.tv_value_defense);
        tvValueAttack = (TextView) findViewById(R.id.tv_value_attack);
        tvValueHp = (TextView) findViewById(R.id.tv_value_hp);

        cvPokemon = (CardView) findViewById(R.id.cv_pokemon);
        ivPokemon = (ImageView) findViewById(R.id.iv_pokemon);
        pbSpeed = (RoundCornerProgressBar) findViewById(R.id.pb_speed);
        pbSpecialDefense = (RoundCornerProgressBar) findViewById(R.id.pb_special_defense);
        pbSpecialAttack = (RoundCornerProgressBar) findViewById(R.id.pb_special_attack);
        pbDefense = (RoundCornerProgressBar) findViewById(R.id.pb_defense);
        pbAttack = (RoundCornerProgressBar) findViewById(R.id.pb_attack);
        pbHp = (RoundCornerProgressBar) findViewById(R.id.pb_hp);

        btnSearch = (Button) findViewById(R.id.bt_search);
        btnLuck = (Button) findViewById(R.id.bt_luck);
        btnSync = (FloatingActionButton) findViewById(R.id.fab_sync);
        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sincronizar(v);
            }
        });

        ajustaEstiloProgressBar(pbSpeed);
        ajustaEstiloProgressBar(pbSpecialDefense);
        ajustaEstiloProgressBar(pbSpecialAttack);
        ajustaEstiloProgressBar(pbDefense);
        ajustaEstiloProgressBar(pbAttack);
        ajustaEstiloProgressBar(pbHp);
    }

    public void desabilitaBotoes() {
        btnSearch.setEnabled(false);
        btnLuck.setEnabled(false);
    }

    public void habilitaBotoes() {
        btnSearch.setEnabled(true);
        btnLuck.setEnabled(true);
    }

    private void ajustaEstiloProgressBar(RoundCornerProgressBar roundCornerProgressBar) {
        roundCornerProgressBar.setProgressColor(Color.parseColor(COLOR_CYAN));
        roundCornerProgressBar.setProgressBackgroundColor(Color.parseColor(COLOR_WHITE));
        roundCornerProgressBar.setMax(200);
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
        try {
            Integer number = Integer.parseInt(etNumber.getText().toString());
            etNumber.setText(null);
            buscarPokemon(number);
        } catch (NumberFormatException nfe) {
            Log.e(TAG_MAIN, nfe.getMessage(), nfe);
            atualizarMensagem(getResources().getString(R.string.invalid_number));
        }
    }

    public void sortear(View view) {
        Random random = new Random();
        int number = random.nextInt(721);
        buscarPokemon(number+1);
    }

    private void sincronizar(View view) {
        realizarRequest(idShowing);
    }

    private void realizarRequest(Integer id) {
        cvPokemon.setVisibility(View.GONE);
        atualizarMensagem(null);
        if (isNetworkAvailable()) {
            new GetPokemonAsyncTask(this).execute(id);
        } else {
            atualizarMensagem(getResources().getString(R.string.network_unnavailable));
        }
    }

    private void buscarPokemon(Integer id) {
        if (cachePokemons.containsKey(id)) {
            popularPokemon(cachePokemons.get(id));
        } else {
            realizarRequest(id);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void popularPokemon(Pokemon pokemon) {
        cachePokemons.put(pokemon.getId(), pokemon);
        idShowing = pokemon.getId();
        Picasso.with(this).load(pokemon.getSprite()).into(ivPokemon);
        //tvIdPokemon.setText(montaString(R.string.base_id_text, String.valueOf(pokemon.getId())));
        tvNomePokemon.setText(String.format("%s #%d", montaString(R.string.base_name_pokemon, pokemon.getNome()), pokemon.getId()));
        tvPesoPokemon.setText(String.format("%s %.3f", getStringResources(R.string.base_weight_pokemon), pokemon.getPeso()).replace(".", ","));
        tvAlturaPokemon.setText(montaString(R.string.base_height_pokemon, String.valueOf(pokemon.getAltura())));
        tvTypesPokemon.setText(montaString(R.string.base_types_pokemon, concatenaTipos(pokemon.getTipos())));

        for (Estatistica estatistica : pokemon.getEstatisticas()) {
            switch (estatistica.getDescricao()) {
                case "speed":
                    pbSpeed.setProgress(estatistica.getValor());
                    tvValueSpeed.setText(String.valueOf(estatistica.getValor()));
                    break;
                case "special-defense":
                    pbSpecialDefense.setProgress(estatistica.getValor());
                    tvValueSpecialDefense.setText(String.valueOf(estatistica.getValor()));
                    break;
                case "special-attack":
                    pbSpecialAttack.setProgress(estatistica.getValor());
                    tvValueSpecialAttack.setText(String.valueOf(estatistica.getValor()));
                    break;
                case "defense":
                    pbDefense.setProgress(estatistica.getValor());
                    tvValueDefense.setText(String.valueOf(estatistica.getValor()));
                    break;
                case "attack":
                    pbAttack.setProgress(estatistica.getValor());
                    tvValueAttack.setText(String.valueOf(estatistica.getValor()));
                    break;
                case "hp":
                    pbHp.setProgress(estatistica.getValor());
                    tvValueHp.setText(String.valueOf(estatistica.getValor()));
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
