package it.unimi.di.sweng.lab04;



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
public class PokerHandTest {
  private static final List<Card> RANDOM_HAND = List.of(Card.get(Rank.ACE, Suit.CLUBS),
          Card.get(Rank.THREE, Suit.HEARTS),
          Card.get(Rank.SEVEN, Suit.SPADES),
          Card.get(Rank.QUEEN, Suit.SPADES),
          Card.get(Rank.KING, Suit.DIAMONDS)
          );

  private static final List<Card> ONE_PAIR = List.of(Card.get(Rank.EIGHT, Suit.CLUBS),
          Card.get(Rank.THREE, Suit.HEARTS),
          Card.get(Rank.THREE, Suit.SPADES),
          Card.get(Rank.QUEEN, Suit.SPADES),
          Card.get(Rank.ACE, Suit.DIAMONDS)
  );

  @Test
  void newPokerHandTest() {
    PokerHand hand = new PokerHand(RANDOM_HAND);
    List<Card> cards = hand.getCards();
    assertThat(cards).containsExactlyInAnyOrderElementsOf(RANDOM_HAND);
  }

  @Test
  void pokerHandIterator() {
    PokerHand hand = new PokerHand(RANDOM_HAND);
    Iterator<Card> it = hand.iterator();
    for (Card card : RANDOM_HAND) {
      assertThat(it.next()).isEqualTo(card);
    }
  }

  @Test
  void onePairEvaluate() {
    PokerHand hand = new PokerHand(RANDOM_HAND);
    assertThat(hand.getRank()).isEqualTo(PokerHand.HandRank.ONE_PAIR);
  }

}
