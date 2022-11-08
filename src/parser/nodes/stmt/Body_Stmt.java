package parser.nodes.stmt;

import parser.SymbolTable;
import parser.nodes.JottTree;
import parser.nodes.primitive.PType;
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

    public static Body_Stmt createBody_Stmt(ArrayList<Token> tokens, SymbolTable table) {
        var bodyStmt = new Body_Stmt();
        Token tok = tokens.get(0);
        switch (tok.getToken()) {
            case "if" -> bodyStmt.if_Stmt = If_Stmt.createIf_Stmt(tokens, table);
            case "while" -> bodyStmt.while_Loop = While_Loop.createWhile_Loop(tokens, table);
            default -> bodyStmt.stmt = Stmt.createStmt(tokens, table);
        }
        return bodyStmt;
    }

    @Override
    public String convertToJott() {
        if(this.if_Stmt !=null){
            return this.if_Stmt.convertToJott();
        }else if(this.while_Loop != null){
            return this.while_Loop.convertToJott();
        }
        else return this.stmt.convertToJott();
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

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return null;
    }
}
