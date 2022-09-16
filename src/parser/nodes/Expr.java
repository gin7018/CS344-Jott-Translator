package parser.nodes;

import java.util.ArrayList;

import utils.Token;
import utils.TokenType;

public class Expr implements JottTree {
    JottTree lnode;
    Token Operator;
    JottTree rnode;
    Boolean isTail;
    

    public Expr(){
        lnode = null;
        rnode = null;
        Operator = null;
        isTail=false;
    }

    public static Expr createExpr(ArrayList<Token> tokens){
        Expr expr = new Expr();
        if(tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){
           if(tokens.get(1).getTokenType()==TokenType.L_BRACKET){
            expr.lnode = FunctionCall.createFunctionCall(tokens);
           }
           else if(Character.isUpperCase(tokens.get(0).getToken().charAt(0))){ 
            //expr.lone = const.newConst(tokens)
           }
           else{
            expr.lnode = Id.CreateId(tokens);
           }
        }
        else if(tokens.get(0).getTokenType() == TokenType.NUMBER){
            //lnode = Constant.createconstant(token,number)
        }
        else if(tokens.get(0).getTokenType()==TokenType.STRING){
            //lnode = const.creatconst
        }
        else{
            //throw error how did we get here 
        }
        if(tokens.get(0).getTokenType()==TokenType.MATH_OP||tokens.get(0).getTokenType()==TokenType.REL_OP){
            if(!(tokens.get(1).getTokenType()==TokenType.ID_KEYWORD||
            tokens.get(1).getTokenType()==TokenType.NUMBER||
            tokens.get(1).getTokenType()==TokenType.STRING)){
                throw new RuntimeException("expected a ID keyword string or number to follow mathop but got"+tokens.get(1).toString());
            }
            expr.Operator=tokens.remove(0);
            expr.rnode =Expr.createExpr(tokens);
        }
        else{
            expr.isTail=true;
        }


        return expr;
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
