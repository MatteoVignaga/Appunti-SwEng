package it.unimi.di.sweng.lab04;

public class HighCardEvaluator implements ChainedHandEvaluator {
    ChainedHandEvaluator next;
    public HighCardEvaluator(ChainedHandEvaluator next) {
        this.next = next;
    }

    @Override
    public PokerHand.HandRank handEvaluator(PokerHand hand) {
        if (isHighCard(hand)) {
            return PokerHand.HandRank.HIGH_CARD;
        } else {
            return next.handEvaluator(hand);
        }
    }

    private boolean isHighCard(PokerHand hand) {
        return true;
    }


}
