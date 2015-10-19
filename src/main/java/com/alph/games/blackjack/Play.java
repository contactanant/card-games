package com.alph.games.blackjack;

import com.alph.games.cards.domain.Deck;
import com.alph.games.cards.domain.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Play {

    public void playGame(GameEngine gameEngine, int numberOfPlayers, int cardsPerPlayer ) {
        Deck deck = createDeck(numberOfPlayers, cardsPerPlayer);
        List<Player> players = createPlayers(numberOfPlayers);

        gameEngine.dealHand(players, deck, cardsPerPlayer);
        players.stream().forEach(p->System.out.println(p.getHandView()));

        Map<Player, BlackJackStatus> gameStat = new HashMap<>();
        players.stream().forEach(p -> gameStat.put(p, gameEngine.turn(p, deck)));
        gameEngine.decideWinner(gameStat);
    }

    private List<Player> createPlayers(int numberOfPlayers) {
        List<Player> players = new ArrayList<>();
        IntStream.rangeClosed(1, numberOfPlayers).forEach(n -> players.add(new Player(n)));
        return players;
    }

    private Deck createDeck(int numberOfPlayers, int cardsPerPlayer) {
        Deck deck = DeckFactory.createDeck();
        if (numberOfPlayers * cardsPerPlayer > deck.size()) {
            System.out.println("Not enough cards.");
            System.exit(0);
        }
        return deck;
    }
}
