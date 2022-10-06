package parser.nodes.stmt;

import parser.nodes.JottTree;
import parser.nodes.function.Function_Call;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import static parser.nodes.NodeUtility.popAndExpect;

public class Stmt implements JottTree{

    private JottTree asmt;
    private JottTree varDec;
    private JottTree funtionCall;

    private Stmt() {

    }

    public static Stmt createStmt(ArrayList<Token> tokens) {
        var stmt = new Stmt();
        Token t0 = tokens.get(0);
        Token t1 = tokens.get(1);
        Token t2 = tokens.get(2);

        if(t1.getTokenType()==TokenType.L_BRACKET){
            stmt.funtionCall = Function_Call.createFunction_Call(tokens);
            popAndExpect(tokens, TokenType.SEMICOLON);
        }
        else if((PType.getPrimitiveType(t0) != null) &&
        t1.getTokenType()==TokenType.ID_KEYWORD && t2.getTokenType() == TokenType.SEMICOLON){
            stmt.varDec = Var_Dec.createVar_Dec(tokens);
        }
        else if(t1.getTokenType()== TokenType.ASSIGN || t2.getTokenType() == TokenType.ASSIGN){
            
            stmt.asmt= Asmt.createAsmt(tokens);

        }
        else {
            throw new RuntimeException("Unexpected token or end of file in stmt");

        }
        
        return stmt;
    }

    @Override
    public String convertToJott() {
        if(this.asmt !=null){
            return this.asmt.convertToJott();
        }else if(this.varDec != null){
            return this.varDec.convertToJott()+"+";
        }else return this.funtionCall.convertToJott()+";";

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
