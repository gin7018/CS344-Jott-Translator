package parser.nodes.primitive;

import parser.nodes.JottTree;
import utils.Token;

import java.util.ArrayList;

public class Int implements JottTree{

    private Int() {

    }

    public static Int createInt(ArrayList<Token> tokens) {
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
