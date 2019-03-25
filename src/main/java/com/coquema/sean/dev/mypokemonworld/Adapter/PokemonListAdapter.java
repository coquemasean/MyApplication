package com.coquema.sean.dev.mypokemonworld.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coquema.sean.dev.mypokemonworld.Common.Common;
import com.coquema.sean.dev.mypokemonworld.Interface.IItemClickListener;
import com.coquema.sean.dev.mypokemonworld.Model.Pokemon;
import com.coquema.sean.dev.mypokemonworld.R;


import java.util.List;

/**
 * Created by Sean Coquema on 08/03/2019.
 *
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    Context mContext;
    List<Pokemon> mPokemonList;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList) {
        mContext = context;
        mPokemonList = pokemonList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pokemon_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //loadImage
        Glide.with(mContext).load(mPokemonList.get(position).getImg()).into(holder.pokemon_image);
        //Set Name
        holder.pokemon_name.setText(mPokemonList.get(position).getName());
        //Event
        holder.setIItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(mContext, "Click at Pokemon" + mPokemonList.get(position).getName(), Toast.LENGTH_LONG).show();

                LocalBroadcastManager.getInstance(mContext)
                        .sendBroadcast(new Intent(Common.KEY_ENABLE_HOME).putExtra("num", mPokemonList.get(position).getNum()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPokemonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView pokemon_image;
        TextView pokemon_name;

        //añado el evento onClick que cree en la interface
        IItemClickListener mIItemClickListener;

        public void setIItemClickListener(IItemClickListener IItemClickListener) {
            mIItemClickListener = IItemClickListener;
        }

        public MyViewHolder(View itemView) {
            super(itemView);

            pokemon_image = (ImageView)itemView.findViewById(R.id.pokemon_image);
            pokemon_name = (TextView) itemView.findViewById(R.id.txt_pokemon_name);

            //I add the onClick event that I created in the interface after doing the set up!
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mIItemClickListener.onClick(view, getAdapterPosition());

        }
    }
}
