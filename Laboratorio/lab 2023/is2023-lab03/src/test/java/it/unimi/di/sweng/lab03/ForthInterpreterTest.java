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
  void emptyString() {
    interpreter.input("");
    assertThat(interpreter.toString()).isEqualTo("<- Top");
  }

  @Test
  void soloNumeri() {
    interpreter.input("1 2");
    assertThat(interpreter.toString()).isEqualTo("1 2 <- Top");
  }

  @Test
  void spaziAndNewLines() {
    interpreter.input("1   2 \n3");
    assertThat(interpreter.toString()).isEqualTo("1 2 3 <- Top");
  }

  @Test
  void somme() {
    interpreter.input("1 2 + 5 +");
    assertThat(interpreter.toString()).isEqualTo("8 <- Top");
  }

  @Test
  void exception() {
    assertThatThrownBy(
            () -> interpreter.input("1 2 +5 +"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Token error '+5'");
  }

  @Test
  void stackUnderflow() {
    assertThatThrownBy(
            () -> interpreter.input("1 +"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Stack Underflow");
  }

  @Test
  void moltiplicazioni() {
    interpreter.input("1 2 * 5 *");
    assertThat(interpreter.toString()).isEqualTo("10 <- Top");
  }

  @ParameterizedTest
  @CsvSource({
          "1 2 -    , -1 <- Top",
          "1 2 /, 0 <- Top"
  })
  void sottrazioneDivisione(String program, String output) {
    interpreter.input(program);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "1 2 3 dup, 1 2 3 3 <- Top"
  })
  void dup(String program, String output) {
    interpreter.input(program);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "1 2 3 swap, 1 3 2 <- Top"
  })
  void swap(String program, String output) {
    interpreter.input(program);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "1 2 3 drop, 1 2 <- Top"
  })
  void drop(String program, String output) {
    interpreter.input(program);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "1 2 + 3 * 4 dup 5 + drop swap, 4 9 <- Top"
  })
  void combinazioneOk(String program, String output) {
    interpreter.input(program);
    assertThat(interpreter.toString()).isEqualTo(output);
  }

  @ParameterizedTest
  @CsvSource({
          "1 2 + 3 * drop swap, Stack Underflow"
  })
  void combinazioneErr(String program, String error) {
    assertThatThrownBy(
            () -> interpreter.input("1 +"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Stack Underflow");
  }

}
