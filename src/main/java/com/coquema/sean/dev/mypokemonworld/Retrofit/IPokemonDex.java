package com.coquema.sean.dev.mypokemonworld.Retrofit;

import com.coquema.sean.dev.mypokemonworld.Model.Pokedex;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Sean Coquema on 05/03/2019.
 *
 */

public interface IPokemonDex {

    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();

}
