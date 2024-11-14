package it.unimi.di.sweng.tresette.common;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public enum Rank {
  //TODO: non potete modificare ordine dei valori,
  //      ma potete se volete aggiungere attributi e metodi
  ASSO,
  DUE,
  TRE,
  QUATTRO,
  CINQUE,
  SEI,
  SETTE,
  FANTE,
  CAVALLO,
  RE;

  Map<Rank,Integer> orders = new HashMap<>();
  orders.

  public Comparator<Rank> COMPARATORE = new Comparator<Rank>() {



    @Override
    public int compare(Rank o1, Rank o2) {
      return 0;
    }
  });
}
