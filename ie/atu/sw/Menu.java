package ie.atu.sw;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Menu {
    private Scanner scanner;
    private String fileAPath = "./1.txt"; // Default input file
    private String fileBPath = "./2.txt"; // Default input file
    private String filterFilePath = "./google.txt";
    private boolean isFilteringMode = false;
    private Set<String> fileA = null; // Stores words
    private Set<String> fileB = null; // Stores words
    private Set<String> filter = null; // Stores filter
    private TextWordSetLoader textLoader;

    /**
     * Clears the console screen using ANSI escape codes.
     * Works in most terminals that support ANSI sequences.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Constructor.
     * Initializes the scanner for user input.
     */
    public Menu() {
        scanner = new Scanner(System.in);
        textLoader = new TextWordSetLoader();
    }

    /**
     * Displays the main menu in a loop and processes user selections.
     * User can configure file paths, toggle modes, start encoding/decoding, or
     * exit.
     */
    public void showMainMenu() {

        while (true) {
            Menu.clearScreen();
            System.out.println(ConsoleColour.YELLOW);
            System.out.println("************************************************************");
            System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
            System.out.println("*                                                          *");
            System.out.println("*                 Sorrensen-Dice compare                   *");
            System.out.println("*                                                          *");
            System.out.println("************************************************************");
            System.out.println("\n=== Text Compare Application ===");
            System.out.println("1) Specify File A (current: " + fileAPath + ")");
            System.out.println("2) Specify File B (current: " + fileBPath + ")");
            System.out.println("3) Specify Filter File (current: " + filterFilePath + ")");
            System.out.println("4) Switch to " + (isFilteringMode ? "Filering" : "No Filtering") + " Mode");
            System.out.println("5) Compare ");
            System.out.println("6) Show State ");
            System.out.println("7) Quit");

            System.out.print(ConsoleColour.BLUE_BOLD);
            System.out.print("Select option [1-6]: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    loadFileA();

                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case "2":
                    loadFileB();

                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case "3":
                    loadFileFilter();

                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case "4":
                    Menu.clearScreen();

                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case "5":
                    Menu.clearScreen();

                    System.out.println();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case "6":
                    return;
                case "7":
                    scanner.close();
                    Menu.clearScreen();
                    System.out.println("Exiting application. Goodbye!");

                    return;
                default:
                    System.out.println("Invalid option. Please select a number from 1 to 6.");
                    break;
            }
        }
    }

    private void loadFileA() {
        Menu.clearScreen();
        System.out.print("Enter path to input text file A or press Enter for default value: ");
        String path = scanner.nextLine();

        if (!path.isBlank()) {
            fileAPath = path;
        }

        try {
            fileA = textLoader.load(fileAPath);

            System.out.println("Text A, tokens was loaded: " + fileA.size());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            fileAPath = "";
            fileA = null;
        }
    }

    private void loadFileB() {
        Menu.clearScreen();
        System.out.print("Enter path to input text file B or press Enter for default value: ");
        String path = scanner.nextLine();

        if (!path.isBlank()) {
            fileBPath = path;
        }

        try {
            fileB = textLoader.load(fileBPath);

            System.out.println("Text B, tokens was loaded: " + fileB.size());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            fileBPath = "";
            fileB = null;
        }
    }

    private void loadFileFilter() {
        Menu.clearScreen();
        System.out.print("Enter path to filter text file or press Enter for default value: ");
        String path = scanner.nextLine();

        if (!path.isBlank()) {
            filterFilePath = path;
        }

        try {
            filter = new TreeSet<String>(FileUtility.readFileToList(filterFilePath));

            System.out.println("Filter, words was loaded: " + filter.size());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            filterFilePath = "";
            filter = null;
        }
    }
}
