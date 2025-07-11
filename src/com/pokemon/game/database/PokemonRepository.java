package com.pokemon.game.database;

import com.pokemon.game.model.Attack;
import com.pokemon.game.model.Pokemon;
import com.pokemon.game.util.RNG;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PokemonRepository {
    private final Map<String, Pokemon> pokemonTemplates = new HashMap<>();

    public PokemonRepository() {
        // Define Attacks
        Attack tackle = new Attack("Tackle", 40, 100);
        Attack vineWhip = new Attack("Vine Whip", 45, 100);
        Attack scratch = new Attack("Scratch", 40, 100);
        Attack ember = new Attack("Ember", 45, 100);
        Attack bubble = new Attack("Bubble", 45, 100);
        Attack quickAttack = new Attack("Quick Attack", 40, 100);

        // Define Pokemon Templates
        pokemonTemplates.put("Bulbasaur", new Pokemon("Bulbasaur", 5, 45, 49, 49, List.of(tackle, vineWhip)));
        pokemonTemplates.put("Charmander", new Pokemon("Charmander", 5, 39, 52, 43, List.of(scratch, ember)));
        pokemonTemplates.put("Squirtle", new Pokemon("Squirtle", 5, 44, 48, 65, List.of(tackle, bubble)));
        pokemonTemplates.put("Pidgey", new Pokemon("Pidgey", 3, 40, 45, 40, List.of(tackle, quickAttack)));
        pokemonTemplates.put("Rattata", new Pokemon("Rattata", 3, 30, 56, 35, List.of(tackle, quickAttack)));
    }

    public Pokemon createPokemon(String name) {
        Pokemon template = pokemonTemplates.get(name);
        if (template == null) return null;
        // Create a new instance from the template
        return new Pokemon(template.getName(), template.getLevel(), template.getMaxHp(), template.getAttackStat(), template.getDefenseStat(), template.getAttacks());
    }

    public Pokemon getRandomWildPokemon() {
        List<String> keys = new ArrayList<>(pokemonTemplates.keySet());
        String randomKey = keys.get(RNG.nextInt(keys.size()));
        return createPokemon(randomKey);
    }
}