package parser.nodes.stmt;

import parser.nodes.JottTree;
import parser.nodes.expr.B_Expr;
import utils.Token;

import java.util.ArrayList;

public class End_Stmt implements JottTree {

    private End_Stmt() {

    }

    public static End_Stmt createEnd_Stmt(ArrayList<Token> tokens) {
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
