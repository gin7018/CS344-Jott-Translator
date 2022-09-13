package parser.nodes;

import java.util.ArrayList;

import utils.Token;
import utils.TokenType;

public class IntExpr implements JottTree {
    ArrayList<JottTree> nodes;
    Token operator;
    Token Id;
    Token Number1;
    Token Number2;

    public IntExpr(){
        this.operator = null;
        this.Id=null;
        this.Number1=null;
        this.Number2=null;
    }

    public static IntExpr createIntExpr(ArrayList<Token> tokens){
        IntExpr iExpr = new IntExpr();
        Token t1 = tokens.get(0);
        Token t2 = tokens.get(1);
        if(t1.getTokenType() == TokenType.ID_KEYWORD){
            if(t2.getTokenType() == TokenType.MATH_OP){
                iExpr.operator = tokens.remove(1);
                iExpr.nodes.add(createIntExpr(tokens));
                iExpr.nodes.add(createIntExpr(tokens));
            }
            else{
                iExpr.Id=tokens.remove(0);

            }
        }
        else if(t1.getTokenType() == TokenType.NUMBER){
            if(t1.getToken().contains(".")){
                throw new RuntimeException("Integer expression can only contain integers errored on line"+ t1.getLineNum());
            }
            if(t2.getTokenType() == TokenType.MATH_OP){
                if(tokens.get(2).getTokenType() == TokenType.NUMBER){
                    if(tokens.get(2).getToken().contains(".")){
                        throw new RuntimeException("Integer expression can only contain integers errored on line"+ tokens.get(2).getLineNum());
                    }
                    iExpr.Number1=tokens.remove(0);
                    iExpr.operator=tokens.remove(1);
                    iExpr.Number2=tokens.remove(2);
                }
                else{
                    iExpr.Number1=tokens.remove(0);
                    iExpr.operator=tokens.remove(1);
                    iExpr.nodes.add(createIntExpr(tokens));

                }
                
            }
            else{
                iExpr.Number1 = tokens.remove(1);
            }

        
        }


        return iExpr;
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
