package com.alph.games.blackjack;

import com.alph.games.cards.domain.Deck;
import com.alph.games.cards.domain.Player;

import java.util.List;
import java.util.Map;

/**
 * Created by aag03 on 19/10/2015.
 */
public interface GameEngine {
    void decideWinner(Map<Player, BlackJackStatus> gameStat);

    BlackJackStatus turn(Player p, Deck deck);

    void dealHand(List<Player> players, Deck deck, int cardsPerPlayer);
}
