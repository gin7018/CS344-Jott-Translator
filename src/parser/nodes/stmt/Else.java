package parser.nodes.stmt;

import parser.SymbolTable;
import parser.nodes.JottTree;
import parser.nodes.function.Function_Def;
import parser.nodes.primitive.Constant;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

import static parser.nodes.NodeUtility.popAndExpect;

public class Else implements JottTree {

    private final boolean isEpsilon;
    private final Body body;

    private Else() {
        this.isEpsilon = true;
        this.body = null;
    }

    private Else(Body body) {
        this.isEpsilon = false;
        this.body = body;
    }

    public static Else createElse(ArrayList<Token> tokens, SymbolTable table) {
        var constant = Constant.CreateConstant(tokens);
        if (constant.getType() != PType.STRING || !constant.getContents().equals("else")) {
            return new Else();
        }

        popAndExpect(tokens, TokenType.L_BRACE);
        var body = Body.createBody(tokens, table);
        popAndExpect(tokens, TokenType.R_BRACE);

        return new Else(body);
    }

    @Override
    public String convertToJott() {
        if (isEpsilon) {
            return "";
        }

        return String.format("else { %s }", body.convertToJott());
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
    public boolean validateTree(SymbolTable table, Function_Def function) {
        return isEpsilon || body.validateTree(table, function);
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

        return body.hasReturn();
    }
}
