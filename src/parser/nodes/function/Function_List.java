package parser.nodes.function;

import parser.nodes.JottTree;
import utils.Token;

import java.util.ArrayList;

public class Function_List implements JottTree {

    private ArrayList<Function_Def> functionDefs;

    private Function_List() {

    }

    public static Function_List createFunction_List(ArrayList<Token> tokens) {
        var fl = new Function_List();
        fl.functionDefs = new ArrayList<>();
        while (!tokens.isEmpty() && !tokens.get(0).getToken().equals("$$")) {
            var fd = Function_Def.createFunction_Def(tokens);
            fl.functionDefs.add(fd);
        }
        return fl;
    }

    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        return null;
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
