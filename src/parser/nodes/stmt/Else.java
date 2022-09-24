package parser.nodes.stmt;

import parser.nodes.JottTree;
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

    public static Else createElse(ArrayList<Token> tokens) {
        var constant = Constant.CreateConstant(tokens);
        if (constant.getType() != PType.STRING || !constant.getContents().equals("else")) {
            return new Else();
        }

        popAndExpect(tokens, TokenType.L_BRACE);
        var body = Body.createBody(tokens);
        popAndExpect(tokens, TokenType.R_BRACE);

        return new Else(body);
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
