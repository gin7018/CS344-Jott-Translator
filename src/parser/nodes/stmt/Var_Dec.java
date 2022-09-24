package parser.nodes.stmt;

import parser.nodes.JottTree;
import parser.nodes.primitive.Constant;
import parser.nodes.primitive.Id;
import utils.Token;
import parser.nodes.primitive.PType;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;

public class Var_Dec implements JottTree{
    private PType type;
    private Token token;
    private Id id;
    private End_Stmt end_stmt;
    private Var_Dec(PType type, Token token,Id id, End_Stmt end_stmt) {
        this.type = type;
        this.token = token;    
        this.id = id;
        this.end_stmt = end_stmt;
    }

    public static Var_Dec createVar_Dec(ArrayList<Token> tokens) throws Exception {
        if(tokens.isEmpty()){
            throw new Exception("wrong");
        }
        Token tok = tokens.remove(0);
        switch(tok.getToken()){

            case "Integer":
                return new Var_Dec(PType.INT, tok, Id.CreateId(tokens), End_Stmt.createEnd_Stmt(tokens));
                
            case "Double":
                return new Var_Dec(PType.DBL,tok, Id.CreateId(tokens), End_Stmt.createEnd_Stmt(tokens));
                
            case "Boolean":
                return new Var_Dec(PType.BOOL,tok, Id.CreateId(tokens), End_Stmt.createEnd_Stmt(tokens));
               
            case "String":
                return new Var_Dec(PType.STRING,tok, Id.CreateId(tokens), End_Stmt.createEnd_Stmt(tokens));
                
            default:
                throw new Exception("Syntax error at token: "+tok.getToken()+ " at line "+tok.getLineNum()+" invalid variable type");
        }
        
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
