package ie.atu.sw;

import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Set;

public final class TextWordSetLoader {
	WordTokenizer wt = new WordTokenizer();

	public Set<String> load(String fileName) throws Exception {
		var lines = FileUtility.readFileToList(fileName);
		var result = new ConcurrentSkipListSet<String>();

		try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
			System.out.println("Start processing file ...");
			System.out.println();

			var counter = 0;

			for (var line : lines) {
				counter += 1;

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
