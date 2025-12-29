package ie.atu.sw;

/**
 * Utility class for console output.
 */
public final class ConsoleUtil {

    private ConsoleUtil() {
    }

    /**
     * Prints a progress bar in the console.
     *
     * @param index current step
     * @param total total number of steps
     */
    public static void printProgress(int index, int total) {
        if (index > total) {
            return;
        }

        int size = 50;
        char done = '█';
        char todo = '░';

        int percent = (100 * index) / total;
        int doneLength = size * percent / 100;

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size; i++) {
            sb.append(i < doneLength ? done : todo);
        }

        sb.append("] ").append(percent).append("%");

        System.out.print("\r" + sb);

        if (index == total) {
            System.out.println();
        }
    }
}
