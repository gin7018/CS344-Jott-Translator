package parser.nodes.stmt;

import parser.nodes.JottTree;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

public class Body implements JottTree {

    private Body_Stmt body_Stmt;
    private Body body;
    private Return_Stmt return_Stmt;
    private Token token;
    private Body(Return_Stmt return_Stmt, Token token) {
        this.return_Stmt = return_Stmt;
        this.body_Stmt = null;
        this.body = null;
        this.token = token;
    }
    private Body(Body_Stmt body_Stmt, Body body, Token token){
        this.return_Stmt = null;
        this.body_Stmt = body_Stmt;
        this.body = body;
        this.token= token;

    }

    public static Body createBody(ArrayList<Token> tokens) {
        if(tokens.get(0).getToken().equals("return")){
            
            return new Body(Return_Stmt.createReturn_Stmt(tokens),tokens.remove(0));
        }else if(tokens.get(0).getToken().equals("while")||tokens.get(0).getToken().equals("if")
                ||tokens.get(0).getTokenType() == TokenType.ID_KEYWORD ){
                    return new Body(Body_Stmt.createBody_Stmt(tokens), Body.createBody(tokens),tokens.remove(0));
            
        }else return null;
        
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
