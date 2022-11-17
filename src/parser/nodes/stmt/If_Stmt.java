package parser.nodes.stmt;

import parser.SymbolTable;
import parser.exceptions.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.expr.Expr;
import parser.nodes.function.Function_Def;
import parser.nodes.primitive.Constant;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

import static parser.nodes.NodeUtility.popAndExpect;

public class If_Stmt implements JottTree{

    private Expr expr;
    private Body body;
    private Else_If_Lst elseIfLst;
    private Else singleElse;

    private If_Stmt() {

    }

    public static If_Stmt createIf_Stmt(ArrayList<Token> tokens, SymbolTable table) {
        var constant = Constant.CreateConstant(tokens);
        if (constant.getType() != PType.STRING || !constant.getContents().equals("if")) {
            throw new SyntaxException("Unexpected token " + constant, constant.getToken());
        }

        var ifStatement = new If_Stmt();

        popAndExpect(tokens, TokenType.L_BRACKET);
        ifStatement.expr = Expr.createExpr(tokens);
        popAndExpect(tokens, TokenType.R_BRACKET);
        popAndExpect(tokens, TokenType.L_BRACE);
        ifStatement.body = Body.createBody(tokens, table);
        popAndExpect(tokens, TokenType.R_BRACE);
        ifStatement.elseIfLst = Else_If_Lst.createElse_If_Lst(tokens, table);
        ifStatement.singleElse = Else.createElse(tokens, table);

        return ifStatement;
    }

    @Override
    public String convertToJott() {
        return String.format("if[%s] {%s} %s %s", expr.convertToJott(), body.convertToJott(), elseIfLst.convertToJott(), singleElse.convertToJott());
    }

    @Override
    public String convertToJava() {
        return String.format("if (%s) {%s} %s %s", expr.convertToJava(), body.convertToJava(), elseIfLst.convertToJava(), singleElse.convertToJava());
    }

    @Override
    public String convertToC() {
        return String.format("if (%s) {%s} %s %s", expr.convertToC(), body.convertToC(), elseIfLst.convertToC(), singleElse.convertToC());
    }

    @Override
    public String convertToPython() {
        return String.format("""
            if %s:
            %s
            %s
            %s
            """, expr.convertToPython(), body.convertToPython().indent(4), elseIfLst.convertToPython(), singleElse.convertToPython());
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def function) {
        expr.validateTree(table, function);
        body.validateTree(table, function);
        elseIfLst.validateTree(table, function);
        singleElse.validateTree(table, function);
    }

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasReturn() {
        if (!body.hasReturn()) {
            return false;
        }

        if (singleElse != null) {
            return singleElse.hasReturn();
        }

        if (elseIfLst != null) {
            return elseIfLst.hasReturn();
        }

        return true;
    }
}
