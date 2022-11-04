package parser.nodes.stmt;

import parser.SymbolTable;
import parser.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.expr.Expr;
import parser.nodes.primitive.Id;
import parser.nodes.primitive.PType;
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

    public static Asmt createAsmt(ArrayList<Token> tokens, SymbolTable table) {
        String tempKey = tokens.get(0).getToken();
        Asmt asmt = new Asmt();
        if(tempKey.equals("Integer")){
            asmt.keyword = tokens.remove(0);
            asmt.id = Id.CreateId(tokens);
            Symbol  tempSymbol = new Symbol(asmt.id.getToken().getToken(),PType.INT.label,null);
            table.insert(tempSymbol);
        }
        else if(tempKey.equals("String")){
            asmt.keyword = tokens.remove(0);
            asmt.id = Id.CreateId(tokens);
            Symbol  tempSymbol = new Symbol(asmt.id.getToken().getToken(),PType.STRING.label,null);
            table.insert(tempSymbol);
        }
        else if(tempKey.equals("Boolean")){
            asmt.keyword = tokens.remove(0);
            asmt.id = Id.CreateId(tokens);
            Symbol  tempSymbol = new Symbol(asmt.id.getToken().getToken(),PType.BOOL.label,null);
            table.insert(tempSymbol);

        }
        else if(tempKey.equals("Double")){
            asmt.keyword = tokens.remove(0);
            asmt.id = Id.CreateId(tokens);
            Symbol  tempSymbol = new Symbol(asmt.id.getToken().getToken(),PType.DBL.label,null);
            table.insert(tempSymbol);

        }
        else if(tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){
            asmt.id = Id.CreateId(tokens);
        }
        else{
            throw new SyntaxException("Expected an Id or Keyowrkd but got"+tokens.get(0).getToken(), tokens.get(0));
        }
        if(!(tokens.remove(0).getTokenType() == TokenType.ASSIGN)){
            throw new SyntaxException("why am I in a assighnment", tokens.get(0));
        }
        asmt.expr = Expr.createExpr(tokens);
        if(!(tokens.remove(0).getTokenType() == TokenType.SEMICOLON)){
            throw new SyntaxException("expected a semicoln here"+tokens.get(0).getLineNum(), tokens.get(0));
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
    public boolean validateTree(SymbolTable table) {
        if(this.id.validateTree(table) && this.expr.validateTree(table)){
            if(this.id.getPrimitiveType()==this.expr.getPrimitiveType()){
                return true;
            }
        }
        return false;
    }

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return null;
    }
}
