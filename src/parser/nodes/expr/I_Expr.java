package parser.nodes.expr;

import parser.nodes.JottTree;
import utils.Token;

import java.util.ArrayList;

public class I_Expr implements JottTree{

    private I_Expr() {

    }

    public static I_Expr createI_Expr(ArrayList<Token> tokens) {
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
