package ie.atu.sw;

import java.util.Set;

public final class DiceCoefficient {
    public static double score(Set<String> a, Set<String> b) {
        if (a.isEmpty() || b.isEmpty())
            return 0.0;

        int intersection = 0;
        Set<String> small = a.size() <= b.size() ? a : b;
        Set<String> large = small == a ? b : a;

        for (String w : small) {
            if (large.contains(w))
                intersection++;
        }
        return (2.0 * intersection) / (a.size() + b.size());
    }
}
