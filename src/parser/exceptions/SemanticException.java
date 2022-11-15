package parser.exceptions;

import utils.Token;

/**
 * A syntax exception based off of a given {@link Token}.
 */
public class SemanticException extends RuntimeException {
    private final Token token;

    public SemanticException(String message, Token token) {
        super(message);
        this.token = token;
    }

    /**
     * Reports the exception as a syntax error with the file name and line number provided by the
     * {@link SemanticException#SemanticException(String, Token)}
     */
    public void report() {
        if (token == null) {
            System.err.printf("Semantic Error:\n%s\n%s:%d\n\n", getMessage(), "file.jott", -1);
        } else {
            System.err.printf("Semantic Error:\n%s\n%s:%d\n\n", getMessage(), token.getFilename(), token.getLineNum());
        }
    }
}
