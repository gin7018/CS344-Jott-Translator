package parser.nodes.stmt;

import parser.SymbolTable;
import parser.nodes.JottTree;
import parser.nodes.function.Function_Def;
import parser.nodes.primitive.PType;
import utils.Token;

import java.util.ArrayList;

public class Body_Stmt implements JottTree {

    private If_Stmt if_Stmt;
    private While_Loop while_Loop;
    private Stmt stmt;

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
        if(this.if_Stmt !=null){
            return this.if_Stmt.convertToJava();
        }else if(this.while_Loop != null){
            return this.while_Loop.convertToJava();
        }
        else return this.stmt.convertToJava();
    }

    @Override
    public String convertToC() {
        if(this.if_Stmt !=null){
            return this.if_Stmt.convertToC();
        }else if(this.while_Loop != null){
            return this.while_Loop.convertToC();
        }
        else return this.stmt.convertToC();
    }

    @Override
    public String convertToPython() {
        if(this.if_Stmt !=null){
            return this.if_Stmt.convertToPython();
        }else if(this.while_Loop != null){
            return this.while_Loop.convertToPython();
        }
        else return this.stmt.convertToPython();
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def function) {
        if (if_Stmt != null) {
            if_Stmt.validateTree(table, function);
        } else if (while_Loop != null) {
            while_Loop.validateTree(table, function);
        } else {
            stmt.validateTree(table, function);
        }
    }

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasReturn() {
        if (if_Stmt != null) {
            return if_Stmt.hasReturn();
        } else if (while_Loop != null) {
            return while_Loop.hasReturn();
        } else {
            return stmt.hasReturn();
        }
    }
}
