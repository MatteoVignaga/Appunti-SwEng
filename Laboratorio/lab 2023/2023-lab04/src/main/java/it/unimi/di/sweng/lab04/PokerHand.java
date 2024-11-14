package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PokerHand implements Iterable<Card> {
    private List<Card> hand;

    public enum HandRank {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        STRAIGHT,
        FLUSH,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        STRAIGHT_FLUSH
    }

    public PokerHand(List<Card> list) {
        if (list.size() != 5) throw new IllegalArgumentException("The hand must contain 5 cards.");
        hand = new ArrayList<Card>(list);
    }

    protected List<Card> getCards() {
        return List.copyOf(hand);
    }

    public HandRank getRank() {
        ChainedHandEvaluator evaluator = new OnePairEvaluator(new HighCardEvaluator(null));
        return evaluator.handEvaluator(this);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<Card> iterator() {
        return hand.iterator();
    }
}


