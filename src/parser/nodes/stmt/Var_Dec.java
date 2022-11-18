package parser.nodes.stmt;

import parser.exceptions.ContextUnawareSyntaxException;
import parser.SymbolTable;
import parser.exceptions.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.function.Function_Def;
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

    public Id getId() {
        return id;
    }

    public PType getType() {
        return type;
    }

    @Override
    public String convertToJott() {
        return this.type.getLabel()+" "+this.id.convertToJott();
    }

    @Override
    public String convertToJava() {
        return this.type.getLabel()+" "+this.id.convertToJava();
    }

    @Override
    public String convertToC() {
        return this.type.getLabel()+" "+this.id.convertToC();
    }

    @Override
    public String convertToPython() {
        return this.type.getLabel()+" "+this.id.convertToPython();
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def function) {}

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return null;
    }
}
