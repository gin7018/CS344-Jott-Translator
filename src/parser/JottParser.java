package parser;

import parser.exceptions.ContextUnawareSyntaxException;
import parser.exceptions.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.Program;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

/**
 * This class is responsible for paring Jott Tokens
 * into a Jott parse tree.
 *
 * @author
 */
public class JottParser {

    /**
     * Parses an ArrayList of Jotton tokens into a Jott Parse Tree.
     *
     * @param tokens the ArrayList of Jott tokens to parse
     * @return the root of the Jott Parse Tree represented by the tokens.
     * or null upon an error in parsing.
     */
    public static Program parse(ArrayList<Token> tokens) {
        if (tokens.size() == 0) {
            return null;
        }
        var lastToken = tokens.get(tokens.size() - 1);

        try {
            Token eof = new Token("EOF", lastToken.getFilename(), lastToken.getLineNum(), TokenType.EOF);
            tokens.add(eof);
            tokens.add(eof);
            tokens.add(eof);
            return Program.createProgram(tokens);
        } catch (ContextUnawareSyntaxException e) {
            e.report(lastToken.getFilename(), lastToken.getLineNum());
        } catch (SyntaxException e) {
            e.printStackTrace();
            e.report();
        }

        return null;
    }
}
