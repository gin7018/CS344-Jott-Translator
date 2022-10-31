package parser.nodes.stmt;

import parser.SymbolTable;
import parser.nodes.JottTree;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

public class Body implements JottTree {

    private Body_Stmt body_Stmt;
    private Body body;
    private Return_Stmt return_Stmt;

    public static Body createBody(ArrayList<Token> tokens, SymbolTable table) {
        var body = new Body();

        if(tokens.get(0).getToken().equals("return")){
            body.return_Stmt = Return_Stmt.createReturn_Stmt(tokens);
        }
        else if(tokens.get(0).getTokenType()==TokenType.R_BRACE){
            body.return_Stmt=null;
        }
        else {
            body.body_Stmt = Body_Stmt.createBody_Stmt(tokens, table);
            body.body = Body.createBody(tokens, table);
        }
        return body;
        
    }

    @Override
    public String convertToJott() {
        if(this.body_Stmt!= null &&this.body != null){
            return this.body_Stmt.convertToJott() + this.body.convertToJott();
        }else if(this.body_Stmt != null){
            return this.body_Stmt.convertToJott();
        }
        else if(this.return_Stmt != null){
            return this.return_Stmt.convertToJott();
        }
        else return "";
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
    public boolean validateTree(SymbolTable table) {
        return false;
    }
}
