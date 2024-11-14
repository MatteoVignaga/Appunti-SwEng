package it.unimi.di.sweng;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import it.unimi.di.sweng.rubamazzetto.CountableDeck;

import java.util.ArrayList;
import java.util.List;

public class MyDeck extends Deck implements CountableDeck {
    @Override
    public int size() {
        int count = 0;
        List<Card> temp = new ArrayList<>();

        while (!this.isEmpty()) {
            temp.add(this.draw());
            count++;
        }

        for (Card removedCard : temp) {
            this.push(removedCard);
        }

        return count;
    }
}
