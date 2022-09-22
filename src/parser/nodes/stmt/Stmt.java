package parser.nodes.stmt;

import parser.nodes.JottTree;
import parser.nodes.expr.B_Expr;
import utils.Token;

import java.util.ArrayList;

public class Stmt implements JottTree{

    private Stmt() {

    }

    public static Stmt createStmt(ArrayList<Token> tokens) {
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
