package it.unimi.di.sweng.lab04;

import ca.mcgill.cs.stg.solitaire.cards.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OnePairEvaluator implements ChainedHandEvaluator {
    ChainedHandEvaluator next;

    public OnePairEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public PokerHand.HandRank handEvaluator(PokerHand hand) {
        if (isOnePair(hand)) {
            return PokerHand.HandRank.ONE_PAIR;
        } else {
            return next.handEvaluator(hand);
        }
    }

    private boolean isOnePair(PokerHand hand) {
        Map<Rank, Integer> occorrenze = new HashMap<>();
        Iterator<Card> it = hand.iterator();

        while (it.hasNext()) {
            Card card = it.next();
            if (occorrenze.containsKey(card.getRank())) {
                Integer o = occorrenze.get(card.getRank())+1;
                occorrenze.put(card.getRank(), o);
            } else {
                occorrenze.put(card.getRank(), 1);
            }
        }

        if (occorrenze.size() == 4 && occorrenze.containsValue(2)) {
            return true;
        } else {
            return false;
        }
    }
}
