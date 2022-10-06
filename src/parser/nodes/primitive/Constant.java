package parser.nodes.primitive;
import parser.nodes.JottTree;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;
public class Constant implements JottTree{

    private Token token;
    private PType type;

    private Constant(PType type, Token token){
        this.type = type;
        this.token = token;
    }

    public PType getType(){
        return this.type;
    }
    public Token getToken(){
        return this.token;
    }
    public static Constant CreateConstant(ArrayList<Token> tokens) {
        if (tokens.isEmpty()){
            throw new RuntimeException("something aint right");
        }
        Token tok = tokens.remove(0);
        if(tok.getTokenType() == TokenType.NUMBER){
            if(tok.getToken().contains(".")){
                    return new Constant(PType.DBL, tok);
            }else{
                return new Constant(PType.INT, tok);
            }
        }else if(tok.getToken().contains("True") || tok.getToken().contains("False")){
            return new Constant(PType.BOOL, tok);
        }else{
            return new Constant(PType.STRING, tok);
        }


    }

    public String getContents() {
        return token.getToken();
    }

    @Override
    public String convertToJott() {
        return getContents();
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
    public boolean validateTree() {
        // TODO Auto-generated method stub
        return false;
    }

}
