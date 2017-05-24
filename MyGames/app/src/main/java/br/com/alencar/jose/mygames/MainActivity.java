package br.com.alencar.jose.mygames;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.com.alencar.jose.mygames.adapters.GamesAdapter;
import br.com.alencar.jose.mygames.adapters.GamesRecyclerAdapter;
import br.com.alencar.jose.mygames.models.Game;
import br.com.alencar.jose.mygames.models.GameList;
import br.com.alencar.jose.mygames.utils.Contants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener{

    private static final String TAG = "Log";
    private RecyclerView myGamesList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private GameList gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initComponents();
    }

    private void initData() {
        SharedPreferences preferences = getSharedPreferences(Contants.ARQUIVO_PREFERENCIAS, MODE_PRIVATE);
        gameList = new GameList(preferences);
    }

    @Override
    protected void onRestart() {
        initData();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        adapter = new GamesRecyclerAdapter(gameList.getAll());
        myGamesList.setAdapter(adapter);
        
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Passou no onPause");
        SharedPreferences preferences = getSharedPreferences(Contants.ARQUIVO_PREFERENCIAS, MODE_PRIVATE);
        Log.d(TAG, preferences.getString(Contants.LISTA_CSV, "Está vazia a lista"));
        super.onPause();
    }

    private void initComponents() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(myToolbar);
        myGamesList = (RecyclerView) findViewById(R.id.my_games_list);
        myGamesList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        myGamesList.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_find:
                Toast.makeText(getBaseContext(), "TODO: pesquisar", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        int position = myGamesList.getChildLayoutPosition(v);
        Intent i = new Intent(MainActivity.this, DetalheActivity.class);
        Game game = gameList.getItem(position);
        i.putExtra(Contants.CHAVE_GAME, game);
        startActivity(i);
    }

    @Override
    public boolean onLongClick(View v) {
        int position = myGamesList.getChildLayoutPosition(v);
        gameList.remove(position);
        adapter.notifyDataSetChanged();
        return true;
    }
}
