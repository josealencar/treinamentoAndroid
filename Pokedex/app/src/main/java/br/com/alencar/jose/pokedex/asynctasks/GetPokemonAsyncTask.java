package br.com.alencar.jose.pokedex.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import br.com.alencar.jose.pokedex.MainActivity;
import br.com.alencar.jose.pokedex.R;
import br.com.alencar.jose.pokedex.models.Estatistica;
import br.com.alencar.jose.pokedex.models.Pokemon;

/**
 * Created by jose on 24/05/17.
 */

public class GetPokemonAsyncTask extends AsyncTask<Integer, Void, JSONObject> {

    private static final String TAG_GET_POKEMON = "GetPokemonAsyncTask";

    private MainActivity activity;

    public GetPokemonAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        activity.mostrarSpinner();
    }

    @Override
    protected JSONObject doInBackground(Integer... params) {
        Integer id = params[0];
        String urlString = "http://pokeapi.co/api/v2/pokemon/" + id;
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            Log.d(TAG_GET_POKEMON, builder.toString());

        } catch (MalformedURLException e) {
            Log.e(TAG_GET_POKEMON, e.getMessage(), e);
        } catch (IOException e) {
            Log.e(TAG_GET_POKEMON, e.getMessage(), e);
        }
        JSONObject response = null;
        try {
            response = new JSONObject(builder.toString());
        } catch (JSONException e) {
            Log.e(TAG_GET_POKEMON, e.getMessage(), e);
        }

        return response;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        String nome, sprite, descricao;
        Integer id, altura, valor;
        Double peso;
        List<String> tipos = null;
        List<Estatistica> estatisticas = null;

        try {
            nome = jsonObject.getString("name");
            nome = nome.substring(0,1).toUpperCase() + nome.substring(1);
            sprite = jsonObject.getJSONObject("sprites").getString("front_default");
            id = jsonObject.getInt("id");
            peso = jsonObject.getDouble("weight") / 10;
            altura = jsonObject.getInt("height") * 100;

            JSONArray stats = jsonObject.getJSONArray("stats");

            estatisticas = new ArrayList<>();
            for (int i=0; i < stats.length(); i++) {
                descricao = stats.getJSONObject(i).getJSONObject("stat").getString("name");
                valor = stats.getJSONObject(i).getInt("base_stat");

                Estatistica estatistica = new Estatistica(descricao, valor);
                estatisticas.add(estatistica);
            }

            JSONArray types = jsonObject.getJSONArray("types");

            tipos = new ArrayList<>();
            for (int j=0; j < types.length(); j++) {
                String tipo = types.getJSONObject(j).getJSONObject("type").getString("name");
                tipos.add(tipo.substring(0, 1).toUpperCase() + tipo.substring(1));
            }

            Pokemon pokemon = new Pokemon(id, nome, sprite, altura, peso, tipos, estatisticas);

            activity.popularPokemon(pokemon);
        } catch (JSONException | NullPointerException e) {
            activity.atualizarMensagem(activity.getResources().getString(R.string.error_search));
            Log.e(TAG_GET_POKEMON, e.getMessage(), e);
        }
    }
}
