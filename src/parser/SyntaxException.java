package parser;

import utils.Token;

/**
 * A syntax exception based off of a given {@link Token}.
 */
public class SyntaxException extends RuntimeException {
    private final Token token;

    public SyntaxException(String message, Token token) {
        super(message);
        this.token = token;
    }

    /**
     * Reports the exception as a syntax error with the file name and line number provided by the
     * {@link SyntaxException#SyntaxException(String, Token)}
     */
    public void report() {
        System.err.printf("Syntax Error:\n%s\n%s:%d\n", getMessage(), token.getFilename(), token.getLineNum());
    }
}
