package com.coquema.sean.dev.mypokemonworld.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coquema.sean.dev.mypokemonworld.Common.Common;
import com.coquema.sean.dev.mypokemonworld.Model.Evolution;
import com.coquema.sean.dev.mypokemonworld.R;
import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean Coquema on 05/03/2019.
 *
 */

public class PokemonEvolutionAdapter extends RecyclerView.Adapter<PokemonEvolutionAdapter.MyViewHolder> {

    Context mContext;
    List<Evolution> evolution;

    public PokemonEvolutionAdapter(Context context, List<Evolution> evolution) {
        mContext = context;
        if(evolution != null)
            this.evolution = evolution;
        else
            this.evolution = new ArrayList<>(); //Fix crash if Pokemon doesm't have Prev or next Evolution.
    }

    @NonNull
    @Override
    public PokemonEvolutionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.chip_item, parent, false);
        return new PokemonEvolutionAdapter.MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull PokemonEvolutionAdapter.MyViewHolder holder, int position) {

        holder.chip.setChipText(evolution.get(position).getName());
        holder.chip.changeBackgroundColor(
                Common.getColorByType(

                    Common.findPokemonByNum(
                            evolution.get(position).getNum()
                    ).getType().get(0)
                )
        );
    }

    @Override
    public int getItemCount() {
        return evolution.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        Chip chip;

        public MyViewHolder(View itemView){
            super(itemView);
            chip = (Chip)itemView.findViewById(R.id.chip);
            chip.setOnChipClickListener(new OnChipClickListener() {
                @Override
                public void onChipClick(View v) {
                    LocalBroadcastManager.getInstance(mContext)
                            .sendBroadcast(new Intent(Common.KEY_NUM_EVOLUTION)
                            .putExtra("num", evolution.get(getAdapterPosition())
                            .getNum()));
                }
            });
        }

    }
}

