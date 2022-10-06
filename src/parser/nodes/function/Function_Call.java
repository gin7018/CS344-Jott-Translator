package parser.nodes.function;

import parser.nodes.JottTree;

import java.util.ArrayList;

import parser.nodes.expr.Expr;
import parser.nodes.primitive.Constant;
import parser.nodes.primitive.Id;
import parser.nodes.stmt.Else;
import utils.Token;
import utils.TokenType;

public class Function_Call implements JottTree {
    Id id;
    ArrayList<JottTree> param;

    private Function_Call() {

    }

    public static Function_Call createFunction_Call(ArrayList<Token> tokens) {
        Function_Call fCall = new Function_Call();
        fCall.param = new ArrayList<JottTree>();
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
                    fCall.param.add(Constant.CreateConstant(tokens));
                    break;
                case NUMBER:
                    fCall.param.add(Expr.createExpr(tokens));
                    break;
                case ID_KEYWORD:
                    if (tokens.get(1).getTokenType() == TokenType.MATH_OP||
                    tokens.get(1).getTokenType() == TokenType.REL_OP) {
                        fCall.param.add(Expr.createExpr(tokens));
                    } else if (tokens.get(1).getTokenType() == TokenType.L_BRACE) {
                        fCall.param.add(Function_Call.createFunction_Call(tokens));
                    } 
                    else if(Character.isUpperCase(tokens.get(0).getToken().charAt(0))){
                        fCall.param.add(Constant.CreateConstant(tokens));
                    }
                    else{
                        fCall.param.add(Id.CreateId(tokens));
                    }

                    break;
                    default:
                    throw new RuntimeException("expected function call param");
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
        String out = id.convertToJott()+"[";
        for(JottTree node: param){
            out = out + node.convertToJott()+",";
        }
        char[] temp = out.toCharArray();
        temp[temp.length-1] = ']';
        return String.valueOf(temp);
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
