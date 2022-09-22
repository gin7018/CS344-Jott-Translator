package parser.nodes.stmt;

import parser.nodes.JottTree;
import parser.nodes.expr.B_Expr;
import utils.Token;

import java.util.ArrayList;

public class Return_Stmt implements JottTree{

    private Return_Stmt() {

    }

    public static Return_Stmt createReturn_Stmt(ArrayList<Token> tokens) {
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
