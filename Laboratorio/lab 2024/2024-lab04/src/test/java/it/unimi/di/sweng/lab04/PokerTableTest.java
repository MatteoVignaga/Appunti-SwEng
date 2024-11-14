package it.unimi.di.sweng.lab04;



import static org.assertj.core.api.Assertions.*;

import ca.mcgill.cs.stg.solitaire.cards.Card;
import ca.mcgill.cs.stg.solitaire.cards.Deck;
import ca.mcgill.cs.stg.solitaire.cards.Rank;
import ca.mcgill.cs.stg.solitaire.cards.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Timeout(2)
public class PokerTableTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "4, 4",
            "8, 8"
    })
    void newPokerTableTest(int input, int output) {
        PokerTable t = new PokerTable(input);

        int i=0;
        for(PokerHand player : t ){
            i++;
        }
        assertThat(i).isEqualTo(output);
    }

    @Test
    void getHandTest() {
        PokerTable table = new PokerTable(5);
        PokerHand secondPlayer = table.getHand(1);
        assertThat(secondPlayer).containsExactlyInAnyOrder();
    }

}
