package parser.nodes.stmt;

import parser.nodes.JottTree;
import parser.nodes.expr.Expr;
import parser.nodes.primitive.Id;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

public class Asmt implements JottTree {
    private Token keyword;
    private Id id;
    private Expr expr;
    private Asmt() {
        keyword = null;
    }

    public static Asmt createAsmt(ArrayList<Token> tokens) {
        String tempKey = tokens.get(0).getToken();
        Asmt asmt = new Asmt();       
        if(tempKey.equals("Integer")){
            asmt.keyword = tokens.remove(0);
            asmt.id = Id.CreateId(tokens);
        }
        else if(tempKey.equals("String")){
            asmt.keyword = tokens.remove(0);
            asmt.id = Id.CreateId(tokens);

        }
        else if(tempKey.equals("Boolean")){
            asmt.keyword = tokens.remove(0);
            asmt.id = Id.CreateId(tokens);

        }
        else if(tempKey.equals("Double")){
            asmt.keyword = tokens.remove(0);
            asmt.id = Id.CreateId(tokens);

        }
        else if(tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){
            asmt.id = Id.CreateId(tokens);
        }
        else{
            throw new RuntimeException("Expected an Id or Keyowrkd but got"+tokens.get(0).getToken());
        }
        if(!(tokens.remove(0).getTokenType() == TokenType.ASSIGN)){
            throw new RuntimeException("why am I in a assighnment");
        }
        asmt.expr = Expr.createExpr(tokens);
        if(!(tokens.remove(0).getTokenType() == TokenType.SEMICOLON)){
            throw new RuntimeException("expected a semicoln here"+tokens.get(0).getLineNum());
        }
        return asmt;
    }

    @Override
    public String convertToJott() {
        String out = "";
        if(keyword != null){
            out+=keyword.getToken()+" ";
        }
        out+=id.convertToJott()+"="+expr.convertToJott()+";";
        return out;
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
