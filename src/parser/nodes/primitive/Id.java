package parser.nodes.primitive;



import parser.Symbol;
import parser.SymbolTable;
import parser.exceptions.SemanticException;
import parser.exceptions.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.function.Function_Def;
import utils.Token;

import java.util.ArrayList;

public class Id  implements JottTree {
    Token id;
    PType type;

    public Id(){

    }

    public static Id CreateId(ArrayList<Token> tokens){
        Id id = new Id();
        Token tok = tokens.remove(0);

        if( Character.isUpperCase(tok.getToken().charAt(0))){
            throw new SyntaxException("Id can not start with a Uppercase", tok);
        }
        if(tok.getToken().equals("elseif")){
            throw new SyntaxException("elseif withought if", tok);
        }
        if(tok.getToken().equals("else")){
            throw new SyntaxException("else withought if", tok);
        }
        id.id= tok;
        return id;


    }

    public String getName() {
        return id.getToken();
    }

    public Token getToken() {
        return id;
    }

    @Override
    public String convertToJott() {
        return id.getToken();
    }

    @Override
    public String convertToJava() {
        return id.getToken();
    }

    @Override
    public String convertToC() {
        return id.getToken();
    }

    @Override
    public String convertToPython() {
        return id.getToken();
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def function) {
        Symbol temp = table.lookup(this.id.getToken());
        if (temp == null){
            throw new SemanticException("Invalid ID '" + id.getToken() + "'", null);
        }

        this.type = temp.getType();
    }

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return this.type;
    }

}
