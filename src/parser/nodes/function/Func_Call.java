package parser.nodes.function;

import parser.nodes.JottTree;
import utils.Token;

import java.util.ArrayList;

public class Func_Call implements JottTree{

    private Func_Call() {

    }

    public static Func_Call createFunc_Call(ArrayList<Token> tokens) {
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
