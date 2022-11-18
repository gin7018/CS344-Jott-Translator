package parser.nodes.expr;

import parser.exceptions.SemanticException;
import parser.SymbolTable;
import parser.exceptions.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.function.Function_Call;
import parser.nodes.function.Function_Def;
import parser.nodes.primitive.Constant;
import parser.nodes.primitive.Id;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

public class Expr implements JottTree {
    JottTree lnode;
    Token operator;
    Expr rnode;
    Boolean isTail;
    PType ptype;


    private Expr() {
        lnode = null;
        rnode = null;
        operator = null;
        isTail = false;
        ptype = null;
    }

    public static Expr createExpr(ArrayList<Token> tokens) {
        Expr expr = new Expr();
        if (tokens.get(0).getTokenType() == TokenType.ID_KEYWORD) {
            if (tokens.get(1).getTokenType() == TokenType.L_BRACKET) {
                expr.lnode = Function_Call.createFunction_Call(tokens);
            } else if (Character.isUpperCase(tokens.get(0).getToken().charAt(0))) {
                expr.lnode = Constant.CreateConstant(tokens);
            } else {
                expr.lnode = Id.CreateId(tokens);
            }
        } else if (tokens.get(0).getTokenType() == TokenType.NUMBER) {
            expr.lnode = Constant.CreateConstant(tokens);
        } else if (tokens.get(0).getTokenType() == TokenType.STRING) {
            expr.lnode = Constant.CreateConstant(tokens);
        } else {
            throw new SyntaxException("Expected a Number string or Id For expr but got " + tokens.get(0).getToken(), tokens.get(0));
        }
        if (tokens.get(0).getTokenType() == TokenType.MATH_OP || tokens.get(0).getTokenType() == TokenType.REL_OP) {
            if (!(tokens.get(1).getTokenType() == TokenType.ID_KEYWORD ||
                    tokens.get(1).getTokenType() == TokenType.NUMBER ||
                    tokens.get(1).getTokenType() == TokenType.STRING)) {
                throw new SyntaxException("Expected a ID keyword string or number to follow op but got " + tokens.get(1).toString(), tokens.get(1));
            }
            expr.operator = tokens.remove(0);
            expr.rnode = Expr.createExpr(tokens);
        } else {
            expr.isTail = true;
        }


        return expr;
    }


    @Override
    public String convertToJott() {
        if (isTail) {
            return lnode.convertToJott();
        } else {
            return lnode.convertToJott() + operator.getToken() + rnode.convertToJott();
        }
    }

    @Override
    public String convertToJava() {
        
        if (isTail) {
            return lnode.convertToJava();
        } else {
            return lnode.convertToJava() + operator.getToken() + rnode.convertToJava();
        }
    }

    @Override
    public String convertToC() {
        if (isTail) {
            return lnode.convertToC();
        } else {
            return lnode.convertToC() + operator.getToken() + rnode.convertToC();
        }
    }

    @Override
    public String convertToPython() {
        if (isTail) {
            return lnode.convertToPython();
        } else {
            return lnode.convertToPython() + operator.getToken() + rnode.convertToPython();
        }
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def function) {
        if (this.isTail) {
            this.lnode.validateTree(table, function);
            this.ptype = lnode.getPrimitiveType();
        } else {
            lnode.validateTree(table, function);

            PType left = lnode.getPrimitiveType();
            ExprType right = rnode.gExprType(table, function);
            Boolean mathop;
            String tokenop = this.operator.getToken();
            if (tokenop.equals("+") || tokenop.equals("*") ||
                    tokenop.equals("/") || tokenop.equals("-")) {
                mathop = true;
            } else {
                mathop = false;
            }
            switch (right) {
                case Iexpr:
                    if (mathop) {
                        this.ptype = PType.INT;
                    } else {
                        this.ptype = PType.BOOL;
                    }
                    if (left != PType.INT) {
                        throw new SemanticException("Expected type Int", null);
                    }
                    break;


                case Dexpr:
                    if (mathop) {
                        this.ptype = PType.DBL;
                    } else {
                        this.ptype = PType.BOOL;
                    }
                    if (left != PType.DBL) {
                        throw new SemanticException("Expected type Double", null);
                    }
                    break;


                case Irel:
                    if (!mathop) {
                        throw new SemanticException("Invalid operator '" + tokenop + "'", null);
                    }
                    if (left != PType.INT) {
                        throw new SemanticException("Expected type Int", null);
                    }
                    this.ptype = PType.BOOL;
                    break;


                case Drel:
                    if (!mathop) {
                        throw new SemanticException("Invalid operator '" + tokenop + "'", null);
                    }
                    if (left != PType.DBL) {
                        throw new SemanticException("Expected type Double", null);
                    }
                    this.ptype = PType.BOOL;

                    break;


                case Srel:
                    if (mathop) {
                        throw new SemanticException("Invalid operator '" + tokenop + "'", null);
                    }
                    if (left != PType.STRING) {
                        throw new SemanticException("Expected type String", null);
                    }
                    this.ptype = PType.BOOL;
                    break;
                case Fail:
                    throw new SemanticException("Invalid expression", null);
            }
        }
    }

    private ExprType gExprType(SymbolTable table, Function_Def function) {
        lnode.validateTree(table, function);

        if (this.isTail) {
            return switch (lnode.getPrimitiveType()) {
                case INT -> ExprType.Iexpr;
                case DBL -> ExprType.Dexpr;
                case STRING -> ExprType.Srel;
                default -> null;
            };
        } else {
            Boolean mathop;
            String tokenop = this.operator.getToken();
            if (tokenop.equals("+") || tokenop.equals("*") ||
                    tokenop.equals("/") || tokenop.equals("-")) {
                mathop = true;
            } else {
                mathop = false;
            }
            PType lType = lnode.getPrimitiveType();
            ExprType eType = rnode.gExprType(table, function);
            switch (eType) {
                case Iexpr:
                    if (lType != PType.INT) {
                        return ExprType.Fail;
                    }
                    if (mathop) {
                        return ExprType.Iexpr;
                    } else {
                        return ExprType.Irel;
                    }

                case Dexpr:
                    if (lType != PType.DBL) {
                        return ExprType.Fail;
                    }
                    if (mathop) {
                        return ExprType.Iexpr;
                    } else {
                        return ExprType.Irel;
                    }

                case Irel:
                    if (lType != PType.INT) {
                        return ExprType.Fail;
                    }
                    if (mathop) {
                        return ExprType.Irel;
                    } else {
                        return ExprType.Fail;
                    }
                case Drel:
                    if (lType != PType.DBL) {
                        return ExprType.Fail;
                    }
                    if (mathop) {
                        return ExprType.Drel;
                    } else {
                        return ExprType.Fail;
                    }
                case Srel:
                case Fail:
                default:
                    return ExprType.Fail;
            }
        }
    }

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return ptype;
    }

    enum ExprType {
        Iexpr,
        Dexpr,
        Irel,
        Drel,
        Fail,
        Srel;
    }

}
