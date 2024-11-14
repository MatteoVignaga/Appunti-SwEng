package it.unimi.di.sweng.rubamazzetto;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Tavolo  {

  @NotNull private final List<Card> scoperte = new ArrayList<>();

  public boolean inMostra(@NotNull Card aCard) {
    for (Card card : scoperte) {
      if (card.getRank() == aCard.getRank())
        return true;
    }
    return false;
  }

  public void prendi(@NotNull Card aCard) {
    for (Card card : scoperte) {
      if (card.getRank() == aCard.getRank()) {
        scoperte.remove(card);
        return;
      }
    }
  }

  public void metti(@NotNull Card aCard) {
    scoperte.add(aCard);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(scoperte.size());
    s.append(" -> ");

    for (Card card : scoperte) {
      s.append(card.toString());
      s.append(", ");
    }
    return s.toString();
  }


  public int size() {
    return scoperte.size();
  }
}
