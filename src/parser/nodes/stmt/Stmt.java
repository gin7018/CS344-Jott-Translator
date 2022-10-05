package parser.nodes.stmt;

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

    public static Stmt createStmt(ArrayList<Token> tokens) {
        var stmt = new Stmt();
        var tok = tokens.get(0);
        if (PType.getPrimitiveType(tok) != null) { // it can either be varDec or Asmt
            int lookAheadIndex = 2; // look 2 indexes ahead to see which node to create
            if (lookAheadIndex < tokens.size() &&
                    tokens.get(lookAheadIndex).getTokenType().equals(TokenType.ASSIGN)) {
                stmt.asmt = Asmt.createAsmt(tokens);
            }
            else if (lookAheadIndex < tokens.size() &&
                    tokens.get(lookAheadIndex).getTokenType().equals(TokenType.SEMICOLON)) {
                stmt.varDec = Var_Dec.createVar_Dec(tokens);
            }
            else {
                throw new RuntimeException("Unexpected token or end of file");
            }

        }
        else { // this means it can only be a function call now
            stmt.funtionCall = Function_Call.createFunction_Call(tokens);
            popAndExpect(tokens, TokenType.SEMICOLON);
        }
        return stmt;
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
