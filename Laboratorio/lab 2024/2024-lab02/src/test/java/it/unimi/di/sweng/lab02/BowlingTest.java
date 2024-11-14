package it.unimi.di.sweng.lab02;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BowlingTest {

  private Bowling game;


  @BeforeEach
  void setUp(){
    game = new BowlingGame();
  }


  @Test
  void gutterGame() {
    for(int i=0; i<20; i++)
      game.roll(0);
    assertThat(game.score()).isEqualTo(0);
  }

  @Test
   void allOnesGame() {
    for(int i=0; i<20; i++)
      game.roll(1);
    assertThat(game.score()).isEqualTo(20);
  }

@Test
   void oneSpareGame() {
    game.roll(5);
    game.roll(5);
    game.roll(3);
    for(int i=0; i<17; i++)
      game.roll(0);
    assertThat(game.score()).isEqualTo(16);
  }

	@Test
   void notSpareGame() {
     // Il test deve simulare una partita in cui vengono colpiti 5 birilli due volte di seguito,
     // ma non all'interno del medesimo frame.
     // Questa condizione non deve essere riconosciuta come spare.
     // Es., roll(1), roll(5), roll(5), ecc., non è spare.
      game.roll(3);
      game.roll(5);
      game.roll(5);
      for(int i=0; i<17; i++)
        game.roll(0);
      assertThat(game.score()).isEqualTo(13);
  }

@Test
   void oneStrikeGame() {
     // Il test deve simulare una partita in cui avviene uno strike.
     // Es., lo score di: roll(10), roll(3), roll(4), roll(0), ..., roll(0), è 24.
    game.roll(10);
    game.roll(3);
    game.roll(4);
    game.roll(0);
    game.roll(0);
    for(int i=0; i<16; i++)
      game.roll(0);
    assertThat(game.score()).isEqualTo(24);
  }

	@Test
   void notStrikeGame() {
     // Il test deve simulare una partita in cui vengono colpiti 10 birilli con il secondo tiro di un frame,
     // Questa condizione deve essere riconosciuta come spare e non come strike.
     // Es., lo score di: roll(0), roll(10), roll(3), roll(0), ..., roll(0), è 16.
      game.roll(0);
      game.roll(10);

      game.roll(5);
      game.roll(0);

      game.roll(5);
      game.roll(0);
      for(int i=0; i<14; i++)
        game.roll(0);
      assertThat(game.score()).isEqualTo(25);
  }

	@Test
   void lastFrameStrikeGame() {
     // Il test deve simulare una partita in cui avviene uno strike nell'ultimo frame.
     // In questo caso il giocatore completa il frame ed ha diritto ad un tiro aggiuntivo.
     // Es., lo score di: roll(0), ..., roll(0), roll(10), roll(3), roll(2), è 15.
      for(int i=0; i<18; i++) {
          game.roll(0);
      }
        game.roll(10);
        game.roll(3);
        game.roll(2);
        assertThat(game.score()).isEqualTo(15);
      }

	@Test
   void perfectGame() {
     // Il test deve simulare una partita perfetta in cui avvengono 12 strike di seguito.
     // Es., lo score di: roll(10), ..., roll(10), è 300.
        for(int i=0; i<12; i++) {
            game.roll(10);
        }
        assertThat(game.score()).isEqualTo(300);
  }

/**/

}
