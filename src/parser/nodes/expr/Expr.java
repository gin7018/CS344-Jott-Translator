package parser.nodes.expr;

import parser.SymbolTable;
import parser.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.function.Function_Call;
import parser.nodes.primitive.Constant;
import parser.nodes.primitive.Id;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

public class Expr implements JottTree {
    JottTree lnode;
    Token operator;
    JottTree rnode;
    Boolean isTail;


    private Expr(){
        lnode = null;
        rnode = null;
        operator = null;
        isTail=false;
    }

    public static Expr createExpr(ArrayList<Token> tokens){
        Expr expr = new Expr();
        if(tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){
           if(tokens.get(1).getTokenType()==TokenType.L_BRACKET){
            expr.lnode = Function_Call.createFunction_Call(tokens);
           }
           else if(Character.isUpperCase(tokens.get(0).getToken().charAt(0))){
            expr.lnode = Constant.CreateConstant(tokens);           }
           else{
            expr.lnode = Id.CreateId(tokens);
           }
        }
        else if(tokens.get(0).getTokenType() == TokenType.NUMBER){
            expr.lnode = Constant.CreateConstant(tokens);
        }
        else if(tokens.get(0).getTokenType()==TokenType.STRING){
            expr.lnode = Constant.CreateConstant(tokens);        }
        else{
            throw new SyntaxException("Expected a Number string or Id For expr but got " + tokens.get(0).getToken(), tokens.get(0));
        }
        if(tokens.get(0).getTokenType()==TokenType.MATH_OP||tokens.get(0).getTokenType()==TokenType.REL_OP){
            if(!(tokens.get(1).getTokenType()==TokenType.ID_KEYWORD||
            tokens.get(1).getTokenType()==TokenType.NUMBER||
            tokens.get(1).getTokenType()==TokenType.STRING)){
                throw new SyntaxException("Expected a ID keyword string or number to follow op but got " + tokens.get(1).toString(), tokens.get(1));
            }
            expr.operator=tokens.remove(0);
            expr.rnode =Expr.createExpr(tokens);
        }
        else{
            expr.isTail=true;
        }


        return expr;
    }

    public PType getExprType() {
        return null;
    }

    @Override
    public String convertToJott() {
        if (isTail){
            return lnode.convertToJott();
        }
        else{
            return lnode.convertToJott() + operator.getToken() + rnode.convertToJott();
        }
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
    public boolean validateTree(SymbolTable table) {
        // TODO Auto-generated method stub
        return false;
    }

}
