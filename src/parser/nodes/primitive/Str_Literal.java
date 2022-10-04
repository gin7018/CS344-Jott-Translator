package parser.nodes.primitive;

import parser.nodes.JottTree;
import utils.Token;

import java.util.ArrayList;

public class Str_Literal implements JottTree{

    private Str_Literal() {

    }

    public static Str_Literal createStr_Literal(ArrayList<Token> tokens) {
        return null;
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
