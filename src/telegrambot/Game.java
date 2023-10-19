package telegrambot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    public List<String> wordlist = new ArrayList<>();
    public List<Character> correctAnswers = new ArrayList<>();
    public List<Character> userGuesses = new ArrayList<>();
    public String secretWord;
    public int counter;
    private boolean playGame;

    public boolean isOn() {
        return playGame;
    }

    public void setPlayGame(boolean playGame) {
        this.playGame = playGame;
    }

    private void words() throws IOException {
        wordlist = Files.readAllLines(Paths.get("dictionary.txt"));
    }

    public String setGame() throws IOException {
        words();
        wordToCharList(chooseRandomWord());

        return printField();
    }

    public String playGame(String answer) {
        correctLetters(answer);
        state();

        return printField();
    }

    public String chooseRandomWord() {
        int randomIndex = ThreadLocalRandom.current().nextInt(wordlist.size());

        String randomWord = wordlist.get(randomIndex).toUpperCase();
        this.secretWord = randomWord;

        return randomWord;
    }

    private void wordToCharList(String word) {
        for (Character c : word.toUpperCase().toCharArray()) {
            correctAnswers.add(c);
        }
    }

    public void correctLetters(String answer) {
        Character letter = answer.toUpperCase().charAt(0);

        if (!userGuesses.contains(letter)) {
            userGuesses.add(letter);
            if (!correctAnswers.contains(letter)) {
                counter++;
            }
        }
    }

    public String printField() {

        StringBuilder sb = new StringBuilder();
        sb.append(drawAscii());

        for (int i = 0; i < correctAnswers.size(); i++) {
            if (userGuesses.contains(correctAnswers.get(i))) {
                sb.append(correctAnswers.get(i));
            } else {
                sb.append("_ ");
            }
        }
        return sb.toString();
    }

    public boolean state() {
        if (counter == 7) {
            return false;
        } else if (userGuesses.containsAll(correctAnswers)) {
            return false;
        } else {
            return true;
        }
    }

    public String end(String userName) {

        if (userGuesses.containsAll(correctAnswers)) {
            String won = "Very well done, " + userName + "! 🥳\n\n" +
                    "You should play another game 🎉 Click /playagain";
            return won;
        } else {
            String lost = "Oh no, that's a pity! 😭" +
                    " The word was " + secretWord.toUpperCase() + ".\n\n" +
                    "Don't worry, better luck awaits next time! \uD83C\uDF40\n\n" +
                    "Ready for another round? 🎯 Just klick on /playagain";                    ;
            return lost;
        }
    }

    public void clearField() {
        correctAnswers.clear();
        userGuesses.clear();
        counter = 0;
    }

    public String drawAscii() {
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        for (Character c : userGuesses) {
            sb3.append(c);
            sb3.append(" ");
        }
        sb3.trimToSize();

        int incorrectGuesses = counter; //

        // Append different parts of the Hangman figure based on the number of incorrect guesses
        switch (incorrectGuesses) {
            case 0:
                sb2.append("  +-----+          Guessed letters: " + sb3 + "\n");
                sb2.append("|       \n");
                sb2.append("|      \n");
                sb2.append("|      \n");
                sb2.append("|      \n");
                sb2.append("|      \n");
                sb2.append("=========\n");
                sb2.append("\n");
                break;
            case 1:
                sb2.append("  +-----+          Guessed letters: " + sb3 + "\n");
                sb2.append("|       |\n");
                sb2.append("|      \n");
                sb2.append("|      \n");
                sb2.append("|      \n");
                sb2.append("|      \n");
                sb2.append("=========\n");
                sb2.append("\n");
                break;
            case 2:
                sb2.append("  +-----+          Guessed letters: " + sb3 + "\n");
                sb2.append("|       |\n");
                sb2.append("|       O\n");
                sb2.append("|      \n");
                sb2.append("|      \n");
                sb2.append("|      \n");
                sb2.append("=========\n");
                sb2.append("\n");
                break;
            case 3:
                sb2.append("  +-----+          Guessed letters: " + sb3 + "\n");
                sb2.append("|       |\n");
                sb2.append("|       O\n");
                sb2.append("|       |\n");
                sb2.append("|      \n");
                sb2.append("|      \n");
                sb2.append("=========\n");
                sb2.append("\n");
                break;
            case 4:
                sb2.append("  +-----+          Guessed letters: " + sb3 + "\n");
                sb2.append("|       |\n");
                sb2.append("|       O\n");
                sb2.append("|      /|\n");
                sb2.append("|      \n");
                sb2.append("|      \n");
                sb2.append("=========\n");
                sb2.append("\n");
                break;
            case 5:
                sb2.append("  +-----+          Guessed letters: " + sb3 + "\n");
                sb2.append("|       |\n");
                sb2.append("|       O\n");
                sb2.append("|      /|\\\n");
                sb2.append("|      \n");
                sb2.append("|      \n");
                sb2.append("=========\n");
                sb2.append("\n");
                    break;
            case 6:
                sb2.append("  +-----+          Guessed letters: " + sb3 + "\n");
                sb2.append("|       |\n");
                sb2.append("|       O\n");
                sb2.append("|      /|\\\n");
                sb2.append("|      /\n");
                sb2.append("|      \n");
                sb2.append("=========\n");
                sb2.append("\n");
                break;
            case 7:
                sb2.append("  +-----+          Guessed letters: " + sb3 + "\n");
                sb2.append("|       |\n");
                sb2.append("|      💀\n");
                sb2.append("|      /|\\\n");
                sb2.append("|      /  \\\n");
                sb2.append("|      \n");
                sb2.append("=========\n");
                sb2.append("\n");
                break;
            default:
                // This is when the game is won or lost, so no Hangman figure is displayed
                break;
        }
        return sb2.toString();
    }
}
