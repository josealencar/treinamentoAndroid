package br.com.alencar.jose.mygames.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.alencar.jose.mygames.R;
import br.com.alencar.jose.mygames.models.Game;

/**
 * Created by jose on 22/05/17.
 */

public class GamesAdapter extends BaseAdapter {

    private List<Game> games;
    private Context context;
    private LayoutInflater inflater;
    private View view;
    private TextView tvName;
    private TextView tvDate;

    public GamesAdapter(List<Game> games, Context context) {
        this.games = games;
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return games.size();
    }

    @Override
    public Game getItem(int position) {
        return games.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Game game = getItem(position);

        view = inflater.inflate(R.layout.game_list_item, parent, false);

        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvDate = (TextView) view.findViewById(R.id.tv_date);

        tvName.setText(game.getNome());
        tvDate.setText(game.getDataFormatada());

        return view;
    }
}
