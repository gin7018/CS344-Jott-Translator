package parser;

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
     * @param tokens the ArrayList of Jott tokens to parse
     * @return the root of the Jott Parse Tree represented by the tokens.
     *         or null upon an error in parsing.
     */
    public static JottTree parse(ArrayList<Token> tokens){
      if (tokens.size()==0){
        return null;
      }
      try{
      Token eof = new Token("EOF",tokens.get(tokens.size()-1).getFilename(), tokens.get(tokens.size()-1).getLineNum(), TokenType.EOF);
      tokens.add(eof);
      tokens.add(eof);
      tokens.add(eof);
		  return Program.createProgram(tokens);
      }
      catch(RuntimeException e){
        System.out.println(e.getMessage());
        return null;

      }
    }
}
