package parser.nodes.stmt;

import parser.nodes.JottTree;
import utils.Token;

import java.util.ArrayList;

public class Body implements JottTree {

    private Body_Stmt body_Stmt;
    private Body body;
    private Return_Stmt return_Stmt;

    public static Body createBody(ArrayList<Token> tokens) {
        var body = new Body();

        if(tokens.get(0).getToken().equals("return")){
            body.return_Stmt = Return_Stmt.createReturn_Stmt(tokens);
        }else {
            body.body_Stmt = Body_Stmt.createBody_Stmt(tokens);
            body.body = Body.createBody(tokens);
        }
        return body;
        
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
