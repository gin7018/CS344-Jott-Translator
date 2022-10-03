package parser.nodes.stmt;

import parser.nodes.JottTree;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class Body_Stmt implements JottTree {
    
    private If_Stmt if_Stmt;
    private While_Loop while_Loop;
    private Stmt stmt;
    
    private Body_Stmt(If_Stmt if_Stmt) {
        this.stmt = null;
        this.while_Loop =null;
        this.if_Stmt= if_Stmt;
    }
    private Body_Stmt(While_Loop while_Loop) {
        this.stmt = null;
        this.while_Loop = while_Loop;
        this.if_Stmt= null;
    }
    private Body_Stmt(Stmt stmt) {
        this.stmt = stmt;
        this.while_Loop =null;
        this.if_Stmt= null;
    }

    public static Body_Stmt createBody_Stmt(ArrayList<Token> tokens) {

        if(tokens.get(0).getToken().equals("if")){   
            tokens.remove(0);
            return new Body_Stmt(If_Stmt.createIf_Stmt(tokens));
        }else if(tokens.get(0).getToken().equals("while")){
            tokens.remove(0);
            return new Body_Stmt(While_Loop.createWhile_Loop(tokens));
        } else if(tokens.get(0).getTokenType() == TokenType.STRING){
            return new Body_Stmt(Stmt.createStmt(tokens));
        }else throw new RuntimeErrorException(new Error(), "something terrible ");

        
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
