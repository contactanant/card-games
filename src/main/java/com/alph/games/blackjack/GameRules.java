package com.alph.games.blackjack;

import com.alph.games.cards.domain.Card;
import com.alph.games.cards.domain.Deck;
import com.alph.games.cards.domain.Hand;
import com.alph.games.cards.domain.Player;
import sun.plugin.dom.exception.InvalidStateException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.alph.games.blackjack.BlackJackStatus.BLACKJACK;
import static com.alph.games.blackjack.BlackJackStatus.BUST;
import static com.alph.games.blackjack.BlackJackStatus.STICK;

public class GameRules {

    private int sumForStick;
    private int sumForBlackJack;

    public GameRules(int sumForStick, int sumForBlackJack) {
        this.sumForStick = sumForStick;
        this.sumForBlackJack = sumForBlackJack;
    }

    public void decideWinner(Map<Player, BlackJackStatus> gameStat) {
        if (gameStat == null) {
            System.out.println("Players have not taken turns yet, can not decide winner");
        }
        List<Player> playersSticking = getPlayersForStatus(gameStat, STICK);  // sum greater than equal to 17 but less than 21
        List<Player> playersBlackjack = getPlayersForStatus(gameStat, BLACKJACK); //sum equals to blackjack


        if ((playersSticking != null && playersSticking.size() > 0) || (playersBlackjack != null && playersBlackjack.size() > 0)) {
            // Rule 4 : find the player with max card value
            getWinner(playersSticking, playersBlackjack);
        }

        System.out.println("Game Finished");
    }

    public BlackJackStatus turn(Player p, Deck deck) {
        if (p.getHand() == null || (p.getHand().getNumberOfCards()) == 0) {
            throw new InvalidStateException("Deal cards to player before they can take turns");
        }

        int sumOfCards;
        while ((sumOfCards=p.getHand().getSumOfCards()) < sumForStick) {
            Card card = deck.drawTopCard();
            System.out.println(p + " hit new card " + card);
            p.getHand().addCard(card);
        }

        if (sumOfCards > sumForBlackJack) {
            System.out.println(p + " has gone bust");
            return BUST;
        }

        if (sumOfCards == sumForBlackJack) {
            System.out.println(p + " has got sum = " + sumOfCards);
            return BLACKJACK;
        }

        System.out.println(p + " sticks");
        return STICK;
    }

    public void dealHand(List<Player> players, Deck deck, int cardsPerPlayer) {
        players.stream().forEach(p -> p.setHand(new Hand(deck.dealHand(cardsPerPlayer))));
    }

    private void getWinner(List<Player> playersSticking, List<Player> playersBlackjack) {
        if (playersBlackjack != null && playersBlackjack.size() > 0) {
            if (playersSticking != null && playersSticking.size() > 0) {
                Optional<Player> playerWithMaxSum = playersSticking.stream().sorted((p1, p2) -> Integer.valueOf(p2.getHand().getSumOfCards()).compareTo(p1.getHand().getSumOfCards())).findFirst();
                if (playerWithMaxSum.isPresent()) {
                    System.out.println(playerWithMaxSum.get() + " wins his sum is " + playerWithMaxSum.get().getHand().getSumOfCards());
                }
            }
        }
    }

    private List<Player> getPlayersForStatus(Map<Player, BlackJackStatus> gameStat, BlackJackStatus status) {
        return gameStat.entrySet().stream().filter(e -> (e.getValue() == status)).map(Map.Entry::getKey).collect(Collectors.toList());
    }
}
