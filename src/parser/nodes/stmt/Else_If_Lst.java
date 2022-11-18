package parser.nodes.stmt;

import parser.SymbolTable;
import parser.nodes.JottTree;
import parser.nodes.expr.Expr;
import parser.nodes.function.Function_Def;
import parser.nodes.primitive.Constant;
import parser.nodes.primitive.PType;
import utils.StringUtility;
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
        if (!tokens.get(0).getToken().equals("elseif")) {
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

    public boolean isEpsilon() {
        return isEpsilon;
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
        if (isEpsilon) {
            return "";
        }

        return String.format("else if (%s) {%s} %s", expr.convertToJava(), body.convertToJava(), trailingElseIf.convertToJava());
    }

    @Override
    public String convertToC() {
        if (isEpsilon) {
            return "";
        }

        return String.format("else if (%s) {%s} %s", expr.convertToC(), body.convertToC(), trailingElseIf.convertToC());
    }

    @Override
    public String convertToPython() {
        if (isEpsilon) {
            return "";
        }

        return StringUtility.removeBlankLines(String.format("""
                elif %s:
                %s
                %s
                """, expr.convertToPython(), body.convertToPython().indent(4), trailingElseIf.convertToPython()));
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def function) {
        if (isEpsilon) {
            return;
        }

        expr.validateTree(table, function);
        body.validateTree(table, function);
        trailingElseIf.validateTree(table, function);
    }

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasReturn() {
        if (isEpsilon) {
            return false;
        }

        if (!body.hasReturn()) {
            return false;
        }

        if (!trailingElseIf.isEpsilon()) {
            return trailingElseIf.hasReturn();
        }

        return true;
    }
}
