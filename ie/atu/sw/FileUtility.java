package ie.atu.sw;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Utility class for file operations.
 * Reads text files into a list of strings.
 */
public class FileUtility {

    /**
     * Reads all lines from a file.
     *
     * @param fileName path to the file
     * @return list of file lines
     * @throws Exception if the file cannot be read
     */
    public static List<String> readFileToList(String fileName) throws Exception {

        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (Exception e) {
            throw new Exception("Error reading file: " + fileName);
        }
    }
}
