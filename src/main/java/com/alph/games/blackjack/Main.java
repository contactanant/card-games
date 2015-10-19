package com.alph.games.blackjack;

public class Main {
    //move this to property file
    public static final String INVALID_NUMBER_OF_PLAYERS = "Number of players can not be less than 2 or greater than 6";
    public static final String INVALID_INPUT = "Invalid number of players";
    public static final String PROGRAM_USAGE_TEXT = "Usage: ProgrameName or ProgramName <numberOfPlayers> or ProgramName <numberOfPlayers> <numberOfCards>";
    public static final int DEFAULT_PLAYER_SIZE = 3;
    public static final int DEAULT_NUMBER_OF_CARDS = 2;
    public static final int BLACKJACK_TOTAL = 21;
    public static final int STICK_TOTAL = 17;

    public static void main(String[] args) {
        int numberOfPlayers = getNumberOfPlayers(args);
        int cardsPerPlayer = getNumberOfCardsPerPlayer(args);

        new Play().playGame(new BlackjackGameEngine(STICK_TOTAL, BLACKJACK_TOTAL), numberOfPlayers, cardsPerPlayer);
    }

    private static int getNumberOfCardsPerPlayer(String[] args) {
        int numberOfCards = DEAULT_NUMBER_OF_CARDS;
        if (args.length >= 2) {
            try {
                numberOfCards = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT);
                System.out.println(PROGRAM_USAGE_TEXT);
                System.exit(0);
            }
        }
        return numberOfCards;
    }

    private static int getNumberOfPlayers(String[] args) {
        int numberOfPlayers = DEFAULT_PLAYER_SIZE;
        if (args.length >= 1) {
            try {
                numberOfPlayers = Integer.parseInt(args[0]);
                if (numberOfPlayers < 2 || numberOfPlayers > 6) {
                    System.out.println(INVALID_NUMBER_OF_PLAYERS);
                    System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT);
                System.out.println(PROGRAM_USAGE_TEXT);
                System.exit(0);
            }
        }
        return numberOfPlayers;
    }

}
