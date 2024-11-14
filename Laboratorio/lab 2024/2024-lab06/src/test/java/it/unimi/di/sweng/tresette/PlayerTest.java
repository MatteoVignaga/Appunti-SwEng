package it.unimi.di.sweng.tresette;

import it.unimi.di.sweng.tresette.common.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PlayerTest {

    Player player = new Player("Signor Mock");

    @Test
    public void iterableTest() {
        List<Card> lista = TestUtils.cardsFrom("CS,AB,3D");
        for (Card c : lista) {
            player.takeDrawnCard(c);
        }
        Card[] a = lista.toArray(new Card[3]);
        assertThat(player).containsExactlyInAnyOrder(a);
    }

    @Test
    public void collectCardsTest() throws IllegalAccessException, NoSuchFieldException {
        List<Card> lista = TestUtils.cardsFrom("CS,AB,3D,4D");
        for (int i = 0; i < lista.size(); i+=2) {
            player.collectCards(lista.get(i), lista.get(i+1));
        }
        Card[] a = lista.toArray(new Card[4]);

        Field deck = Player.class.getDeclaredField("personalDeck");
        deck.setAccessible(true);
        List<Card> playersDeck = (List<Card>) deck.get(player);
        assertThat(playersDeck).containsExactlyInAnyOrder(a);
    }

    @ParameterizedTest
    @CsvSource({
            "'AS,AB,AD,7D', 3",
            "'AC,RS,7D,RC', 1",
            "'RB,RC,RD,4C', 1",
            "'RB,RC,RD,AC', 2"
    })
    public void getPointsTest(String input, int expected) {
        List<Card> lista = TestUtils.cardsFrom(input);
        for (int i = 0; i < lista.size(); i+=2) {
            player.collectCards(lista.get(i), lista.get(i+1));
        }

        assertThat(player.getPoints()).isEqualTo(expected);


    }

    @ParameterizedTest
    @CsvSource({
            "'AS,AB,AD,7D', 4",
            "'AC,RS,7D,RC', 2",
            "'RB,RC,RD,4C', 2",
            "'RB,RC,RD,AC', 3"
    })
    public void getPointsLastPlayerTest(String input, int expected) {
        List<Card> lista = TestUtils.cardsFrom(input);
        for (int i = 0; i < lista.size(); i+=2) {
            player.collectCards(lista.get(i), lista.get(i+1));
        }

        player.setLastTaken();
        assertThat(player.getPoints()).isEqualTo(expected);
    }



}
