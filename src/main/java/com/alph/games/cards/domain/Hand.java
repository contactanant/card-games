package com.alph.games.cards.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cards;
    private int sumOfCards;

    public Hand(List<Card> cards) {
        this.cards = cards;
        if (cards != null) {
            sumOfCards = cards.stream().mapToInt(Card::getValue).sum();
        }
    }

    public void addCard(Card card) {
        if (this.cards == null) {
            this.cards = new ArrayList<>();
        }
        this.cards.add(card);
        sumOfCards += card.getValue();
    }

    public int getSumOfCards() {
        return sumOfCards;
    }

    public int getNumberOfCards() {
        return cards == null ? 0 : cards.size();
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
