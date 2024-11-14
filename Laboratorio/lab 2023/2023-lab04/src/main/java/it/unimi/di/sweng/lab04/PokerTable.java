package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Deck;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PokerTable implements Iterable<PokerHand> {
    protected Deck deck = new Deck();
    protected List<PokerHand> players = new ArrayList<>();

    public PokerTable(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            PokerHand hand = new PokerHand(List.of(deck.draw(), deck.draw(), deck.draw(), deck.draw(), deck.draw()));
            players.add(hand);
        }
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<PokerHand> iterator() {
        return players.iterator();
    }
}
