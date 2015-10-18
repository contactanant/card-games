package com.alph.games.cards.domain;

public enum SpecialCard {
    ACE(1),
    JACK(11),
    QUEEN(12),
    KING(13);

    int defaultValue;

    SpecialCard(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getDefaultValue() {
        return defaultValue;
    }
}
