package br.com.alencar.jose.pokedex.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.alencar.jose.pokedex.models.Estatistica;

/**
 * Created by jose on 25/05/17.
 */

public class EstatisticasAdapter extends RecyclerView.Adapter<EstatisticasAdapter.ViewHolder> {

    //TODO: transformar lista de estatísticas para list view dinâmica
    private List<Estatistica> estatisticas;

    public EstatisticasAdapter(List<Estatistica> estatisticas) {
        this.estatisticas = estatisticas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return estatisticas.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
