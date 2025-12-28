package ie.atu.sw;

/**
 * Utility methods for console output.
 */
public final class ConsoleUtil {
    private ConsoleUtil() {
    }

    /**
     * Prints a terminal progress meter (works best in real terminals, not Eclipse
     * console).
     *
     * @param index current step (1..total)
     * @param total total steps
     */
    public static void printProgress(int index, int total) {
        if (index > total)
            return;

        int size = 50; // must be less than console width
        char done = '█';
        char todo = '░';

        int complete = (100 * index) / total;
        int completeLen = size * complete / 100;

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++)
            sb.append((i < completeLen) ? done : todo);
        sb.append("] ").append(complete).append("%");

        System.out.print("\r" + sb);

        // fix: finish line when last step is reached
        if (index == total)
            System.out.println();
    }
}
