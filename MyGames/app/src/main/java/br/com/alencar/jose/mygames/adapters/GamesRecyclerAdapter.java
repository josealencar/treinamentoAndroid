package br.com.alencar.jose.mygames.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.alencar.jose.mygames.MainActivity;
import br.com.alencar.jose.mygames.R;
import br.com.alencar.jose.mygames.models.Game;

/**
 * Created by jose on 22/05/17.
 */

public class GamesRecyclerAdapter extends RecyclerView.Adapter<GamesRecyclerAdapter.ViewHolder> {

    private List<Game> games;

    public GamesRecyclerAdapter(List<Game> games) {
        this.games = games;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Game game = games.get(position);

        holder.tvNome.setText(game.getNome());
        holder.tvData.setText(game.getDataFormatada());
        Picasso.with(holder.itemView.getContext()).load(game.getUrlImage()).into(holder.ivCard);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvNome, tvData;
        protected ImageView ivCard;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNome = (TextView) itemView.findViewById(R.id.tv_name);
            tvData = (TextView) itemView.findViewById(R.id.tv_date);
            ivCard = (ImageView) itemView.findViewById(R.id.iv_card);

            itemView.setOnClickListener((MainActivity)itemView.getContext());
            itemView.setOnLongClickListener((MainActivity)itemView.getContext());
        }
    }
}
