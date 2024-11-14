package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Suit;

import java.util.ArrayList;
import java.util.List;

public class StraightFlushEvaluator implements ChainedHandEvaluator {
    ChainedHandEvaluator next;

    public StraightFlushEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand hand) {
        if (isStraightFlush(hand)) {
            return HandRank.STRAIGHT_FLUSH;
        } else {
            if (next != null) {
                return next.handEvaluator(hand);
            } else {
                throw new IllegalStateException("Nessuna mano riconosciuta");
            }
        }
    }

    private boolean isStraightFlush(PokerHand hand) {
        Suit firstSuit = hand.iterator().next().getSuit();
        List<Integer> cards = new ArrayList<>();

        for (Card card : hand) {
            if (!card.getSuit().equals(firstSuit) ) {
                return false;
            }
            cards.add(card.getRank().ordinal());
        }

        cards.sort(Integer::compareTo);
        for (int i = 0; i < cards.size()-2; i++) {
            if (cards.get(i)+1 != cards.get(i+1)) {
                return false;
            }
        }

        return true;
    }
}
