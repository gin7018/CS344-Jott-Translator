package parser.nodes;

import utils.Token;
import utils.TokenType;

import java.util.List;


public class NodeUtility {


    /**
     * Pops off the top token, throwing a {@link RuntimeException} if the expected {@link TokenType} doesn't match the
     * actual {@link TokenType}.
     *
     * @param tokens The token list
     * @param tokenType The type of the token
     * @return The token with the given {@link TokenType}
     * @throws RuntimeException If the token types do not match
     */
    public static Token popAndExpect(List<Token> tokens, TokenType tokenType) {
        var token = tokens.remove(0);
        if (token.getTokenType() != tokenType) {
            // TODO: This should move into its own dedicated exception when this branch is merged into master
            System.out.println("ERROR: Expected " + tokenType + " but got " + token);
            return null;
        }

        return token;
    }

}
