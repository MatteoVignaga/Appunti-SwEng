package it.unimi.di.sweng.tresette;

import it.unimi.di.sweng.tresette.common.Rank;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class RankTest {
    @Test
    public void orderTest() {
        List<Rank> ranks = List.of(Rank.values());
        assertThat(ranks.sort(new Comparator<Rank>() {
            @Override
            public int compare(Rank o1, Rank o2) {
                return 0;
            }
        });).contains
    }

}
