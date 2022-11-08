package parser.nodes.stmt;

import parser.Symbol;
import parser.SymbolTable;
import parser.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.function.Function_Call;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

import static parser.nodes.NodeUtility.popAndExpect;

public class Stmt implements JottTree{

    private JottTree asmt;
    private JottTree varDec;
    private JottTree funtionCall;

    private Stmt() {

    }

    public static Stmt createStmt(ArrayList<Token> tokens, SymbolTable table) {
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

            // add declaration to sym table
            Var_Dec vd = (Var_Dec) stmt.varDec;
            Symbol sym = new Symbol(vd.getId().getToken().getToken(), vd.getType().label, null);
            table.insert(sym);
        }
        else if(t1.getTokenType()== TokenType.ASSIGN || t2.getTokenType() == TokenType.ASSIGN){

            stmt.asmt= Asmt.createAsmt(tokens, table);

        }
        else {
            throw new SyntaxException("Unexpected token or end of file in stmt", t0);

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
    public boolean validateTree(SymbolTable table) {
        return false;
    }

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return null;
    }
}
