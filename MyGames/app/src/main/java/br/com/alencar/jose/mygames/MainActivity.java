package br.com.alencar.jose.mygames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import br.com.alencar.jose.mygames.adapters.GamesAdapter;
import br.com.alencar.jose.mygames.models.Game;
import br.com.alencar.jose.mygames.models.GameList;
import br.com.alencar.jose.mygames.utils.Contants;

public class MainActivity extends AppCompatActivity {

    private ListView myGamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents() {
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
    }
}
