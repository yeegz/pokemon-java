package com.pokemon.game.util;

import java.util.concurrent.ThreadLocalRandom;

public class RNG {
    public static int nextInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }
}