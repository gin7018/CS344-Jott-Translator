package parser.nodes.stmt;

import parser.nodes.JottTree;
import parser.nodes.expr.Expr;
import parser.nodes.function.Function_Call;
import parser.nodes.primitive.Constant;
import parser.nodes.primitive.Id;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

public class Return_Stmt implements JottTree{
    private JottTree expr;
    private Return_Stmt() {
        expr = null;

    }

    public static Return_Stmt createReturn_Stmt(ArrayList<Token> tokens) {
        if(!tokens.remove(0).getToken().equals("return")){
            throw new RuntimeException("expected return statment how though");
        }
        Return_Stmt rStmt = new Return_Stmt();
        switch (tokens.get(0).getTokenType()) {
            case STRING:
                rStmt.expr=Constant.CreateConstant(tokens);
                break;
            case NUMBER:
            rStmt.expr=Expr.createExpr(tokens);
                break;
            case ID_KEYWORD:
                if (tokens.get(1).getTokenType() == TokenType.MATH_OP||
                tokens.get(1).getTokenType() == TokenType.REL_OP) {
                    rStmt.expr= Expr.createExpr(tokens);
                } else if (tokens.get(1).getTokenType() == TokenType.L_BRACE) {
                    rStmt.expr = Function_Call.createFunction_Call(tokens);
                } else {
                    rStmt.expr =Id.CreateId(tokens);
                }
                break;
            
        }
        if(!(tokens.remove(0).getTokenType()==TokenType.SEMICOLON)){
            throw new RuntimeException("expected semicolon");
        }
        return rStmt;

    }

    @Override
    public String convertToJott() {
        String out ="return";
        if(expr != null){
            out+=expr.convertToJott();
        }
        out+=";";
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
