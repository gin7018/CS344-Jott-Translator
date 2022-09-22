package parser.nodes.stmt;

import parser.nodes.JottTree;
import utils.Token;

import java.util.ArrayList;

public class Asmt implements JottTree {

    private Asmt() {

    }

    public static Asmt createAsmt(ArrayList<Token> tokens) {
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
