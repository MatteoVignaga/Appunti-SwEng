package it.unimi.di.sweng;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import it.unimi.di.sweng.rubamazzetto.CountableDeck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MyDeckTest {
    @Test
    public void myDeckSizeTest() {
        MyDeck testedDeck = new MyDeck();
        assertThat(testedDeck.size()).isEqualTo(52);

        testedDeck.draw();
        assertThat(testedDeck.size()).isEqualTo(51);
    }



}
