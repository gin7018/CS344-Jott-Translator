package parser.nodes.stmt;

import parser.Symbol;
import parser.SymbolTable;
import parser.exceptions.SemanticException;
import parser.exceptions.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.expr.Expr;
import parser.nodes.function.Function_Def;
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
        if (tempKey.equals("Integer")) {
            asmt.keyword = tokens.remove(0);
            asmt.id = Id.CreateId(tokens);
            Symbol tempSymbol = new Symbol(asmt.id.getName(), PType.INT);
            table.insert(tempSymbol);
        } else if (tempKey.equals("String")) {
            asmt.keyword = tokens.remove(0);
            asmt.id = Id.CreateId(tokens);
            Symbol tempSymbol = new Symbol(asmt.id.getName(), PType.STRING);
            table.insert(tempSymbol);
        } else if (tempKey.equals("Boolean")) {
            asmt.keyword = tokens.remove(0);
            asmt.id = Id.CreateId(tokens);
            Symbol tempSymbol = new Symbol(asmt.id.getName(), PType.BOOL);
            table.insert(tempSymbol);

        } else if (tempKey.equals("Double")) {
            asmt.keyword = tokens.remove(0);
            asmt.id = Id.CreateId(tokens);
            Symbol tempSymbol = new Symbol(asmt.id.getName(), PType.DBL);
            table.insert(tempSymbol);

        } else if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            asmt.id = Id.CreateId(tokens);
            if (table.lookup(asmt.id.getToken().getToken()) == null) {
                throw new SyntaxException("this id has not be created", asmt.id.getToken());
            }
        } else {
            throw new SyntaxException("Expected an Id or Keyowrkd but got" + tokens.get(0).getToken(), tokens.get(0));
        }
        if (!(tokens.remove(0).getTokenType() == TokenType.ASSIGN)) {
            throw new SyntaxException("why am I in a assighnment", tokens.get(0));
        }
        asmt.expr = Expr.createExpr(tokens);
        if (!(tokens.remove(0).getTokenType() == TokenType.SEMICOLON)) {
            throw new SyntaxException("expected a semicoln here" + tokens.get(0).getLineNum(), tokens.get(0));
        }
        return asmt;
    }

    @Override
    public String convertToJott() {
        String out = "";
        if (keyword != null) {
            out += keyword.getToken() + " ";
        }
        out += id.convertToJott() + "=" + expr.convertToJott();
        return out;
    }

    @Override
    public String convertToJava() {
        String out = "";
        if (keyword != null) {
            out += keyword.getToken() + " ";
        }
        out += id.convertToJava() + "=" + expr.convertToJava();
        return out;
    }

    @Override
    public String convertToC() {
        String out = "";
        if (keyword != null) {
            out += keyword.getToken() + " ";
        }
        out += id.convertToC() + "=" + expr.convertToC();
        return out;
    }

    @Override
    public String convertToPython() {
        String out = "\n";
//        if (keyword != null) {
//            out += keyword.getToken() + " ";
//        } python does not have type declarations!
        out += id.convertToPython() + "=" + expr.convertToPython();
        return out;
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def function) {
        this.id.validateTree(table, function);
        this.expr.validateTree(table, function);

        if (this.id.getPrimitiveType() != this.expr.getPrimitiveType()) {
            throw new SemanticException("Assignment types do not match", null);
        }
    }

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return null;
    }
}
