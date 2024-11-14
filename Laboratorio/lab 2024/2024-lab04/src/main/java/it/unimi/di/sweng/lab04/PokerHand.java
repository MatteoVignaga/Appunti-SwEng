package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PokerHand implements Iterable<Card> {
    private final List<Card> cards = new ArrayList<>();
    public PokerHand(Card[] cards) {
        Collections.addAll(this.cards, cards);
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder();
        for (Card c : cards) {
            print.append(c.toString()).append(" ");
        }
        return print.toString();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    public HandRank getRank() {
        StraightFlushEvaluator st = new StraightFlushEvaluator(new FourOfaKindEvaluator(new FullHouseEvaluator(new FlushEvaluator(new StraightEvaluator(new ThreeOfaKindEvaluator(new TwoPairEvaluator(new OnePairEvaluator(new HighCardEvaluator(null)))))))));
        return st.handEvaluator(this);
    }
}


