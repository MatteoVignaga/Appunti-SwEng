package it.unimi.di.sweng.lab04;

import org.junit.jupiter.api.Timeout;
import static org.assertj.core.api.Assertions.*;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Timeout(2)

public class PokerTableTest {
    @Test
    void PokerTableTest() {
        PokerTable game = new PokerTable(5);
        assertThat(game.deck).isInstanceOf(Deck.class);
        assertThat(game.players.size()).isEqualTo(5);
        for (PokerHand player : game.players) {
            assertThat(player).isInstanceOf(PokerHand.class);
        }
    }
}
