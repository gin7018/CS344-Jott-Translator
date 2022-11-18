package utils;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StringUtility {

    /**
     * Removes all blank lines from the string.
     *
     * @param input The string to change
     * @return The stripped string
     */
    public static String removeBlankLines(String input) {
        return input.lines().filter(Predicate.not(String::isBlank)).collect(Collectors.joining("\n"));
    }

}
