package com.pokemon.game.service;

import com.pokemon.game.model.Pokemon;
import java.util.List;

public class Scoreboard {
    public void displayPlayerParty(List<Pokemon> party) {
        System.out.println("\n--- YOUR POKEMON PARTY ---");
        if (party.isEmpty()) {
            System.out.println("Your party is empty.");
        } else {
            for (Pokemon p : party) {
                System.out.printf("  - %-12s | LVL: %-3d | HP: %-3d/%-3d | XP: %d/%d\n",
                    p.getName(), p.getLevel(), p.getHp(), p.getMaxHp(), p.getXp(), p.getXpToNextLevel());
            }
        }
        System.out.println("--------------------------\n");
    }
}