package parser.nodes.stmt;

import parser.SymbolTable;
import parser.exceptions.SyntaxException;
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

public class While_Loop implements JottTree{

    private Expr expr;
    private Body body;

    private While_Loop() {

    }

    public static While_Loop createWhile_Loop(ArrayList<Token> tokens, SymbolTable table) {
        var constant = Constant.CreateConstant(tokens);
        if (constant.getType() != PType.STRING || !constant.getContents().equals("while")) {
            throw new SyntaxException("Unexpected token " + constant, constant.getToken());
        }

        var whileLoop = new While_Loop();
        popAndExpect(tokens, TokenType.L_BRACKET);
        whileLoop.expr = Expr.createExpr(tokens);
        popAndExpect(tokens, TokenType.R_BRACKET);
        popAndExpect(tokens, TokenType.L_BRACE);
        whileLoop.body = Body.createBody(tokens, table);
        popAndExpect(tokens, TokenType.R_BRACE);

        return whileLoop;
    }

    @Override
    public String convertToJott() {
        return String.format("while[%s] {%s}", expr.convertToJott(), body.convertToJott());
    }

    @Override
    public String convertToJava() {
        return String.format("while (%s) {%s}", expr.convertToJava(), body.convertToJava());
    }

    @Override
    public String convertToC() {
        return String.format("while (%s) {%s}", expr.convertToC(), body.convertToC());
    }

    @Override
    public String convertToPython() {
        return StringUtility.removeBlankLines(String.format("while %s:\n%s", expr.convertToPython(), body.convertToPython().indent(4)));
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def function) {
        expr.validateTree(table, function);
        body.validateTree(table, function);
    }

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return null;
    }
}
