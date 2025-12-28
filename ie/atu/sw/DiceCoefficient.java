package ie.atu.sw;

import java.util.Set;

public final class DiceCoefficient {
    public static SimilarityResult score(Set<String> a, Set<String> b) {
        if (a.isEmpty() || b.isEmpty())
            return new SimilarityResult(0, a.size(), b.size(), 0);

        int intersection = 0;
        Set<String> small = a.size() <= b.size() ? a : b;
        Set<String> large = small == a ? b : a;

        for (String w : small) {
            if (large.contains(w))
                intersection++;
        }
        var result = (2.0 * intersection) / (a.size() + b.size());

        return new SimilarityResult(result, a.size(), b.size(), intersection);
    }
}
