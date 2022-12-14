package parser.nodes.stmt;

import parser.SymbolTable;
import parser.nodes.JottTree;
import parser.nodes.function.Function_Def;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

import static parser.nodes.NodeUtility.popAndExpect;

public class End_Stmt implements JottTree {

    private Token semiColon;

    private End_Stmt() {

    }

    public static End_Stmt createEnd_Stmt(ArrayList<Token> tokens) {
        var endStmt = new End_Stmt();
        endStmt.semiColon = popAndExpect(tokens, TokenType.SEMICOLON);
        return endStmt;
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
    public void validateTree(SymbolTable table, Function_Def function) {}

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return null;
    }
}
