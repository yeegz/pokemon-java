package com.pokemon.game;

import com.pokemon.game.controller.GameController;

public class Main {
    public static void main(String[] args) {
        GameController game = new GameController();
        game.start();
    }
}