package br.com.alencar.jose.mygames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.alencar.jose.mygames.models.Game;
import br.com.alencar.jose.mygames.models.GameList;
import br.com.alencar.jose.mygames.utils.Contants;

public class DetalheActivity extends AppCompatActivity {

    private Game game;
    private ImageView ivImage;
    private TextView tvName;
    private TextView tvDate;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        initComponents();

        Intent i = getIntent();
        game = (Game) i.getSerializableExtra(Contants.CHAVE_GAME);
        setTitle(game.getNome());

        popView();
    }

    private void popView() {
        tvName.setText(getResources().getString(R.string.pref_detalhe_nome) + game.getNome());
        tvDate.setText(getResources().getString(R.string.pref_detalhe_data) + game.getDataFormatada());
        tvDescription.setText(game.getDescricao());
        Picasso.with(this).load(game.getUrlImage()).into(ivImage);
    }

    private void initComponents() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.tb_detail);
        setSupportActionBar(myToolbar);

        ivImage = (ImageView) findViewById(R.id.iv_image);
        tvName = (TextView) findViewById(R.id.tv_name_detail);
        tvDate = (TextView) findViewById(R.id.tv_date_detail);
        tvDescription = (TextView) findViewById(R.id.tv_description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.detail_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem favorite = menu.findItem(R.id.item_favorite);
        MenuItem toFavorite = menu.findItem(R.id.item_to_favorite);
        favorite.setVisible(false);
        toFavorite.setVisible(false);

        if (game.isFavorito()) {
            favorite.setVisible(true);
        } else {
            toFavorite.setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onStop() {
        GameList.update(game);
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_to_favorite:
                game.setFavorito(true);
                invalidateOptionsMenu();
                return true;
            case R.id.item_favorite:
                game.setFavorito(false);
                invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
