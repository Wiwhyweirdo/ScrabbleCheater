package scrabble.data;

import scrabble.util.Permutation;
import scrabble.util.SubSets;

import java.io.*;
import java.util.*;

import static scrabble.data.WordList.MIN_WORD_LENGTH;

public class SimpleWordList implements WordList {
	private Set	<String> words;

	public SimpleWordList(){
		this.words=new HashSet<>();

	}

	@Override
	public Set<String> validWordsUsingAllTiles(String tileRackPart) {

		Set<String> validWords = new HashSet<>();
		Permutation tilePermutation = new Permutation(tileRackPart);
		for (String word : words) {
			if (word.length() == tileRackPart.length()) {
				Permutation wordPermutation = new Permutation(word);
				if (wordPermutation.equals(tilePermutation)) {
					validWords.add(word);
				}
			}
		}
		return validWords;
	}

	private String normalize(String input){
		char[] chars=input.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

	@Override
	public Set<String> allValidWords(String tileRack) {
		Set<String> allWords = new HashSet<>();
		Set<String> subsets = SubSets.getSubSets(tileRack);

		for (String subset: subsets) {
			if (subset.length() >= MIN_WORD_LENGTH) {
			allWords.addAll(validWordsUsingAllTiles(subset));
			}
		}
		return allWords;
	}

	@Override
	public boolean add(String word) {

		return words.add(word.toLowerCase());
	}

	@Override
	public boolean addAll(Collection<String> words) {
		boolean changed = false;
		for (String word : words) {
			add(word);
		}
		return changed;
	}

	@Override
	public int size() {
		return words.size();
	}

	@Override
	public WordList initFromFile(String fileName) {
		try(BufferedReader reader=new BufferedReader(new FileReader(fileName))){
			String line;
			while((line=reader.readLine())!=null){
				line=line.trim().toLowerCase();
				if(line.length()>=MIN_WORD_LENGTH){
					add(line);
				}
			}
		} catch(IOException e){
			System.out.println("oopsies");
		}
		return this;
	}

}
