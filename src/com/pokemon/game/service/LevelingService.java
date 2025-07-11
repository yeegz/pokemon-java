package com.pokemon.game.service;

import com.pokemon.game.model.Pokemon;

public class LevelingService {
    public void grantXp(Pokemon pokemon, int xpGained) {
        System.out.println(pokemon.getName() + " gained " + xpGained + " XP.");
        pokemon.addXp(xpGained);

        if (pokemon.getXp() >= pokemon.getXpToNextLevel()) {
            pokemon.levelUp();
        }
    }
}