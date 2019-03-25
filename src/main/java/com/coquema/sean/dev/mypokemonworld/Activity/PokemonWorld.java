package com.coquema.sean.dev.mypokemonworld.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.coquema.sean.dev.mypokemonworld.Common.Common;
import com.coquema.sean.dev.mypokemonworld.Fragment.PokemonDetail;
import com.coquema.sean.dev.mypokemonworld.Fragment.PokemonList;
import com.coquema.sean.dev.mypokemonworld.Fragment.PokemonType;
import com.coquema.sean.dev.mypokemonworld.Model.Pokemon;

import com.coquema.sean.dev.mypokemonworld.R;

/**
 * Created by Sean Coquema on 12/03/2019.
 *
 */

public class PokemonWorld extends AppCompatActivity {

    Toolbar mToolbar;
    View myFragment;

    //BroadcastReceiver showPokemonType
    BroadcastReceiver showPokemonType = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().toString().equals(Common.KEY_POKEMON_TYPE)){

                //Remplace FRAGMENT
                Fragment pokemonType = PokemonType.getInstance();
                //int position = intent.getIntExtra("position", -1);
                String type = intent.getStringExtra("type");
                Bundle bundle = new Bundle();
                bundle.putString("type", type);
                pokemonType.setArguments(bundle);

                getSupportFragmentManager().popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.list_pokemon_fragment, pokemonType);
                fragmentTransaction.addToBackStack("type");
                fragmentTransaction.commit();

                mToolbar.setTitle("POKEMON TYPE " + type.toUpperCase());
            }
        }
    };

    //BroadcastReceiver showDetail
    BroadcastReceiver showDetail = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().toString().equals(Common.KEY_ENABLE_HOME)){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Enable back button on toolBar
                getSupportActionBar().setDisplayHomeAsUpEnabled(true); //too

                //Remplace FRAGMENT
                Fragment detailFragment = PokemonDetail.getInstance();
                //int position = intent.getIntExtra("position", -1);
                String num = intent.getStringExtra("num");
                Bundle bundle = new Bundle();
                bundle.putString("num", num);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.list_pokemon_fragment, detailFragment);
                fragmentTransaction.addToBackStack("detail");
                fragmentTransaction.commit();

                //Set Pokemon Name for Toolbar
                Pokemon pokemon = Common.findPokemonByNum(num);
                mToolbar.setTitle(pokemon.getName());
            }
        }
    };

    //BroadcastReceiver showEvolution
    BroadcastReceiver showEvolution = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().toString().equals(Common.KEY_NUM_EVOLUTION)){

                //Remplace FRAGMENT
                Fragment detailFragment = PokemonDetail.getInstance();

                Bundle bundle = new Bundle();
                String num = intent.getStringExtra("num");
                bundle.putString("num", num);
                detailFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.remove(detailFragment);   //Remove current fragment
                fragmentTransaction.replace(R.id.list_pokemon_fragment, detailFragment);
                fragmentTransaction.addToBackStack("detail");
                fragmentTransaction.commit();

                //Set Pokemon Name for Toolbar
                Pokemon pokemon = Common.findPokemonByNum(num);
                mToolbar.setTitle(pokemon.getName());
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_world);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("POKEMON LIST");
        setSupportActionBar(mToolbar);

        //Register Broadcast
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showDetail, new IntentFilter(Common.KEY_ENABLE_HOME));

        //Register Broadcast
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showEvolution, new IntentFilter(Common.KEY_NUM_EVOLUTION));

        //Register Broadcast
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(showPokemonType, new IntentFilter(Common.KEY_POKEMON_TYPE
                ));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PokemonWorld.this, InsertData.class));
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                mToolbar.setTitle("POKEMON LIST");

                //Clear all fragment detail and pop to list fragment
                getSupportFragmentManager().popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                getSupportFragmentManager().popBackStack("type", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                //Replace Fragment
                Fragment pokemonList = PokemonList.getInstance();

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.remove(pokemonList);
                fragmentTransaction.replace(R.id.list_pokemon_fragment, pokemonList);
                fragmentTransaction.commit();

                getSupportActionBar().setDisplayShowHomeEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);

                break;
            default:
                break;
        }
        return true;
    }
}

