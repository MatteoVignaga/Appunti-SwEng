package it.unimi.di.sweng.lab03;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


@Timeout(2)
public class ForthInterpreterTest {
  private Interpreter interpreter;

  @BeforeEach
  public void setUp() {
    interpreter = new ForthInterpreter();
  }

  @Test
  public void emptyStackTest() {
    interpreter.input("");
    assertThat(interpreter.toString()).isEqualTo("<- Top");
  }

  @ParameterizedTest
  @CsvSource({
          "1, 1 <- Top",
          "1 2, 1 2 <- Top"
  })
  public void onlyNumbers(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "'1\n2', 1 2 <- Top",
          "'1   2 \n3', 1 2 3 <- Top"
  })
  public void newLinesAndSpaces(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "'1 2 +', 3 <- Top",
          "'1 2 + 5 +', 8 <- Top"
  })
  public void somma(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "'1 2+', Token error: 2+",
          "'1 2 +5 +', Token error: +5"
  })
  public void eccezioneSomma(String input1, String output){
    assertThatThrownBy(
            () -> interpreter.input(input1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(output);
  }

  @Test
  public void notEnoughOp() {
    assertThatThrownBy(
            () -> interpreter.input("1 +"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Stack Underflow");
  }

  @ParameterizedTest
  @CsvSource({
          "'1 2 *', 2 <- Top",
          "'1 2 * 5 *', 10 <- Top"
  })
  public void prodotto(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @Test
  public void notEnoughOpProdotto() {
    assertThatThrownBy(
            () -> interpreter.input("1 *"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Stack Underflow");
  }

  @ParameterizedTest
  @CsvSource({
          "'1 2 -', -1 <- Top",
          "'1 2 - 5 -', -6 <- Top"
  })
  public void sottrazione(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @Test
  public void notEnoughOpSottrazione() {
    assertThatThrownBy(
            () -> interpreter.input("1 -"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Stack Underflow");
  }
  @ParameterizedTest
  @CsvSource({
          "'1 2 /', 0 <- Top",
          "'10 2 / 5 /', 1 <- Top"
  })
  public void divisione(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @Test
  public void notEnoughOpDivisione() {
    assertThatThrownBy(
            () -> interpreter.input("1 /"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Stack Underflow");
  }

    @ParameterizedTest
    @CsvSource({
            "'1 2 3 dup', 1 2 3 3 <- Top"
    })
    public void duplica(String input, String output){
      interpreter.input(input);
      assertThat(interpreter.toString()).isEqualTo(output);
    }

  @ParameterizedTest
  @CsvSource({
          "'1 2 3 swap', 1 3 2 <- Top"
  })
  public void swap(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "'1 2 3 drop', 1 2 <- Top"
  })
  public void drop(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "'1 2 + 3 * 4 dup 5 + drop swap', 4 9 <- Top"
  })
  public void combinaComandi(String input, String output){
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "': raddoppia 2 * ; 5 raddoppia dup raddoppia', 10 20 <- Top"
  })
  public void comandiPersonalizzati(String input, String output) {
    interpreter.input(input);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "'pippo', Token error: pippo",
          "'1 2 pippo', Token error: pippo",
          "'1 : raddoppia 2 * ; raddoppi raddoppia', Token error: raddoppi"
  })
  public void checkTokenErrors(String input, String output) {
    assertThatThrownBy(
            () -> interpreter.input(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(output);
  }
}
