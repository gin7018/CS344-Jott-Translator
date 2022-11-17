package parser.nodes.stmt;

import parser.SymbolTable;
import parser.exceptions.SemanticException;
import parser.exceptions.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.expr.Expr;
import parser.nodes.function.Function_Call;
import parser.nodes.function.Function_Def;
import parser.nodes.primitive.Constant;
import parser.nodes.primitive.Id;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

public class Return_Stmt implements JottTree{
    private JottTree expr;
    private Return_Stmt() {
        expr = null;

    }

    public static Return_Stmt createReturn_Stmt(ArrayList<Token> tokens) {
        var firstToken = tokens.remove(0);
        if(!firstToken.getToken().equals("return")){
            throw new SyntaxException("Expected return statement how though", firstToken);
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
        var tempToken = tokens.remove(0);
        if(!(tempToken.getTokenType()==TokenType.SEMICOLON)){
            throw new SyntaxException("Expected semicolon", tempToken);
        }
        return rStmt;

    }


    @Override
    public PType getPrimitiveType() {
        return expr.getPrimitiveType();
    }

    @Override
    public String convertToJott() {
        String out ="return ";
        if(expr != null){
            out+=expr.convertToJott();
        }
        out+=";";
        return out;

    }

    @Override
    public String convertToJava() {
        String out ="return ";
        if(expr != null){
            out+=expr.convertToJott();
        }
        out+=";";
        return out;
    }

    @Override
    public String convertToC() {
        String out ="return ";
        if(expr != null){
            out+=expr.convertToJott();
        }
        out+=";";
        return out;
    }

    @Override
    public String convertToPython() {
        String out ="return ";
        if(expr != null){
            out+=expr.convertToJott();
        }
        return out;
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def function) {
        // TODO: Shouldn't this be in the create method? When would this be null?
        if (expr == null) {
            throw new SemanticException("Expression is null", null);
        }

        expr.validateTree(table, function);

        if (expr.getPrimitiveType() != function.getReturnType()) {
            // Mismatched return types
            throw new SemanticException("Mismatched return types", null);
        }
    }


}
