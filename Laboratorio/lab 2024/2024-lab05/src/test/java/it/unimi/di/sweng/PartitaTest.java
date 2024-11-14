package it.unimi.di.sweng;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import it.unimi.di.sweng.rubamazzetto.Giocatore;
import it.unimi.di.sweng.rubamazzetto.Partita;
import it.unimi.di.sweng.rubamazzetto.Tavolo;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PartitaTest {
    @Test
    public void iterableTest() {
        Partita partita = new Partita();
        Giocatore matteo = new Giocatore("Matteo", partita);
        Giocatore lorenzo = new Giocatore("Lorenzo", partita);

        assertThat(partita).containsExactlyInAnyOrder(matteo,lorenzo);
    }

    @Test
    public void distribuisciCarteInizialiTest() {
        Partita partita = new Partita();
        Giocatore matteo = new Giocatore("Matteo", partita);
        Giocatore lorenzo = new Giocatore("Lorenzo", partita);
        partita.distribuisciCarteIniziali();

        assertThat(matteo.numCards()).isEqualTo(3);
        assertThat(lorenzo.numCards()).isEqualTo(3);
    }

    @Test
    public void controllaSeCartaPresenteSuTavolo() throws NoSuchFieldException, IllegalAccessException {
        Partita p = new Partita();
        Giocatore matteo = new Giocatore("Matteo", p);
        Giocatore lorenzo = new Giocatore("Lorenzo", p);
        Field tav = Partita.class.getDeclaredField("tavolo");
        tav.setAccessible(true);

        Tavolo newTavolo = new Tavolo();
        Card c = Card.get(Rank.ACE, Suit.CLUBS);
        newTavolo.metti(c);

        tav.set(p, newTavolo);

        assertThat(p.controllaSeCartaPresenteSuTavolo(c)).isEqualTo(true);
    }
}
