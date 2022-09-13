package nodes;

import java.util.ArrayList;

import utils.Token;
import utils.TokenType;

public class DExpr implements JottTree {
    ArrayList<JottTree> nodes;
    ArrayList<Token> tokens;
    Token operator;
    Token Id;
    Token Number1;
    Token Number2;

    public DExpr(){
        nodes = new ArrayList<>();
        this.operator = null;
        this.Id=null;
        this.Number1=null;
        this.Number2=null;
    }

    public static DExpr createDExpr(ArrayList<Token> tokens){
        DExpr dExpr = new DExpr();
        Token t1 = tokens.get(0);
        Token t2 = tokens.get(1);
        if(t1.getTokenType() == TokenType.ID_KEYWORD){
            if(t2.getTokenType() == TokenType.MATH_OP){
                dExpr.operator = tokens.remove(1);
                dExpr.nodes.add(createDExpr(tokens));
                dExpr.nodes.add(createDExpr(tokens));
            }
            else{
                dExpr.Id=tokens.remove(0);

            }
        }
        else if(t1.getTokenType() == TokenType.NUMBER){
            if(!t1.getToken().contains(".")){
                throw new RuntimeException("double expression can only contain double errored on line"+ t1.getLineNum());
            }
            if(t2.getTokenType() == TokenType.MATH_OP){
                if(tokens.get(2).getTokenType() == TokenType.NUMBER){
                    if(!tokens.get(2).getToken().contains(".")){
                        throw new RuntimeException("double expression can only contain double errored on line"+ tokens.get(2).getLineNum());
                    }
                    dExpr.Number1=tokens.remove(0);
                    dExpr.operator=tokens.remove(1);
                    dExpr.Number2=tokens.remove(2);
                }
                else{
                    dExpr.Number1=tokens.remove(0);
                    dExpr.operator=tokens.remove(1);
                    dExpr.nodes.add(createDExpr(tokens));

                }
                
            }
            else{
                dExpr.Number1 = tokens.remove(1);
            }

        
        }


        return dExpr;
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
