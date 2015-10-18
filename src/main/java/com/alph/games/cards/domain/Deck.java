package com.alph.games.cards.domain;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {

    List<Card>  deck;

    /**
     * Parameter cardValues: A mapping of Special cards and values.  e.g. blackjack maps JACK, QUEEN and KING to 10 and ACE to 11
     */
    public Deck(Map<SpecialCard, Integer> cardValues) {
        this.deck = new ArrayList<>();

        for (Suit suit : Suit.values()) {

            // add cards 2 to 10 to deck
            IntStream.range(2, 10).forEach(n-> deck.add(new Card(n, suit)));

            // add special cards to deck
            for (SpecialCard specialCard : SpecialCard.values()) {

                Integer cardValue = cardValues == null ? specialCard.getDefaultValue() : cardValues.getOrDefault(specialCard, specialCard.getDefaultValue());
                deck.add(new Card(cardValue, specialCard, suit));
            }
        }
        Collections.shuffle(this.deck);
    }

    @Override
    public String toString() {
        return deck.stream().map(card-> card.toString()).collect(Collectors.joining(", "));
    }

//    public void shuffle(Consumer<T> consumer) {
//        consumer.accept(T);
//    }

    public int size() {
        return deck.size();
    }

    public List<Card> dealHand(int n) {
        int deckSize = deck.size();
        List<Card> handView = deck.subList(deckSize - n, deckSize);
        List<Card> hand = new ArrayList<>(handView);
        handView.clear();
        return hand;
    }

    public Card drawTopCard() {
        int deckSize = deck.size();
        return deck.remove(deckSize - 1);
    }
}
