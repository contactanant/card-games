package com.alph.games.cards.domain;

public class Card {
    int value;
    SpecialCard specialCard;
    Suit suit;

    public Card(int value, SpecialCard specialCard, Suit suit) {
        this.value = value;
        this.specialCard = specialCard;
        this.suit = suit;
    }

    public Card(int value, Suit suit) {
        this(value, null, suit);
    }

    @Override
    public String toString() {
        return (specialCard == null ? value : specialCard + " with value " + value) + " of " + suit;
    }

    public int getValue() {
        return value;
    }
}
