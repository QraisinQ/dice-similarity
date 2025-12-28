package ie.atu.sw;

import java.util.ArrayList;
import java.util.List;

public final class WordTokenizer {

    private String normalize(String text) {
        return text.toLowerCase();
    }

    private String removePunctuation(String text) {
        return text.replaceAll("[\\p{Punct}&&[^-]]|(?<![а-яёА-ЯЁa-zA-Z])-|-(?![а-яёА-ЯЁa-zA-Z])", "");
    }

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
