package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Suit;

import java.util.HashMap;
import java.util.Map;

public class FlushEvaluator implements ChainedHandEvaluator {
    ChainedHandEvaluator next;

    public FlushEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }
    @Override
    public HandRank handEvaluator(PokerHand hand) {
        if (isFlush(hand)) {
            return HandRank.FLUSH;
        } else if (next != null){
            return next.handEvaluator(hand);
        } else {
            throw new IllegalStateException("Nessuna mano riconosciuta");
        }
    }

    private boolean isFlush(PokerHand hand) {
        Map<Suit, Integer> suits = new HashMap<>();
        for (Card card : hand) {
            suits.put(card.getSuit(), suits.getOrDefault(card.getSuit(), 0)+1);
        }
        return suits.size() == 1;
    }
}
