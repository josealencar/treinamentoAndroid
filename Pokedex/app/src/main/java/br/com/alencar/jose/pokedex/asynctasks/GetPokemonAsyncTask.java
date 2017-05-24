package br.com.alencar.jose.pokedex.asynctasks;

import android.os.AsyncTask;
import android.util.Log;

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

import br.com.alencar.jose.pokedex.MainActivity;

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
        String nome = null;
        try {
            nome = jsonObject.getString("name");
        } catch (JSONException | NullPointerException e) {
            Log.e(TAG_GET_POKEMON, e.getMessage(), e);
        }
        activity.renderizarNome(nome);
    }
}
