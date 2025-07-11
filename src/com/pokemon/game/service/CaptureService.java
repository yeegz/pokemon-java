package com.pokemon.game.service;

import com.pokemon.game.model.Pokemon;
import com.pokemon.game.util.RNG;

public class CaptureService {
    public boolean attemptCapture(Pokemon wildPokemon) {
        // Simplified capture formula
        int chance = ( (wildPokemon.getMaxHp() * 3 - wildPokemon.getHp() * 2) * 45 ) / (wildPokemon.getMaxHp() * 3);
        System.out.println("...Shake...Shake...");
        return RNG.nextInt(150) < chance;
    }
}