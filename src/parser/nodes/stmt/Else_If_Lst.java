package parser.nodes.stmt;

import parser.SymbolTable;
import parser.nodes.JottTree;
import parser.nodes.expr.Expr;
import parser.nodes.primitive.Constant;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

import static parser.nodes.NodeUtility.popAndExpect;

public class Else_If_Lst implements JottTree {

    private final boolean isEpsilon;
    private Expr expr;
    private Body body;
    private Else_If_Lst trailingElseIf;

    private Else_If_Lst(boolean isEpsilon) {
        this.isEpsilon = isEpsilon;
    }

    public static Else_If_Lst createElse_If_Lst(ArrayList<Token> tokens, SymbolTable table) {
        //var constant = Constant.CreateConstant(tokens);
        if ( !tokens.get(0).getToken().equals("elseif")) {
            return new Else_If_Lst(true);
        }
        var constant = Constant.CreateConstant(tokens);
        var elseIfLst = new Else_If_Lst(false);
        popAndExpect(tokens, TokenType.L_BRACKET);
        elseIfLst.expr = Expr.createExpr(tokens);
        popAndExpect(tokens, TokenType.R_BRACKET);
        popAndExpect(tokens, TokenType.L_BRACE);
        elseIfLst.body = Body.createBody(tokens, table);
        popAndExpect(tokens, TokenType.R_BRACE);
        elseIfLst.trailingElseIf = Else_If_Lst.createElse_If_Lst(tokens, table);

        return elseIfLst;
    }

    @Override
    public String convertToJott() {
        if (isEpsilon) {
            return "";
        }

        return String.format("elseif[%s] {%s} %s", expr.convertToJott(), body.convertToJott(), trailingElseIf.convertToJott());
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
    public boolean validateTree(SymbolTable table) {
        return false;
    }

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return null;
    }
}
