package ie.atu.sw;

import java.util.Set;
import java.util.TreeSet;

/**
 * Compare two sets of words.
 * Can use a filter to remove unwanted words.
 */
public class TextComparisonService {

    /**
     * Removes filtered words from the input set.
     *
     * @param inputSet set of wards
     * @param filter   set of words to remove
     * @return filtered set of words
     */
    private Set<String> filterInput(Set<String> inputSet, Set<String> filter) {
        var result = new TreeSet<String>();

        for (var token : inputSet) {
            if (!filter.contains(token)) {
                result.add(token);
            }
        }

        return result;
    }

    /**
     * Compare two sets of wards using Dice coefficient.
     *
     * @param setA   first set of words
     * @param setB   seccond set of wards
     * @param filter filter set (can be null)
     * @return similarity result
     */
    public SimilarityResult compare(Set<String> setA, Set<String> setB, Set<String> filter) {

        if (filter == null) {
            return DiceCoefficient.score(setA, setB);
        }

        var filteredA = filterInput(setA, filter);
        var filteredB = filterInput(setB, filter);

        return DiceCoefficient.score(filteredA, filteredB);
    }
}
