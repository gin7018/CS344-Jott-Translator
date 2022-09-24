package parser.nodes.stmt;

import parser.nodes.JottTree;
import parser.nodes.NodeUtility;
import parser.nodes.expr.Expr;
import parser.nodes.primitive.Constant;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;
import java.util.List;

import static parser.nodes.NodeUtility.popAndExpect;

public class If_Stmt implements JottTree{

    private Expr expr;
    private Body body;
    private Else_If_Lst elseIfLst;
    private Else singleElse;

    private If_Stmt() {

    }

    public static If_Stmt createIf_Stmt(ArrayList<Token> tokens) {
        var constant = Constant.CreateConstant(tokens);
        if (constant.getType() != PType.STRING || !constant.getContents().equals("if")) {
            throw new RuntimeException("Unexpected token " + constant);
        }

        var ifStatement = new If_Stmt();

        popAndExpect(tokens, TokenType.L_BRACKET);
        ifStatement.expr = Expr.createExpr(tokens);
        popAndExpect(tokens, TokenType.R_BRACKET);
        popAndExpect(tokens, TokenType.L_BRACE);
        ifStatement.body = Body.createBody(tokens);
        popAndExpect(tokens, TokenType.R_BRACE);
        ifStatement.elseIfLst = Else_If_Lst.createElse_If_Lst(tokens);
        ifStatement.singleElse = Else.createElse(tokens);

        return ifStatement;
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
