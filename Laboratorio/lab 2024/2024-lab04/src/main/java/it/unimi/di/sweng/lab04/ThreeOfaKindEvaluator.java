package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;

import java.util.HashMap;
import java.util.Map;

public class ThreeOfaKindEvaluator implements ChainedHandEvaluator {
    private ChainedHandEvaluator next;

    public ThreeOfaKindEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand hand) {
        if (isThreeOfaKind(hand)) {
            return HandRank.THREE_OF_A_KIND;
        } else if (next != null) {
            return next.handEvaluator(hand);
        } else {
            throw new IllegalStateException("Nessuna mano riconosciuta");
        }
    }

    private boolean isThreeOfaKind(PokerHand hand) {
        Map<Rank, Integer> rankAmounts = new HashMap<>();
        for (Card card : hand) {
            rankAmounts.put(card.getRank(), rankAmounts.getOrDefault(card.getRank(), 0) + 1);
        }

        return rankAmounts.containsValue(3) && rankAmounts.containsValue(1);
    }
}
