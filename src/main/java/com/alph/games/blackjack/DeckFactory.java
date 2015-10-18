package com.alph.games.blackjack;

import com.alph.games.cards.domain.Deck;
import com.alph.games.cards.domain.SpecialCard;

import java.util.HashMap;
import java.util.Map;

import static com.alph.games.cards.domain.SpecialCard.*;

public class DeckFactory {

    public static Deck createDeck() {
        Map<SpecialCard, Integer> cardValues = new HashMap<SpecialCard, Integer>() {{ put(ACE, 11); put(JACK, 10); put(QUEEN, 10); put(KING, 10);}};
        return new Deck(cardValues);
    }
}
