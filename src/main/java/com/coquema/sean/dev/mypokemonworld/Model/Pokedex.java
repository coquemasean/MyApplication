package com.coquema.sean.dev.mypokemonworld.Model;

import java.util.List;

/**
 * Created by Sean Coquema on 05/03/2019.
 *
 */

public class Pokedex
{
        private List<Pokemon> pokemon;

    public Pokedex() {
    }

    public Pokedex(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }
}

