package br.com.alencar.jose.mygames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.alencar.jose.mygames.models.Game;
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

        popView();
    }

    private void popView() {
        tvName.setText(getResources().getString(R.string.pref_detalhe_nome) + game.getNome());
        tvDate.setText(getResources().getString(R.string.pref_detalhe_data) + game.getDataFormatada());
        tvDescription.setText(null);
        Picasso.with(this).load(game.getUrlImage()).into(ivImage);
    }

    private void initComponents() {
        ivImage = (ImageView) findViewById(R.id.iv_image);
        tvName = (TextView) findViewById(R.id.tv_name_detail);
        tvDate = (TextView) findViewById(R.id.tv_date_detail);
        tvDescription = (TextView) findViewById(R.id.tv_description);
    }
}
