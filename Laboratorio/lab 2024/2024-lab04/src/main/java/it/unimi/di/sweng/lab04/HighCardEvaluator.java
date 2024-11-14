package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.HashMap;
import java.util.Map;

public class HighCardEvaluator implements ChainedHandEvaluator {
    ChainedHandEvaluator next;

    public HighCardEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand hand) {
        if (isHighCard(hand)) {
            return HandRank.HIGH_CARD;
        } else {
            if (next != null) {
                return next.handEvaluator(hand);
            } else {
                throw new IllegalStateException("Nessuna mano riconosciuta");
            }
        }
    }

    private boolean isHighCard(PokerHand hand) {
        Map<Rank, Integer> rankAmounts = new HashMap<>();
        for (Card card : hand) {
            rankAmounts.put(card.getRank(), rankAmounts.getOrDefault(card.getRank(), 0) + 1);
        }

        int pairCount = 0;
        for (int count : rankAmounts.values()) {
            if (count == 2) pairCount++;
        }

        return pairCount == 0;
    }
}
