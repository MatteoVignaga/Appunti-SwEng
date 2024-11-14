package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.HashMap;
import java.util.Map;

public class FourOfaKindEvaluator implements ChainedHandEvaluator {
    ChainedHandEvaluator next;

    public FourOfaKindEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand hand) {
        if (isFourOfaKind(hand)) {
            return HandRank.FOUR_OF_A_KIND;
        } else {
            if (next != null) {
                return next.handEvaluator(hand);
            } else {
                throw new IllegalStateException("Nessuna mano riconosciuta");
            }
        }
    }

    private boolean isFourOfaKind(PokerHand hand) {
        Map<Rank, Integer> rankAmounts = new HashMap<>();
        for (Card card : hand) {
            rankAmounts.put(card.getRank(), rankAmounts.getOrDefault(card.getRank(), 0) + 1);
        }

        return rankAmounts.containsValue(4) && rankAmounts.containsValue(1);
    }
}
