package scrabble.util;


import java.util.Arrays;

public class Permutation {
private final String word;
	public Permutation(String word) {
		this.word=word.toLowerCase();
	}

	@Override
	public int hashCode() {
		// TBD: implement this method
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if(word==obj){return true;}
		Permutation that=(Permutation) obj;
		return this.getNormalized().equals(that.getNormalized());
	}

	@Override
	public String toString() {
		return getWord() + " / " + getNormalized();
	}

	public String getNormalized() {
		char[] s=word.toCharArray();
		Arrays.sort(s);
		return new String(s);
	}

	public String getWord() {
		// TBD: implement this method
		return null;
	}

	public int length() {
		// TBD: implement this method
		return 0;
	}

}
