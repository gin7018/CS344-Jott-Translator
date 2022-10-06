package parser.nodes.primitive;



import parser.nodes.JottTree;
import utils.Token;

import java.util.ArrayList;

public class Id  implements JottTree {
    Token id;

    public Id(){

    }

    public static Id CreateId(ArrayList<Token> tokens){
        Id id = new Id();
        Token tok = tokens.remove(0);
        
        if( Character.isUpperCase(tok.getToken().charAt(0))){
            throw new RuntimeException("Id can not start with a Uppercase");
        }
        if(tok.getToken().equals("elseif")){
            throw new RuntimeException("elseif withought if");
        }
        if(tok.getToken().equals("else")){
            throw new RuntimeException("else withought if");
        }
        id.id= tok;
        return id;


    }
    @Override
    public String convertToJott() {
        return id.getToken();
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
