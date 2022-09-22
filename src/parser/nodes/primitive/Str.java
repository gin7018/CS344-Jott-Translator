package parser.nodes.primitive;

import parser.nodes.JottTree;
import parser.nodes.expr.B_Expr;
import utils.Token;

import java.util.ArrayList;

public class Str implements JottTree{

    private Str() {

    }

    public static Str createStr(ArrayList<Token> tokens) {
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
