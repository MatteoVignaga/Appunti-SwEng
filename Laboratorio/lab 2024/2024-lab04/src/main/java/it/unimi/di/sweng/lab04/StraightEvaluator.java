package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class StraightEvaluator implements ChainedHandEvaluator {
    ChainedHandEvaluator next;

    public StraightEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public HandRank handEvaluator(PokerHand hand) {
        if (isStraightFlush(hand)) {
            return HandRank.STRAIGHT;
        } else {
            if (next != null) {
                return next.handEvaluator(hand);
            } else {
                throw new IllegalStateException("Nessuna mano riconosciuta");
            }
        }
    }

    private boolean isStraightFlush(PokerHand hand) {
        List<Integer> ranks = new ArrayList<>();
        for (Card card : hand) {
            ranks.add(card.getRank().ordinal());
        }

        ranks.sort(Integer::compareTo);
        boolean check = true;
        for (int i = 0; i<ranks.size()-2; i++) {
            if (ranks.get(i) != ranks.get(i + 1) - 1) {
                check = false;
                break;
            }
        }
        return check;
    }
}
