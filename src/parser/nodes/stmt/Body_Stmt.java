package parser.nodes.stmt;

import parser.nodes.JottTree;
import parser.nodes.expr.B_Expr;
import utils.Token;

import java.util.ArrayList;

public class Body_Stmt implements JottTree {

    private Body_Stmt() {

    }

    public static Body_Stmt createBody_Stmt(ArrayList<Token> tokens) {
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
