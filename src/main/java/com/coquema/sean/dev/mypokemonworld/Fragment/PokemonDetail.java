package com.coquema.sean.dev.mypokemonworld.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coquema.sean.dev.mypokemonworld.Adapter.PokemonEvolutionAdapter;
import com.coquema.sean.dev.mypokemonworld.Adapter.PokemonTypeAdapter;
import com.coquema.sean.dev.mypokemonworld.Common.Common;
import com.coquema.sean.dev.mypokemonworld.Model.Pokemon;
import com.coquema.sean.dev.mypokemonworld.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonDetail extends Fragment {

    ImageView pokemon_img;
    TextView pokemon_name, pokemon_height, pokemon_weight;
    RecyclerView recycler_type, recycler_weakness, recycler_next_evolution, recycler_prev_evolution;

    static PokemonDetail instance;

    public static PokemonDetail getInstance() {
        if(instance == null)
            instance = new PokemonDetail();
        return instance;
    }

    public PokemonDetail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);

        Pokemon pokemon = Common.findPokemonByNum(getArguments().getString("num"));

        pokemon_img = (ImageView)itemView.findViewById(R.id.pokemon_image);
        pokemon_name = (TextView) itemView.findViewById(R.id.name);
        pokemon_height= (TextView) itemView.findViewById(R.id.height);
        pokemon_weight = (TextView) itemView.findViewById(R.id.weight);

        recycler_type = (RecyclerView)itemView.findViewById(R.id.recyclerview_type);
        recycler_type.setHasFixedSize(true);
        recycler_type.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recycler_weakness = (RecyclerView)itemView.findViewById(R.id.recyclerview_weakness);
        recycler_weakness.setHasFixedSize(true);
        recycler_weakness.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recycler_next_evolution = (RecyclerView)itemView.findViewById(R.id.recyclerview_next_evolution);
        recycler_next_evolution.setHasFixedSize(true);
        recycler_next_evolution.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        recycler_prev_evolution = (RecyclerView)itemView.findViewById(R.id.recyclerview_prev_evolution);
        recycler_prev_evolution.setHasFixedSize(true);
        recycler_prev_evolution.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        setDetailPokemon(pokemon);

        return itemView;
    }

    private void setDetailPokemon(Pokemon pokemon) {

        //Load Image
        Glide.with(getActivity()).load(pokemon.getImg()).into(pokemon_img);

        pokemon_name.setText(pokemon.getName());

        pokemon_weight.setText("Weight" + pokemon.getWeight());

        pokemon_height.setText("Height" + pokemon.getHeight());

        //Set Type
        PokemonTypeAdapter typeAdapter = new PokemonTypeAdapter(getActivity(), pokemon.getType());
        recycler_type.setAdapter(typeAdapter);

        //Set Weakness
        PokemonTypeAdapter weaknessAdapter = new PokemonTypeAdapter(getActivity(), pokemon.getWeaknesses());
        recycler_weakness.setAdapter(weaknessAdapter);

        //Set Evolution
        PokemonEvolutionAdapter preEvolutionAdapter = new PokemonEvolutionAdapter(getActivity(), pokemon.getPrev_evolution());
        recycler_prev_evolution.setAdapter(preEvolutionAdapter);

        //Set Evolution
        PokemonEvolutionAdapter nextEvolutionAdapter = new PokemonEvolutionAdapter(getActivity(), pokemon.getNext_evolution());
        recycler_next_evolution.setAdapter(nextEvolutionAdapter);
    }
}
