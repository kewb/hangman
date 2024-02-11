import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

  private String secretWord;
  private char[] guessedLetters;
  private int maxTries;
  private int remainingTries;
  private Scanner scanner;

  public Hangman(String word) {
    this.secretWord = word;
    this.guessedLetters = new char[word.length()];
    Arrays.fill(guessedLetters, '-');
    this.maxTries = 7;
    this.remainingTries = maxTries;
    this.scanner = new Scanner(System.in);
  }

  public static String word() throws IOException {
    return WordGenerator.getRandomWord();
  }

  public void removeChance() {
    this.remainingTries--;
}


  public int getTries() {
    return this.remainingTries;
  }

  public void guessLetters(char letter) {
    boolean found = false;
    for (int i = 0; i < secretWord.length(); i++) {
      if (letter == secretWord.charAt(i)) {
        guessedLetters[i] = letter;
        found = true;
      }
    }
    if (!found) {
      removeChance();
      System.out.println("Failed to guess, tries remaining:" + getTries());
    }
    for (char c : guessedLetters) {
      System.out.print(c);
    }
  }

  public char[] censoredWord(String word) {
    char[] newArr = new char[word.length()];
    for (int i = 0; i < word.length(); i++) {
      newArr[i] = '-';
    }
    return newArr;
  }

  public void playGame() {
    char[] toGuess = censoredWord(secretWord);
    System.out.println(toGuess);
    System.out.println(secretWord);
    guessLetters('x');
  }

  public static void main(String[] args) {
    try {
      String toGuess = word();
      Hangman hangman = new Hangman(toGuess);
      hangman.playGame();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
