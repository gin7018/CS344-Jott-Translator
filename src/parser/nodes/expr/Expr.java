package parser.nodes.expr;

import parser.SymbolTable;
import parser.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.function.Function_Call;
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


    private Expr(){
        lnode = null;
        rnode = null;
        operator = null;
        isTail=false;
        ptype = null;
    }

    public static Expr createExpr(ArrayList<Token> tokens){
        Expr expr = new Expr();
        if(tokens.get(0).getTokenType() == TokenType.ID_KEYWORD){
           if(tokens.get(1).getTokenType()==TokenType.L_BRACKET){
            expr.lnode = Function_Call.createFunction_Call(tokens);
           }
           else if(Character.isUpperCase(tokens.get(0).getToken().charAt(0))){
            expr.lnode = Constant.CreateConstant(tokens);           }
           else{
            expr.lnode = Id.CreateId(tokens);
           }
        }
        else if(tokens.get(0).getTokenType() == TokenType.NUMBER){
            expr.lnode = Constant.CreateConstant(tokens);
        }
        else if(tokens.get(0).getTokenType()==TokenType.STRING){
            expr.lnode = Constant.CreateConstant(tokens);        }
        else{
            throw new SyntaxException("Expected a Number string or Id For expr but got " + tokens.get(0).getToken(), tokens.get(0));
        }
        if(tokens.get(0).getTokenType()==TokenType.MATH_OP||tokens.get(0).getTokenType()==TokenType.REL_OP){
            if(!(tokens.get(1).getTokenType()==TokenType.ID_KEYWORD||
            tokens.get(1).getTokenType()==TokenType.NUMBER||
            tokens.get(1).getTokenType()==TokenType.STRING)){
                throw new SyntaxException("Expected a ID keyword string or number to follow op but got " + tokens.get(1).toString(), tokens.get(1));
            }
            expr.operator=tokens.remove(0);
            expr.rnode =Expr.createExpr(tokens);
        }
        else{
            expr.isTail=true;
        }


        return expr;
    }

     
    @Override
    public String convertToJott() {
        if (isTail){
            return lnode.convertToJott();
        }
        else{
            return lnode.convertToJott() + operator.getToken() + rnode.convertToJott();
        }
    }

    @Override
    public String convertToJava() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String convertToC() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String convertToPython() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validateTree(SymbolTable table) {
        if(this.isTail){
            this.ptype = lnode.getPrimitiveType();
            return this.lnode.validateTree(table);
        }
        else if (!lnode.validateTree(table) ){
            return false;
        }
        else{

            PType left = lnode.getPrimitiveType();
            ExprType right = rnode.gExprType(table);
            Boolean mathop;
            String tokenop = this.operator.getToken();
            if(tokenop.equals("+")||tokenop.equals("*")||
            tokenop.equals("/")||tokenop.equals("-")){
                mathop=true;
            }
            else{
                mathop = false;
            }
            switch(right){
                case Iexpr:
                if(mathop){
                    this.ptype=PType.INT;
                }
                else{
                    this.ptype=PType.BOOL;
                }
                if(left!= PType.INT){
                    return false;
                }
                break;


                case Dexpr:
                if(mathop){
                    this.ptype=PType.DBL;
                }
                else{
                    this.ptype=PType.BOOL;
                }
                if(left!= PType.DBL){
                    return false;
                }
                break;


                case Irel:
                if(!mathop){
                    return false;
                }
                if(left!= PType.INT){
                    return false;
                }
                this.ptype = PType.BOOL;
                break;


                case Drel:
                if(!mathop){
                    return false;
                }
                if(left!= PType.DBL){
                    return false;
                }
                this.ptype = PType.BOOL;
                
                break;


                case Srel:
                if(mathop){
                    return false;
                }
                if(left!= PType.STRING){
                    return false;
                }
                this.ptype = PType.BOOL;
                break;
                case Fail:
                return false;
            }
            return true;
        }
    }

    private ExprType gExprType(SymbolTable table){
        if (!lnode.validateTree(table)){
            return null;
        }
        if (this.isTail){
            switch(lnode.getPrimitiveType()){
                case INT:
                return ExprType.Iexpr;
                case DBL:
                return ExprType.Dexpr;
                case STRING:
                return ExprType.Srel;
                default:
                return null;
            }
        }
        else{
            Boolean mathop;
            String tokenop = this.operator.getToken();
            if(tokenop.equals("+")||tokenop.equals("*")||
            tokenop.equals("/")||tokenop.equals("-")){
                mathop=true;
            }
            else{
                mathop = false;
            }
            PType lType = lnode.getPrimitiveType();
            ExprType eType = rnode.gExprType(table);
            switch (eType){
                case Srel:
                return ExprType.Fail;// can only have srelation
                case Iexpr:
                if(lType!=PType.INT){
                    return ExprType.Fail;
                }
                if(mathop){
                    return ExprType.Iexpr;
                }
                else{
                    return ExprType.Irel;
                }

                case Dexpr:
                if(lType!=PType.DBL){
                    return ExprType.Fail;
                }
                if(mathop){
                    return ExprType.Iexpr;
                }
                else{
                    return ExprType.Irel;
                }

                case Irel:
                if(lType!=PType.INT){
                    return ExprType.Fail;
                }
                if(mathop){
                    return ExprType.Irel;
                }
                else{
                    return ExprType.Fail;
                }
                case Drel:
                if(lType!=PType.DBL){
                    return ExprType.Fail;
                }
                if(mathop){
                    return ExprType.Drel;
                }
                else{
                    return ExprType.Fail;
                }
                case Fail:
                    return ExprType.Fail;
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


    


}
enum ExprType{
    Iexpr,
    Dexpr,
    Irel,
    Drel,
    Fail,
    Srel;
}
