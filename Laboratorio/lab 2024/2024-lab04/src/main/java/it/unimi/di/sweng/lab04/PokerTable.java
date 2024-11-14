package it.unimi.di.sweng.lab04;


import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PokerTable implements Iterable<PokerHand> {
    private final List<PokerHand> players = new ArrayList<PokerHand>();
    private final Deck deck=new Deck();

    public PokerTable(int n){
        for(int i=0;i<n;i++){
            Card[] carte=new Card[5];
            for(int j=0;j<5;j++){
                carte[j]=deck.draw();
            }
            players.add(new PokerHand(carte));
        }
    }

    /**
     * Returns the hand of the i-th player
     * @param i index of the player
     * @return the PokerHand object of that player
     */
    public PokerHand getHand(int i) {
        if (!(i >= 0 && i < players.size())) throw new IllegalArgumentException("Non ci sono i giocatori.");
        return players.get(i);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public Iterator<PokerHand> iterator() {
        return players.iterator();
    }
}
