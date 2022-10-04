package parser.nodes.stmt;

import parser.nodes.JottTree;
import parser.nodes.expr.Expr;
import parser.nodes.primitive.Constant;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

import static parser.nodes.NodeUtility.popAndExpect;

public class While_Loop implements JottTree{

    private Expr expr;
    private Body body;

    private While_Loop() {

    }

    public static While_Loop createWhile_Loop(ArrayList<Token> tokens) {
        var constant = Constant.CreateConstant(tokens);
        if (constant.getType() != PType.STRING || !constant.getContents().equals("while")) {
            throw new RuntimeException("Unexpected token " + constant);
        }

        var whileLoop = new While_Loop();
        popAndExpect(tokens, TokenType.L_BRACKET);
        whileLoop.expr = Expr.createExpr(tokens);
        popAndExpect(tokens, TokenType.R_BRACKET);
        popAndExpect(tokens, TokenType.L_BRACE);
        whileLoop.body = Body.createBody(tokens);
        popAndExpect(tokens, TokenType.R_BRACE);

        return whileLoop;
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
