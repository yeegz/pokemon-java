package com.pokemon.game.service;

import com.pokemon.game.model.Attack;
import com.pokemon.game.model.Pokemon;
import com.pokemon.game.ui.GameUI;
import com.pokemon.game.util.RNG;
import java.util.List;

public class CombatService {
    private final GameUI ui;
    private final LevelingService levelingService;
    private final CaptureService captureService;

    public CombatService(GameUI ui, LevelingService levelingService, CaptureService captureService) {
        this.ui = ui;
        this.levelingService = levelingService;
        this.captureService = captureService;
    }

    public boolean startBattle(Pokemon playerPokemon, Pokemon wildPokemon, List<Pokemon> playerParty) {
        ui.displayMessage("\nA wild " + wildPokemon.getName() + " appeared!");

        while (!playerPokemon.isFainted() && !wildPokemon.isFainted()) {
            ui.displayBattleStatus(playerPokemon, wildPokemon);
            ui.displayBattleMenu(playerPokemon);
            int action = ui.getIntInput(1, 3);

            switch (action) {
                case 1: // Fight
                    Attack playerAttack = ui.chooseAttack(playerPokemon);
                    executeTurn(playerPokemon, wildPokemon, playerAttack);
                    if (wildPokemon.isFainted()) break;
                    
                    // Wild Pokemon attacks back
                    Attack wildAttack = wildPokemon.getAttacks().get(RNG.nextInt(wildPokemon.getAttacks().size()));
                    executeTurn(wildPokemon, playerPokemon, wildAttack);
                    break;
                case 2: // Catch
                    if (captureService.attemptCapture(wildPokemon)) {
                        ui.displayMessage("Gotcha! " + wildPokemon.getName() + " was caught!");
                        playerParty.add(wildPokemon);
                        return true; // Battle won by capture
                    } else {
                        ui.displayMessage("Oh no! The Pokémon broke free!");
                    }
                    // The wild Pokémon still gets to attack after a failed capture attempt
                    Attack angryAttack = wildPokemon.getAttacks().get(RNG.nextInt(wildPokemon.getAttacks().size()));
                    executeTurn(wildPokemon, playerPokemon, angryAttack);
                    break;
                case 3: // Run
                    ui.displayMessage("You got away safely!");
                    return false; // Escaped
            }
        }

        if (wildPokemon.isFainted()) {
            ui.displayMessage(wildPokemon.getName() + " fainted!");
            levelingService.grantXp(playerPokemon, 50 * wildPokemon.getLevel()); // Grant XP
            return true; // Battle won
        } else if (playerPokemon.isFainted()) {
            ui.displayMessage(playerPokemon.getName() + " fainted! You scurried away...");
            return false; // Battle lost
        }
        return false;
    }

    private void executeTurn(Pokemon attacker, Pokemon defender, Attack attack) {
        ui.displayMessage(attacker.getName() + " used " + attack.getName() + "!");
        
        // Timing Attack addon would go here. You could add a small delay and check for user input.
        // For now, we just use accuracy.

        if (RNG.nextInt(100) < attack.getAccuracy()) {
            // Simplified damage formula
            int damage = ( ( (2 * attacker.getLevel() / 5 + 2) * attack.getPower() * attacker.getAttackStat() / defender.getDefenseStat() ) / 50 ) + 2;
            damage = Math.max(1, damage); // Ensure at least 1 damage
            
            defender.takeDamage(damage);
            ui.displayMessage("It's a hit! " + defender.getName() + " took " + damage + " damage.");
        } else {
            ui.displayMessage("But it missed!");
        }
    }
}