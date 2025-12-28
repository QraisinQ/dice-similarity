package ie.atu.sw;

import java.util.TreeSet;
import java.util.Set;

public final class TextWordSetLoader {
	WordTokenizer wt = new WordTokenizer();

	public Set<String> load(String fileName) throws Exception {
		var lines = FileUtility.readFileToList(fileName);
		var result = new TreeSet<String>();

		for (var line : lines) {
			var tokens = wt.tokenize(line);

			for (var token : tokens) {
				result.add(token);
			}
		}

		return result;
	}
}
