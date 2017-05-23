package br.com.alencar.jose.mygames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import br.com.alencar.jose.mygames.adapters.GamesAdapter;
import br.com.alencar.jose.mygames.models.Game;
import br.com.alencar.jose.mygames.models.GameList;
import br.com.alencar.jose.mygames.utils.Contants;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Log";
    private ListView myGamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Passou no onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Passou no onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Passou no onStop");
        super.onStop();
    }

    private void initComponents() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(myToolbar);

        myGamesList = (ListView) findViewById(R.id.my_games_list);

        final GamesAdapter adapter = new GamesAdapter(GameList.getAll(), this);

        myGamesList.setAdapter(adapter);

        myGamesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, DetalheActivity.class);
                Game game = adapter.getItem(position);
                i.putExtra(Contants.CHAVE_GAME, game);
                startActivity(i);
            }
        });

        myGamesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                GameList.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
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
}
