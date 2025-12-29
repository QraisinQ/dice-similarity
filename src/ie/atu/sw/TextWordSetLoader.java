package ie.atu.sw;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Set;

/**
 * Load a text file and converts into a set of words.
 * Use virtual threads for faster processing.
 */
public final class TextWordSetLoader {

	/** Tokenizer for splitting text into words. */
	private WordTokenizer wt = new WordTokenizer();

	/**
	 * Load words from a text file.
	 *
	 * @param fileName path to the text file
	 * @return set of unique words
	 * @throws Exception if the file cannot be read
	 */
	public Set<String> load(String fileName) throws Exception {
		var lines = FileUtility.readFileToList(fileName);
		var result = new ConcurrentSkipListSet<String>();

		try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
			System.out.println("Start processing file ...");
			System.out.println();

			var counter = 0;

			for (var line : lines) {
				counter++;

				executor.submit(() -> {
					var tokens = wt.tokenize(line);
					result.addAll(tokens);
				});

				ConsoleUtil.printProgress(counter, lines.size());
			}

			System.out.println();
		}

		return result;
	}
}
