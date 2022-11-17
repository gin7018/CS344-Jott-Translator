package parser.nodes.function;

import parser.SymbolTable;
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
            var id = Id.CreateId(tokens);
            popAndExpect(tokens, TokenType.COLON);
            var type = PType.getPrimitiveType(tokens.remove(0));
            var param = new FunctionParameters(id, type);
            fdParams.parameters.add(param);
            if (tokens.get(0).getTokenType().equals(TokenType.R_BRACKET)) {
                break; // we reached the end of parameter def
            }
            else {
                popAndExpect(tokens, TokenType.COMMA); // we expect a comma if param is not tail
            }
        }
        return fdParams;
    }

    public ArrayList<FunctionParameters> getParameters() {
        return parameters;
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
        StringBuilder result = new StringBuilder();
        String delim = "";
        for (FunctionParameters param: parameters) {
            result.append(delim).append(param.getType().label).append(" ").
                    append(param.getId().convertToJava());
            delim = ", ";
        }
        return result.toString();
    }

    @Override
    public String convertToC() {
        StringBuilder result = new StringBuilder();
        String delim = "";
        for (FunctionParameters param: parameters) {
            String typeToC  = param.getType().label; // TODO a function that takes a PTYPE and converts it to C
            result.append(delim).append(typeToC).append(" ").
                    append(param.getId().convertToC());
            delim = ", ";
        }
        return result.toString();
    }

    @Override
    public String convertToPython() {
        StringBuilder result = new StringBuilder();
        String delim = "";
        for (FunctionParameters param: parameters) {
            result.append(delim).append(param.getId().convertToPython());
            delim = ", ";
        }
        return result.toString();
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def function) {}

    @Override
    public PType getPrimitiveType() {
        return null;
    }
}
