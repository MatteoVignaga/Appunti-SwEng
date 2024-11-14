package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.HashMap;
import java.util.Map;

public class TwoPairEvaluator implements ChainedHandEvaluator {
    ChainedHandEvaluator next;

    public TwoPairEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand hand) {
        if (isTwoPair(hand)) {
            return HandRank.TWO_PAIR;
        } else {
            if (next != null) {
                return next.handEvaluator(hand);
            } else {
                throw new IllegalStateException("Nessuna mano riconosciuta");
            }
        }
    }

    private boolean isTwoPair(PokerHand hand) {
        Map<Rank, Integer> rankAmounts = new HashMap<>();
        for (Card card : hand) {
            rankAmounts.put(card.getRank(), rankAmounts.getOrDefault(card.getRank(), 0) + 1);
        }

        int pairCount = 0;
        for (int count : rankAmounts.values()) {
            if (count == 2) pairCount++;
        }

        return pairCount == 2;
    }
}
