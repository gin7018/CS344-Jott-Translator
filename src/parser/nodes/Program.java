package parser.nodes;

import parser.ContextUnawareSyntaxException;
import parser.SyntaxException;
import parser.nodes.function.Function_List;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

public class Program implements JottTree {

    private Function_List functionList;

    private Program() {}

    public static Program createProgram(ArrayList<Token> tokens) {
        var prg = new Program();
        prg.functionList = Function_List.createFunction_List(tokens);
        tokens.remove(0);
        tokens.remove(0);
        if (tokens.isEmpty() || !tokens.remove(0).getTokenType().equals(TokenType.EOF)){
            throw new ContextUnawareSyntaxException("No end of program flag found: $$");
        }
        return prg;
    }

    @Override
    public String convertToJott() {
        return functionList.convertToJott();
    }

    @Override
    public String convertToJava() {
        return null;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}
