package parser.nodes;

import java.util.ArrayList;

import utils.Token;
import utils.TokenType;

public class MathExpr implements JottTree {
    JottTree lnode;
    Token Operator;
    JottTree rnode;
    Boolean isTail;
    

    public MathExpr(){
        lnode = null;
        rnode = null;
        Operator = null;
        isTail=false;
    }

    public static MathExpr createMathExpr(ArrayList<Token> tokens){
        MathExpr mExpr = new MathExpr();
        if(tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){
           if(tokens.get(1).getTokenType()==TokenType.L_BRACKET){
            mExpr.lnode = FunctionCall.createFunctionCall(tokens);
           }
           else{ 
            mExpr.lnode = Id.CreateId(tokens);
           }
        }
        else if(tokens.get(0).getTokenType() == TokenType.NUMBER){
            //lnode = Constant.createconstant(token,number)
        }
        if(tokens.get(0).getTokenType()==TokenType.MATH_OP){
            if(!(tokens.get(1).getTokenType()==TokenType.ID_KEYWORD||
            tokens.get(1).getTokenType()==TokenType.NUMBER)){
                throw new RuntimeException("expected a ID or Key word to follow mathop but got"+tokens.get(1).toString());
            }
            mExpr.Operator=tokens.remove(0);
            mExpr.rnode = MathExpr.createMathExpr(tokens);
        }
        else{
            mExpr.isTail=true;
        }


        return mExpr;
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
