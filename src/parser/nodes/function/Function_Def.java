package parser.nodes.function;

import parser.nodes.JottTree;
import parser.nodes.primitive.Id;
import parser.nodes.stmt.Body;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

import static parser.nodes.NodeUtility.popAndExpect;

public class  Function_Def implements JottTree{
    private JottTree id;
    private JottTree fdParams;
    private JottTree functionReturn;
    private JottTree body;

    private Function_Def() {

    }

    public static Function_Def createFunction_Def(ArrayList<Token> tokens) {
        var fd = new Function_Def();
        fd.id = Id.CreateId(tokens);
        popAndExpect(tokens, TokenType.L_BRACKET);
        fd.fdParams = Function_Def_Params.createFunction_Def_Params(tokens);
        popAndExpect(tokens, TokenType.R_BRACKET);
        popAndExpect(tokens, TokenType.COLON);
        fd.functionReturn = Function_Return.createFunction_Return(tokens);
        popAndExpect(tokens, TokenType.L_BRACE);
        fd.body = Body.createBody(tokens);
        popAndExpect(tokens, TokenType.R_BRACE);

        return fd;
    }

    @Override
    public String convertToJott() {
        return null;
    }

    @Override
    public String convertToJava() {
        return null;
    }

    @Override
    public String convertToC() {
        return null;
    }

    @Override
    public String convertToPython() {
        return null;
    }

    @Override
    public boolean validateTree() {
        return false;
    }
}
