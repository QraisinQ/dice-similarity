package ie.atu.sw;

import java.util.Set;
import java.util.TreeSet;

public class TextComparisonService {
    private Set<String> filterInput(Set<String> inputSet, Set<String> filter) {
        var result = new TreeSet<String>();

        for (var token : inputSet) {
            if (!filter.contains(token))
                result.add(token);
        }

        return result;
    }

    public SimilarityResult compare(Set<String> setA, Set<String> setB, Set<String> filter) {

        if (filter == null) {
            return DiceCoefficient.score(setA, setB);
        }

        var filteredA = filterInput(setA, filter);
        var filteredB = filterInput(setB, filter);

        return DiceCoefficient.score(filteredA, filteredB);
    }

}
