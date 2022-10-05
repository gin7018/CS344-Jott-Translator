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
        id.id= tokens.remove(0);
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
