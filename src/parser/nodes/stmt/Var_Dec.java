package parser.nodes.stmt;

import parser.ContextUnawareSyntaxException;
import parser.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.primitive.Id;
import parser.nodes.primitive.PType;
import utils.Token;

import java.util.ArrayList;

public class Var_Dec implements JottTree{
    private PType type;
    private Id id;
    private End_Stmt endStmt;

    private Var_Dec() {
    }

    public static Var_Dec createVar_Dec(ArrayList<Token> tokens) {
        if(tokens.isEmpty()){
            throw new ContextUnawareSyntaxException("wrong");
        }
        var varDec = new Var_Dec();
        Token tok = tokens.remove(0);
        varDec.type = switch (tok.getToken()) {
            case "Integer" -> PType.INT;
            case "Double" -> PType.DBL;
            case "Boolean" -> PType.BOOL;
            case "String" -> PType.STRING;
            default -> throw new SyntaxException("Syntax error at token: " + tok.getToken() + " at line " + tok.getLineNum() + " invalid variable type", tok);
        };
        varDec.id = Id.CreateId(tokens);
        varDec.endStmt = End_Stmt.createEnd_Stmt(tokens);
        return varDec;
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
