package ie.atu.sw;

import java.util.TreeSet;
import java.util.Set;

public final class TextWordSetLoader {

	public Set<String> load(String fileName) throws Exception {
		var lines = FileUtility.readFileToArray(fileName);
		Set<String> words = new TreeSet<>(lines);

		return words;
	}
}
