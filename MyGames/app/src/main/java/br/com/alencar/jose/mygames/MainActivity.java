package br.com.alencar.jose.mygames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import br.com.alencar.jose.mygames.models.Game;
import br.com.alencar.jose.mygames.models.GameList;

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

        ArrayAdapter<Game> adapter = new ArrayAdapter<Game>(this, android.R.layout.simple_list_item_1, GameList.getAll());

        myGamesList.setAdapter(adapter);
    }
}
