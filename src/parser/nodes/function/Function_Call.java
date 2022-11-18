package parser.nodes.function;

import parser.Symbol;
import parser.SymbolTable;
import parser.exceptions.SemanticException;
import parser.exceptions.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.expr.Expr;
import parser.nodes.primitive.Id;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Function_Call implements JottTree {
    Id id;
    ArrayList<Expr> param;
    PType pType;

    SymbolTable st;
    Function_Def context;

    private Function_Call() {

    }

    public static Function_Call createFunction_Call(ArrayList<Token> tokens) {
        Function_Call fCall = new Function_Call();
        fCall.param = new ArrayList<Expr>();
        fCall.id = Id.CreateId(tokens);
        fCall.pType = null;
        var removed = tokens.remove(0);
        if (removed.getTokenType() != TokenType.L_BRACKET) {
            throw new SyntaxException("how did this happen?????", removed);
        }
        if (tokens.get(0).getTokenType() == TokenType.R_BRACKET) {
            tokens.remove(0);
            return fCall;
        }
        while (true) {
            fCall.param.add(Expr.createExpr(tokens));
            Token tempToken = tokens.remove(0);
            if (tempToken.getTokenType() == TokenType.COMMA) continue;
            else if (tempToken.getTokenType() == TokenType.R_BRACKET) break;
            else {
                throw new SyntaxException("Expected comma or right bracket got " + tempToken.getToken()
                        + " on line " + tempToken.getLineNum(), tempToken);
            }
        }
        return fCall;
    }

    @Override
    public String convertToJott() {
        String out = id.convertToJott() + "[";
        for (JottTree node : param) {
            out = out + node.convertToJott() + ",";
        }

        if (out.endsWith(",")) {
            out = out.substring(0, out.length() - 1);
        }
        return out + "]";
    }

    @Override
    public String convertToJava() {
        String out = id.convertToJava() + "(";
        for (JottTree node : param) {
            out = out + node.convertToJava() + ",";
        }

        if (out.endsWith(",")) {
            out = out.substring(0, out.length() - 1);
        }
        return out + ")";
    }

    @Override
    public String convertToC() {
        if (id.convertToC().equals("print")) {
            Expr p = this.param.get(0);
            p.validateTree(st, context);
            return switch (p.getPrimitiveType()) {
                case BOOL -> "\nbool p = " + p.convertToC() + ";\nprintf(\"%d\\n\", p)";
                case INT -> "\nint p = " + p.convertToC() + ";\nprintf(\"%d\\n\", p)";
                case STRING -> "\nchar* p = " + p.convertToC() + ";\nprintf(\"%s\\n\", p)";
                case DBL -> "\nfloat p = " + p.convertToC() + ";\nprintf(\"%f\\n\", p)";
                default -> "\n\nSOMETHING IS HORRIBLY WRONG\n\n";
            };
        } else {

            String out = id.convertToC() + "(";
            for (JottTree node : param) {
                out = out + node.convertToC() + ",";
            }

            if (out.endsWith(",")) {
                out = out.substring(0, out.length() - 1);
            }
            return out + ")";
        }
    }

    @Override
    public String convertToPython() {
        String out = id.convertToPython() + "(";
        for (JottTree node : param) {
            out = out + node.convertToPython() + ",";
        }

        if (out.endsWith(",")) {
            out = out.substring(0, out.length() - 1);
        }
        return out + ")";
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def functionContext) {
        st = table;
        context = functionContext;
        if (table.lookup(id.getName()) != null) {
            Symbol function = table.lookup(id.getName());


            this.pType = function.getType();

            if (function.getAttributeLst().size() != param.size()) {
                throw new SemanticException("Parameter count does not match", null);
            }
            List<FunctionParameters> attributeLst = function.getAttributeLst();
            for (int i = 0; i < attributeLst.size(); i++) {
                FunctionParameters expected = attributeLst.get(i);
                JottTree actual = param.get(i);
                if (actual != null) {
                    actual.validateTree(table, functionContext);
                    if (actual.getPrimitiveType() != expected.getType()) {
                        throw new SemanticException("Expression types do not match", null);
                    }
                }
            }
        }
    }

    @Override
    public PType getPrimitiveType() {
        return this.pType;
    }

}
