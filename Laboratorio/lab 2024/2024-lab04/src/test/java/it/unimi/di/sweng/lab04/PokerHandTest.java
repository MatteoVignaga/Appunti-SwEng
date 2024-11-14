package it.unimi.di.sweng.lab04;



import static org.assertj.core.api.Assertions.*;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


@Timeout(2)
public class PokerHandTest {
  final static private Card[] THREE_OF_A_KIND_CARDS = {
          Card.get(Rank.ACE, Suit.HEARTS),
          Card.get(Rank.THREE, Suit.HEARTS),
          Card.get(Rank.FIVE, Suit.HEARTS),
          Card.get(Rank.ACE, Suit.CLUBS),
          Card.get(Rank.ACE, Suit.DIAMONDS)
  };

  @Test
  void newPokerHandTest() {
    Card[] cards = THREE_OF_A_KIND_CARDS;
    PokerHand hand = new PokerHand(cards);
    int i = 0;
    for (Card c : hand) {
      assertThat(c).isEqualTo(cards[i]);
      i++;
    }
  }

  final static private Card[] STRAIGHT_FLUSH_CARDS = {
          Card.get(Rank.TEN, Suit.HEARTS),
          Card.get(Rank.NINE, Suit.HEARTS),
          Card.get(Rank.EIGHT, Suit.HEARTS),
          Card.get(Rank.SEVEN, Suit.HEARTS),
          Card.get(Rank.SIX, Suit.HEARTS)
  };

  @Test
  void straightFlushRankTest() {
    PokerHand hand = new PokerHand(STRAIGHT_FLUSH_CARDS);
    assertThat(hand.getRank()).isEqualTo(HandRank.STRAIGHT_FLUSH);
  }

  final static private Card[] FOUR_OF_A_KIND_CARDS = {
          Card.get(Rank.TEN, Suit.HEARTS),
          Card.get(Rank.TEN, Suit.CLUBS),
          Card.get(Rank.TEN, Suit.HEARTS),
          Card.get(Rank.TEN, Suit.SPADES),
          Card.get(Rank.SIX, Suit.CLUBS)
  };

  @Test
  void fourOfAKindRankTest() {
    PokerHand hand = new PokerHand(FOUR_OF_A_KIND_CARDS);
    assertThat(hand.getRank()).isEqualTo(HandRank.FOUR_OF_A_KIND);
  }

  final static private Card[] FULL_HOUSE_CARDS = {
          Card.get(Rank.JACK, Suit.CLUBS),
          Card.get(Rank.JACK, Suit.HEARTS),
          Card.get(Rank.SIX, Suit.HEARTS),
          Card.get(Rank.SIX, Suit.SPADES),
          Card.get(Rank.SIX, Suit.CLUBS)
  };

  @Test
  void fullHouseRankTest() {
    PokerHand hand = new PokerHand(FULL_HOUSE_CARDS);
    assertThat(hand.getRank()).isEqualTo(HandRank.FULL_HOUSE);
  }

  final static private Card[] FLUSH_CARDS = {
          Card.get(Rank.JACK, Suit.CLUBS),
          Card.get(Rank.SEVEN, Suit.CLUBS),
          Card.get(Rank.SIX, Suit.CLUBS),
          Card.get(Rank.THREE, Suit.CLUBS),
          Card.get(Rank.ACE, Suit.CLUBS)
  };
  @Test
  void flushRankTest() {
    PokerHand hand = new PokerHand(FLUSH_CARDS);
    assertThat(hand.getRank()).isEqualTo(HandRank.FLUSH);
  }

  final static private Card[] STRAIGHT_CARDS = {
          Card.get(Rank.JACK, Suit.CLUBS),
          Card.get(Rank.TEN, Suit.HEARTS),
          Card.get(Rank.NINE, Suit.HEARTS),
          Card.get(Rank.EIGHT, Suit.SPADES),
          Card.get(Rank.SEVEN, Suit.CLUBS)
  };
  @Test
  void straightRankTest() {
    PokerHand hand = new PokerHand(STRAIGHT_CARDS);
    assertThat(hand.getRank()).isEqualTo(HandRank.STRAIGHT);
  }

  final static private Card[] THREE_OF_A_KIND = {
          Card.get(Rank.QUEEN, Suit.CLUBS),
          Card.get(Rank.QUEEN, Suit.HEARTS),
          Card.get(Rank.QUEEN, Suit.SPADES),
          Card.get(Rank.EIGHT, Suit.SPADES),
          Card.get(Rank.SEVEN, Suit.CLUBS)
  };
  @Test
  void threeOfaKindRankTest() {
    PokerHand hand = new PokerHand(THREE_OF_A_KIND);
    assertThat(hand.getRank()).isEqualTo(HandRank.THREE_OF_A_KIND);
  }

  final static private Card[] TWO_PAIR_CARDS = {
          Card.get(Rank.QUEEN, Suit.CLUBS),
          Card.get(Rank.QUEEN, Suit.HEARTS),
          Card.get(Rank.KING, Suit.SPADES),
          Card.get(Rank.KING, Suit.HEARTS),
          Card.get(Rank.SEVEN, Suit.CLUBS)
  };
  @Test
  void twoPairRankTest() {
    PokerHand hand = new PokerHand(TWO_PAIR_CARDS);
    assertThat(hand.getRank()).isEqualTo(HandRank.TWO_PAIR);
  }

  final static private Card[] ONE_PAIR_CARDS = {
          Card.get(Rank.QUEEN, Suit.CLUBS),
          Card.get(Rank.QUEEN, Suit.HEARTS),
          Card.get(Rank.FOUR, Suit.SPADES),
          Card.get(Rank.KING, Suit.HEARTS),
          Card.get(Rank.SEVEN, Suit.CLUBS)
  };
  @Test
  void onePairRankTest() {
    PokerHand hand = new PokerHand(ONE_PAIR_CARDS);
    assertThat(hand.getRank()).isEqualTo(HandRank.ONE_PAIR);
  }

  final static private Card[] HIGH_CARD_CARDS = {
          Card.get(Rank.QUEEN, Suit.CLUBS),
          Card.get(Rank.TWO, Suit.HEARTS),
          Card.get(Rank.FOUR, Suit.SPADES),
          Card.get(Rank.KING, Suit.HEARTS),
          Card.get(Rank.SEVEN, Suit.CLUBS)
  };
  @Test
  void highCardRankTest() {
    PokerHand hand = new PokerHand(HIGH_CARD_CARDS);
    assertThat(hand.getRank()).isEqualTo(HandRank.HIGH_CARD);
  }
}
