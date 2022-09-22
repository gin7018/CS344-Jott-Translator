package parser.nodes.function;

import parser.nodes.JottTree;
import utils.Token;

import java.util.ArrayList;

public class Function_List implements JottTree {
    //FunctionDef lchild;
    Function_List rchild;

    private Function_List() {

    }

    public static Function_List createFunction_List(ArrayList<Token> tokens) {
        return null;
    }

    @Override
    public String convertToJott() {
        // TODO Auto-generated method stub
        //return lchild.convertToJott+rchild.convertToJott();
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
