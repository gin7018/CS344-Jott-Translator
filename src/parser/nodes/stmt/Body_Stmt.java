package parser.nodes.stmt;

import parser.nodes.JottTree;
import utils.Token;

import java.util.ArrayList;

public class Body_Stmt implements JottTree {
    
    private If_Stmt if_Stmt;
    private While_Loop while_Loop;
    private Stmt stmt;
    
//    private Body_Stmt(If_Stmt if_Stmt) {
//        this.stmt = null;
//        this.while_Loop =null;
//        this.if_Stmt= if_Stmt;
//    }
//    private Body_Stmt(While_Loop while_Loop) {
//        this.stmt = null;
//        this.while_Loop = while_Loop;
//        this.if_Stmt= null;
//    }
//    private Body_Stmt(Stmt stmt) {
//        this.stmt = stmt;
//        this.while_Loop =null;
//        this.if_Stmt= null;
//    }

    public static Body_Stmt createBody_Stmt(ArrayList<Token> tokens) {
        var bodyStmt = new Body_Stmt();
        Token tok = tokens.remove(0);
        switch (tok.getToken()) {
            case "if" -> bodyStmt.if_Stmt = If_Stmt.createIf_Stmt(tokens);
            case "while" -> bodyStmt.while_Loop = While_Loop.createWhile_Loop(tokens);
            default -> bodyStmt.stmt = Stmt.createStmt(tokens);
        }
        return bodyStmt;
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
