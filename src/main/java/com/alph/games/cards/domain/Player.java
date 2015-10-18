package com.alph.games.cards.domain;

import java.util.List;

public class Player {
    private int id;
    private String name;
    private Hand hand;

    public Player(int id) {
        this.id = id;
        this.name = "Player " + id;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public String getHandView() {
        return name + " hand view is " + getHand();
    }
}
