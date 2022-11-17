package parser.nodes.function;

import parser.Symbol;
import parser.SymbolTable;
import parser.exceptions.SemanticException;
import parser.exceptions.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.expr.Expr;
import parser.nodes.primitive.Constant;
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

    private Function_Call() {

    }

    public static Function_Call createFunction_Call(ArrayList<Token> tokens) {
        Function_Call fCall = new Function_Call();
        fCall.param = new ArrayList<Expr>();
        fCall.id = Id.CreateId(tokens);
        fCall.pType = null;
        var removed =tokens.remove(0);
        if (removed.getTokenType() != TokenType.L_BRACKET) {
            throw new SyntaxException("how did this happen?????", removed);
        }
        if (tokens.get(0).getTokenType() == TokenType.R_BRACKET) {
            tokens.remove(0);
            return fCall;
        }
        while(true) {
//            switch (tokens.get(0).getTokenType()) {
//                case STRING:
//                    fCall.param.add(Constant.CreateConstant(tokens));
//                    break;
//                case NUMBER:
//                    fCall.param.add(Expr.createExpr(tokens));
//                    break;
//                case ID_KEYWORD:
//                    if (tokens.get(1).getTokenType() == TokenType.MATH_OP||
//                    tokens.get(1).getTokenType() == TokenType.REL_OP) {
//                        fCall.param.add(Expr.createExpr(tokens));
//                    } else if (tokens.get(1).getTokenType() == TokenType.L_BRACE) {
//                        fCall.param.add(Function_Call.createFunction_Call(tokens));
//                    }
//                    else if(Character.isUpperCase(tokens.get(0).getToken().charAt(0))){
//                        fCall.param.add(Constant.CreateConstant(tokens));
//                    }
//                    else{
//                        fCall.param.add(Id.CreateId(tokens));
//                    }
//
//                    break;
//                    default:
//                    throw new SyntaxException("Expected function call param", tokens.get(0));
//            }
            fCall.param.add(Expr.createExpr(tokens));

            Token tempToken = tokens.remove(0);
            if(tempToken.getTokenType()==TokenType.COMMA) continue;
            else if(tempToken.getTokenType()==TokenType.R_BRACKET) break;
            else {
                throw new SyntaxException("Expected comma or right bracket got " +tempToken.getToken()
                +" on line "+tempToken.getLineNum(), tempToken);
        }
        }
        return fCall;
    }

    @Override
    public String convertToJott() {
        String out = id.convertToJott()+"[";
        for(JottTree node: param){
            out = out + node.convertToJott()+",";
        }

        if (out.endsWith(",")) {
            out = out.substring(0, out.length() - 1);
        }
        return out + ")";
    }

    @Override
    public String convertToJava() {
        String out = id.convertToJava()+"(";
        for(JottTree node: param){
            out = out + node.convertToJava()+",";
        }

        if (out.endsWith(",")) {
            out = out.substring(0, out.length() - 1);
        }
        return out + ")";
    }

    @Override
    public String convertToC() {
        String functname = id.convertToC();
        Expr p =this.param.get(0);

        if(functname.equals("print")){
            switch( p.getPrimitiveType()) {
                case BOOL:
                return "printf(\"%d\\n\", p,"+p.convertToC()+");";
                case INT:
                    return "printf(\"%d\\n\", p,"+p.convertToC()+");";
                case STRING:
                    return "printf(\"%s\\n\", p,"+p.convertToC()+");";
                case DBL:
                    return "printf(\"%f\\n\", p,"+p.convertToC()+");";
                default:
                    return "\n\nSOMETHING IS HORRIBLY WRONG\n\n";
            }
        }else{

            String out = id.convertToC()+"(";
            for(JottTree node: param){
                out = out + node.convertToC()+",";
            }

            if (out.endsWith(",")) {
                out = out.substring(0, out.length() - 1);
            }
            return out + ")";
    }}

    @Override
    public String convertToPython() {
        String out = id.convertToPython()+"(";
        for(JottTree node: param){
            out = out + node.convertToPython()+",";
        }

        if (out.endsWith(",")) {
            out = out.substring(0, out.length() - 1);
        }
        return out + ")";
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def functionContext) {
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
                if (actual instanceof Constant) {
                    if (((Constant) actual).getType() != expected.getType()) {
                        throw new SemanticException("Invalid parameter", null);
                    }
                }
                else if (actual instanceof Expr) {
                    actual.validateTree(table, functionContext);

                    if (actual.getPrimitiveType() != expected.getType()) {
                        throw new SemanticException("Expression types do not match", null);
                    }
                }
                else if (actual instanceof Function_Call) {
                    actual.validateTree(table, functionContext);
                }
                else if (actual instanceof Id && table.lookup(((Id) actual).getToken().getToken()) != null) {
                    Symbol variable = table.lookup(((Id) actual).getToken().getToken());
                    if (variable.getType() != expected.getType()) {
                        throw new SemanticException("Types do not match", null);
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
