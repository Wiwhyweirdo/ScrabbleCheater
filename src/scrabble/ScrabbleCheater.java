package scrabble;

import scrabble.data.HashMapWordList;
import scrabble.data.SimpleWordList;
import scrabble.data.WordList;

import java.util.Scanner;
import java.util.Set;

public class ScrabbleCheater {
    public static void main(String[] args) {
        String filename = "wordlists/sowpods.txt";
        WordList wordList;

        Scanner scanner = new Scanner(System.in);
        System.out.println("simple or hashmap as wordlist?");
        System.out.println("> ");
        String chosenImplementation = scanner.nextLine();

        if (chosenImplementation.equalsIgnoreCase("hashmap")) {
            System.out.println("hashmap chosen");
            wordList = new HashMapWordList();
        } else {
            System.out.println("simple chosen");
            wordList = new SimpleWordList();
        }
        wordList.initFromFile(filename);


        System.out.println("Enter your tile rack. 'quit' to exit");
        while (true) {
            System.out.println("> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("quit")) {
                break;
            }

            System.out.println("Words using all Tiles:");
            Set<String> allTilesWords = wordList.validWordsUsingAllTiles(input);
            if (allTilesWords.isEmpty()) {
                System.out.println(" no words possible");
            } else {
                allTilesWords.forEach(word -> System.out.println(" " + word));
            }

            System.out.println("All possible words:");
            Set<String> allWords = wordList.allValidWords(input);
            if (allWords.isEmpty()) {
                System.out.println("  no words possible");
            } else {
                allWords.forEach(word -> System.out.println("  " + word));
            }
        }

        scanner.close();
        System.out.println("Bye Bye!");
    }
}
