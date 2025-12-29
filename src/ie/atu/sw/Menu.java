package ie.atu.sw;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Shows the main menu of the application.
 * Handles user input and program actions.
 */
public class Menu {

    /** Scanner for reading user input. */
    private Scanner scanner;

    /** Path to file A. */
    private String fileAPath = "./1.txt";

    /** Path to file B. */
    private String fileBPath = "./2.txt";

    /** Path to filter file. */
    private String filterFilePath = "./google.txt";

    /** True if filtering mode is enabled. */
    private boolean isFilteringMode = false;

    /** Words loaded from file A. */
    private Set<String> fileA = null;

    /** Words loaded from file B. */
    private Set<String> fileB = null;

    /** Filter words. */
    private Set<String> filter = null;

    /** Loads words from text files. */
    private TextWordSetLoader textLoader;

    /** Compares text files. */
    private TextComparisonService textService = new TextComparisonService();

    /**
     * Clears the console screen.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Creates a new menu.
     */
    public Menu() {
        scanner = new Scanner(System.in);
        textLoader = new TextWordSetLoader();
    }

    /**
     * Displays the main menu and processes user input.
     */
    public void showMainMenu() {

        while (true) {
            Menu.clearScreen();
            System.out.println(ConsoleColour.YELLOW);
            System.out.println("************************************************************");
            System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
            System.out.println("*                                                          *");
            System.out.println("*     Comparing Text Documents with Virtual Threads        *");
            System.out.println("*                                                          *");
            System.out.println("************************************************************");
            System.out.println("\n=== Text Compare Application ===");
            System.out.println("1) Specify File A (current: " + fileAPath + ")");
            System.out.println("2) Specify File B (current: " + fileBPath + ")");
            System.out.println("3) Specify Filter File (current: " + filterFilePath + ")");
            System.out.println("4) Switch Filtering Mode (current: " + (isFilteringMode ? "ON" : "OFF") + ")");
            System.out.println("5) Compare");
            System.out.println("6) Show State");
            System.out.println("7) Quit");

            System.out.print(ConsoleColour.BLUE_BOLD);
            System.out.print("Select option [1-7]: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    loadFileA();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;

                case "2":
                    loadFileB();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;

                case "3":
                    loadFileFilter();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;

                case "4":
                    Menu.clearScreen();
                    toggleFilter();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;

                case "5":
                    Menu.clearScreen();
                    compareFiles();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;

                case "6":
                    Menu.clearScreen();
                    showState();
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    break;

                case "7":
                    scanner.close();
                    Menu.clearScreen();
                    System.out.println("Exiting application. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option. Please select a number from 1 to 7.");
            }
        }
    }

    /**
     * Loads words from file A.
     */
    private void loadFileA() {
        Menu.clearScreen();
        System.out.print("Enter path to input text file A or press Enter for default value: ");
        String path = scanner.nextLine();

        if (!path.isBlank()) {
            fileAPath = path;
        }

        try {
            fileA = textLoader.load(fileAPath);
            System.out.println("File A loaded. Words: " + fileA.size());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            fileAPath = "";
            fileA = null;
        }
    }

    /**
     * Loads words from file B.
     */
    private void loadFileB() {
        Menu.clearScreen();
        System.out.print("Enter path to input text file B or press Enter for default value: ");
        String path = scanner.nextLine();

        if (!path.isBlank()) {
            fileBPath = path;
        }

        try {
            fileB = textLoader.load(fileBPath);
            System.out.println("File B loaded. Words: " + fileB.size());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            fileBPath = "";
            fileB = null;
        }
    }

    /**
     * Loads filter words from file.
     */
    private void loadFileFilter() {
        Menu.clearScreen();
        System.out.print("Enter path to filter file or press Enter for default value: ");
        String path = scanner.nextLine();

        if (!path.isBlank()) {
            filterFilePath = path;
        }

        try {
            filter = new TreeSet<>(FileUtility.readFileToList(filterFilePath));

            if (filter.isEmpty()) {
                throw new Exception("Filter file is empty");
            }

            System.out.println("Filter loaded. Words: " + filter.size());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            filterFilePath = "";
            filter = null;
        }
    }

    /**
     * Compares file A and file B.
     */
    void compareFiles() {
        if (fileA == null || fileB == null) {
            System.out.println("Load File A and File B first.");
            return;
        }

        if (isFilteringMode && filter == null) {
            System.out.println("Load filter file or turn filtering OFF.");
            return;
        }

        var result = isFilteringMode
                ? textService.compare(fileA, fileB, filter)
                : textService.compare(fileA, fileB, null);

        System.out.println("Comparison result:");
        System.out.println("Dice coefficient: " + String.format("%.2f", result.dice()));
        System.out.println("Set A size: " + result.sizeA());
        System.out.println("Set B size: " + result.sizeB());
        System.out.println("Intersection size: " + result.intersection());
    }

    /**
     * Turns filtering mode on or off.
     */
    void toggleFilter() {
        isFilteringMode = !isFilteringMode;
        System.out.println("Filtering mode: " + (isFilteringMode ? "ON" : "OFF"));
    }

    /**
     * Shows current system state.
     */
    void showState() {
        System.out.println("System state:");
        System.out.println("Set A: " + (fileA == null ? "Not loaded" : fileA.size()));
        System.out.println("Set B: " + (fileB == null ? "Not loaded" : fileB.size()));
        System.out.println("Filter: " + (filter == null ? "Not loaded" : filter.size()));
        System.out.println("Filtering mode: " + (isFilteringMode ? "ON" : "OFF"));
    }
}
