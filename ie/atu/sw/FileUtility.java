package ie.atu.sw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * FileUtility class
 * -----------------
 * Provides helper methods to read and write files as arrays of strings.
 * 
 * This utility is used throughout the project to simplify file I/O.
 */
public class FileUtility {

    /**
     * Reads all lines from a file into a String array.
     *
     * @param fileName Path to the input file.
     * @return List of strings, where each element represents one line of the file.
     * @throws IOException If the file cannot be read.
     */
    public static List<String> readFileToArray(String fileName) throws IOException {

        // Files.readAllLines returns a List<String>
        return Files.readAllLines(Paths.get(fileName));
    }
}