package ie.atu.sw;

/**
 * Holds the result of a comparison.
 */
public record SimilarityResult(double dice, int sizeA, int sizeB, int intersection) {
}
