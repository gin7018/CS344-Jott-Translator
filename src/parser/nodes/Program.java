package parser.nodes;

import parser.nodes.function.Function_List;
import utils.Token;

import java.util.ArrayList;

public class Program implements JottTree {

    private Function_List functionList;

    private Program() {}

    public static Program createProgram(ArrayList<Token> tokens) {
        var prg = new Program();
        prg.functionList = Function_List.createFunction_List(tokens);
        if (tokens.isEmpty() || !tokens.remove(0).getToken().equals("$$")){
            throw new RuntimeException("No end of program flag found: $$");
        }
        return prg;
    }

    @Override
    public String convertToJott() {
        return null;
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
