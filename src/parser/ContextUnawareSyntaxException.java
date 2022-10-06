package parser;

/**
 * A syntax exception that, when thrown, has no knowledge of the file or line number until reported.
 */
public class ContextUnawareSyntaxException extends RuntimeException {
    public ContextUnawareSyntaxException(String message) {
        super(message);
    }

    /**
     * Reports the exception as a syntax error with the given file name and line number.
     *
     * @param fileName   The name of the file the error was found on
     * @param lineNumber The 1-indexed line number
     */
    public void report(String fileName, int lineNumber) {
        System.err.printf("Syntax Error:\n%s\n%s:%d\n", getMessage(), fileName, lineNumber);
    }
}
