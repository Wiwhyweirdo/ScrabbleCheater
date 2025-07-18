package scrabble.data;

import scrabble.util.Permutation;
import scrabble.util.SubSets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HashMapWordList implements WordList {
    private Map<String, Set<String>> normalizedWordMap;

    public HashMapWordList() {
        this.normalizedWordMap = new HashMap();
    }

    @Override
    public Set<String> validWordsUsingAllTiles(String tileRackPart) {
        Permutation key = new Permutation(tileRackPart);
        String normalized = key.getNormalized();
        return normalizedWordMap.getOrDefault(normalized, new HashSet<>());
    }

    @Override
    public Set<String> allValidWords(String tileRack) {
        Set<String> allWords = new HashSet<>();
        Set<String> subsets = SubSets.getSubSets(tileRack);

        for (String subset : subsets) {
            if (subset.length() >= MIN_WORD_LENGTH) {
                allWords.addAll(validWordsUsingAllTiles(subset));
            }
        }
        return allWords;
    }

    @Override
    public boolean add(String word) {
        Permutation key = new Permutation(word);
        String normalized = key.getNormalized();
        normalizedWordMap.computeIfAbsent(normalized, k -> new HashSet<>()).add(word);
        return true;
    }

    @Override
    public boolean addAll(Collection<String> words) {
        for (String word : words) {
            add(word);
        }
        return true;
    }

    @Override
    public int size() {
        int count = 0;
        for (Set<String> wordSet : normalizedWordMap.values()) {
            count += wordSet.size();
        }
        return count;
    }

    @Override
    public WordList initFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim().toLowerCase();
                if (line.length() >= MIN_WORD_LENGTH) {
                    add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("not working");
        }
        return this;
    }
}
