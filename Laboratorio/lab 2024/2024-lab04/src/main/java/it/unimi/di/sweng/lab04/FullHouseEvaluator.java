package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.HashMap;
import java.util.Map;

public class FullHouseEvaluator implements ChainedHandEvaluator {
    ChainedHandEvaluator next;

    public FullHouseEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }
    @Override
    public HandRank handEvaluator(PokerHand hand) {
        if (isFullHouse(hand)) {
            return HandRank.FULL_HOUSE;
        } else {
            if (next != null) {
                return next.handEvaluator(hand);
            } else {
                throw new IllegalStateException("Nessuna mano riconosciuta");
            }
        }
    }

    private boolean isFullHouse(PokerHand hand) {
        Map<Rank, Integer> ranksAmounts = new HashMap<>();
        for (Card card : hand) {
            ranksAmounts.put(card.getRank(), ranksAmounts.getOrDefault(card.getRank(), 0) + 1);
        }

        return ranksAmounts.containsValue(2) && ranksAmounts.containsValue(3);
    }
}
