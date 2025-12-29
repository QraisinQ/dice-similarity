package ie.atu.sw;

import java.util.ArrayList;
import java.util.List;

/**
 * Split a text line into words.
 * Normalize text and remove punctuation.
 */
public final class WordTokenizer {

    /**
     * Convert text to lower case.
     *
     * @param text input text
     * @return normalized text
     */
    private String normalize(String text) {
        return text.toLowerCase();
    }

    /**
     * Remove punctuation symbols from text.
     *
     * @param text input text
     * @return text without punctuation
     */
    private String removePunctuation(String text) {
        return text.replaceAll(
                "[\\p{Punct}&&[^-]]|(?<![а-яёА-ЯЁa-zA-Z])-|-(?![а-яёА-ЯЁa-zA-Z])",
                "");
    }

    /**
     * Split text into separate words.
     *
     * @param text input text
     * @return list of words
     */
    public List<String> tokenize(String text) {
        var normalized = normalize(text);
        var withoutPunctuation = removePunctuation(normalized);
        var words = withoutPunctuation.trim().split("\\s+");
        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.add(word);
            }
        }

        return result;
    }
}
