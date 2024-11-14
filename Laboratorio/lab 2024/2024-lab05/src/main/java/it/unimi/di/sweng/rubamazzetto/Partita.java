package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import it.unimi.di.sweng.MyDeck;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Partita implements Iterable<Giocatore> {
  //TODO: crea classe MyDeck (sfruttando un pattern adapter che
  // partendo da Deck implementi le funzionalità mancanti richieste
  // da interfaccia CountableDeck) NB è situazione al limite tra Adapter
  // e Decorator... ma consideratela pure come se fosse un Adapter

  @NotNull private final CountableDeck mazzo = new MyDeck();
  @NotNull private final List<Giocatore> giocatori = new ArrayList<>();
  @NotNull private final Tavolo tavolo = new Tavolo();

  public Partita() {
    for (int i = 0; i < 4; i++) {
      tavolo.metti(mazzo.draw());
    }
  }

  public void addGiocatore(@NotNull Giocatore giocatore) {
    giocatori.add(giocatore);
  }

  private void distribuisciCartaSenzaControllo() {
    for (Giocatore giocatore : giocatori) {
      giocatore.daiCarta(this.mazzo.draw());
    }
  }

  public void distribuisciCarta() {
    distribuisciCartaSenzaControllo();

    //POST CONDIZIONI
    for (Giocatore giocatore : giocatori) {
      assert giocatore.numCards() == 3 || (giocatore.numCards() < 3 && mazzo.size() < giocatori.size()) : "si possono avere meno di tre carte solo se nel mazzo non ce ne sono abbastanza per fare un altro giro";
      assert giocatori.get(0).numCards() == giocatore.numCards() : "non è stato dato stesso numero di carte a tutti";
    }
  }

  public void distribuisciCarteIniziali() {
    for (int i = 0; i < 3; i++) {
      distribuisciCartaSenzaControllo();
    }
  }



  public boolean isFinita() {
    assert giocatori.size() > 1;
    int cartegiocate = tavolo.size();
    for (Giocatore giocatore : giocatori) {
      cartegiocate += giocatore.getPunti();
    }
    return mazzo.size() < giocatori.size() && 52 - cartegiocate == mazzo.size();
  }


  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Giocatore giocatore : giocatori) {
      s.append(giocatore.toString());
      s.append("\n");
    }
    s.append("Tavolo: ");
    s.append(tavolo);
    s.append("\n");
    s.append("Finita: ");
    s.append(isFinita());
    return s.toString();
  }


  public boolean controllaSeCartaPresenteSuTavolo(@NotNull Card card) {

    return tavolo.inMostra(card);
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @NotNull
  @Override
  public Iterator<Giocatore> iterator() {
    return giocatori.iterator();
  }


  // TODO implementare metodo giocaCarta che esegue la giocata
  // secondo le regole e restituisce i punti ottenuti

}
