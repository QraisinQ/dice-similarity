package ie.atu.sw;

/**
 * Stores the result of text comparison.
 *
 * @param dice         Dice similarity coefficient
 * @param sizeA        size of the first set
 * @param sizeB        size of the second set
 * @param intersection number of common elements
 */
public record SimilarityResult(double dice, int sizeA, int sizeB, int intersection) {
}
