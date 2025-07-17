package scrabble.data;

import scrabble.util.Permutation;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Arrays;

public class SimpleWordList implements WordList {
	private Set	<String> words;
	public SimpleWordList(){
		this.words=new HashSet<>();
	}

	@Override
	public Set<String> validWordsUsingAllTiles(String tileRackPart) {
		Set<String>validWords=new HashSet<>();
		String normalizeTileRack=normalize(tileRackPart);
		int length=tileRackPart.length();
		for(String word:words){
		if(word.length()==length){
			String normalizedWord = normalize(word);
			if(normalizedWord.equals(normalize(normalizeTileRack))){
				validWords.add(word);
			}

			}
		}
		return null;
	}
	private String normalize(String input){
		char[] chars=input.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}


	@Override
	public Set<String> allValidWords(String tileRack) {
		return null;
	}

	@Override
	public boolean add(String word) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<String> words) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return words.size();
	}

	@Override
	public WordList initFromFile(String fileName) {
		try(BufferedReader reader=new BufferedReader(new FileReader(fileName))){
			String line;
			while((line=reader.readLine())!=null){
				line=line.trim().toLowerCase();
				if(line.length()>=MIN_WORD_LENGTH){
					words.add(line);
				}
			}
		} catch(IOException e){
		}
		return new SimpleWordList();
	}

}
