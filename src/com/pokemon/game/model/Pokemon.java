package com.pokemon.game.model;

import java.util.List;

public class Pokemon {
    private final String name;
    private int level;
    private int hp;
    private int maxHp;
    private int attackStat;
    private int defenseStat;
    private int xp;
    private int xpToNextLevel;
    private final List<Attack> attacks;

    public Pokemon(String name, int level, int maxHp, int attackStat, int defenseStat, List<Attack> attacks) {
        this.name = name;
        this.level = level;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.attacks = attacks;
        this.xp = 0;
        this.xpToNextLevel = 100 * level;
    }

    // Getters
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttackStat() { return attackStat; }
    public int getDefenseStat() { return defenseStat; }
    public List<Attack> getAttacks() { return attacks; }
    public int getXp() { return xp; }
    public int getXpToNextLevel() { return xpToNextLevel; }

    public boolean isFainted() {
        return this.hp <= 0;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }
    
    public void addXp(int xpGained) {
        this.xp += xpGained;
    }

    public void levelUp() {
        this.level++;
        this.maxHp += 10;
        this.hp = this.maxHp; // Fully heal on level up
        this.attackStat += 5;
        this.defenseStat += 5;
        this.xp = 0; // Reset xp for the new level
        this.xpToNextLevel = 100 * this.level;
        System.out.println("\n" + this.name + " grew to Level " + this.level + "!");
    }
}