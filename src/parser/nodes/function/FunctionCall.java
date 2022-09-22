package parser.nodes.function;

import parser.nodes.JottTree;

import java.util.ArrayList;

import parser.nodes.MathExpr;
import parser.nodes.primitive.Id;
import utils.Token;
import utils.TokenType;

public class FunctionCall implements JottTree {
    Id id;
    ArrayList<JottTree> param;

    public FunctionCall() {

    }

    public static FunctionCall createFunctionCall(ArrayList<Token> tokens) {
        FunctionCall fCall = new FunctionCall();
        fCall.id = Id.CreateId(tokens);
        if (!(tokens.remove(0).getTokenType() == TokenType.L_BRACKET)) {
            throw new RuntimeException("how did this happen?????");
        }
        if (tokens.get(0).getTokenType() == TokenType.R_BRACKET) {
            tokens.remove(0);
            return fCall;
        }
        while(true) {
            switch (tokens.get(0).getTokenType()) {
                case STRING:
                    // param.add createStrexp(tokens)
                    break;
                case NUMBER:
                    fCall.param.add(Expr.createExpr(tokens));
                    break;
                case ID_KEYWORD:
                    if (tokens.get(1).getTokenType() == TokenType.MATH_OP||
                    tokens.get(1).getTokenType() == TokenType.REL_OP) {
                        fCall.param.add(Expr.createExpr(tokens));
                    } else if (tokens.get(1).getTokenType() == TokenType.L_BRACE) {
                        fCall.param.add(FunctionCall.createFunctionCall(tokens));
                    } else {
                        fCall.param.add(Id.CreateId(tokens));
                    }
                    break;
            }
            Token tempToken = tokens.remove(0);
            if(tempToken.getTokenType()==TokenType.COMMA) continue;
            else if(tempToken.getTokenType()==TokenType.R_BRACKET) break;
            else {
                throw new RuntimeException("expected comma or right bracket got " +tempToken.getToken()
                +" on line "+tempToken.getLineNum());
        }
        } 
        return fCall;
    }

    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String convertToJava() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String convertToC() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String convertToPython() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validateTree() {
        // TODO Auto-generated method stub
        return false;
    }

}
