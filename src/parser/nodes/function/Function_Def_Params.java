package parser.nodes.function;

import parser.nodes.JottTree;
import parser.nodes.primitive.Id;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

import static parser.nodes.NodeUtility.popAndExpect;

public class Function_Def_Params implements JottTree{

    private ArrayList<FunctionParameters> parameters;


    private Function_Def_Params() {
    }

    public static Function_Def_Params createFunction_Def_Params(ArrayList<Token> tokens) {
        var fdParams = new Function_Def_Params();
        fdParams.parameters = new ArrayList<>();

        while (!tokens.isEmpty() && !tokens.get(0).getTokenType().equals(TokenType.R_BRACKET)) {
            if (tokens.get(0).getTokenType().equals(TokenType.COMMA)) {
                tokens.remove(0);
                continue;
            }
            var id = Id.CreateId(tokens);
            popAndExpect(tokens, TokenType.COLON);
            var type = PType.getPrimitiveType(tokens.remove(0));
            var param = new FunctionParameters(id, type);
            fdParams.parameters.add(param);
        }

        return fdParams;
    }

    @Override
    public String convertToJott() {
        StringBuilder result = new StringBuilder();
        for (FunctionParameters param: parameters) {
            result.append(param.toString());
        }
        return result.toString();
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
