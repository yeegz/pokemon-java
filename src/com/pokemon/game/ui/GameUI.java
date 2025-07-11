package com.pokemon.game.ui;

import com.pokemon.game.model.Attack;
import com.pokemon.game.model.Pokemon;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameUI {
    private final Scanner scanner;

    public GameUI() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayWelcome() {
        System.out.println("===================================");
        System.out.println("  Welcome to the Pokémon Terminal! ");
        System.out.println("===================================");
    }
    
    public void displayMainMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Find a wild Pokémon");
        System.out.println("2. View my Pokémon");
        System.out.println("3. Exit Game");
        System.out.print("> ");
    }
    
    public void displayBattleMenu(Pokemon playerPokemon) {
        System.out.println("\nWhat will " + playerPokemon.getName() + " do?");
        System.out.println("1. Fight");
        System.out.println("2. Catch");
        System.out.println("3. Run");
        System.out.print("> ");
    }
    
    public Attack chooseAttack(Pokemon pokemon) {
        System.out.println("Choose an attack:");
        List<Attack> attacks = pokemon.getAttacks();
        for (int i = 0; i < attacks.size(); i++) {
            System.out.printf("%d. %s (Power: %d)\n", i + 1, attacks.get(i).getName(), attacks.get(i).getPower());
        }
        System.out.print("> ");
        int choice = getIntInput(1, attacks.size());
        return attacks.get(choice - 1);
    }
    
    public void displayBattleStatus(Pokemon player, Pokemon opponent) {
        System.out.println("------------------------------------");
        System.out.printf("You: %-12s | LVL: %-3d | HP: %d/%d\n", player.getName(), player.getLevel(), player.getHp(), player.getMaxHp());
        System.out.printf("Wild: %-11s | LVL: %-3d | HP: %d/%d\n", opponent.getName(), opponent.getLevel(), opponent.getHp(), opponent.getMaxHp());
        System.out.println("------------------------------------");
    }

    public int getIntInput(int min, int max) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("Invalid choice. Please enter a number between " + min + " and " + max + ".\n> ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number.\n> ");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}