package com.pokemon.game.controller;

import com.pokemon.game.database.PokemonRepository;
import com.pokemon.game.model.Pokemon;
import com.pokemon.game.service.*;
import com.pokemon.game.ui.GameUI;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final GameUI ui;
    private final PokemonRepository pokemonRepo;
    private final CombatService combatService;
    private final Scoreboard scoreboard;
    private final List<Pokemon> playerParty;

    public GameController() {
        this.ui = new GameUI();
        this.pokemonRepo = new PokemonRepository();
        this.scoreboard = new Scoreboard();
        
        // Services
        LevelingService levelingService = new LevelingService();
        CaptureService captureService = new CaptureService();
        this.combatService = new CombatService(ui, levelingService, captureService);
        
        // Initialize Player's Party
        this.playerParty = new ArrayList<>();
    }

    public void start() {
        ui.displayWelcome();
        
        // Give the player a starting Pokémon
        ui.displayMessage("Professor Oak gives you your first Pokémon!");
        Pokemon startingPokemon = pokemonRepo.createPokemon("Charmander"); // Or let the player choose
        playerParty.add(startingPokemon);
        scoreboard.displayPlayerParty(playerParty);

        gameLoop();
    }

    private void gameLoop() {
        while (true) {
            ui.displayMainMenu();
            int choice = ui.getIntInput(1, 3);
            switch (choice) {
                case 1:
                    findWildPokemon();
                    break;
                case 2:
                    scoreboard.displayPlayerParty(playerParty);
                    break;
                case 3:
                    ui.displayMessage("Thanks for playing!");
                    return;
            }
        }
    }

    private void findWildPokemon() {
        if (playerParty.get(0).isFainted()) {
            ui.displayMessage("Your lead Pokémon has fainted! You should heal it first.");
            // In a full game, you'd go to a Pokémon Center. Here, we just block.
            return;
        }

        Pokemon wildPokemon = pokemonRepo.getRandomWildPokemon();
        combatService.startBattle(playerParty.get(0), wildPokemon, playerParty);
    }
}