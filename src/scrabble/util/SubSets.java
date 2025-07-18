package scrabble.util;

import java.util.HashSet;
import java.util.Set;

public class SubSets {

	public static Set<String> getSubSets(String str) {

		Set<String> subsets = new HashSet<>();
		generateSubsets("", str, subsets);
		return subsets;
	}
	private static void generateSubsets(String prefix, String leftPieces, Set<String> subsets) {
		if(leftPieces.isEmpty()) {
			if(!prefix.isEmpty()){
				subsets.add(prefix);
			}
			return;
		}
		generateSubsets(prefix + leftPieces.charAt(0), leftPieces.substring(1), subsets);

		generateSubsets(prefix, leftPieces.substring(1), subsets);
	}

}
