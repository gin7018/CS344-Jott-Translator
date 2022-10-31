package parser.nodes.function;

import parser.SymbolTable;
import parser.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.primitive.PType;
import utils.Token;

import java.util.ArrayList;

public class Function_Return implements JottTree{

    private PType type;

    private Function_Return() {

    }

    public static Function_Return createFunction_Return(ArrayList<Token> tokens) {
        Function_Return fr = new Function_Return();
        var tk = tokens.remove(0);
        var potentialType = PType.getPrimitiveType(tk);
        if (potentialType != null) {
            fr.type = potentialType;
        }
        else {
            throw new SyntaxException("Unsupported return type: " + tk, tk);
        }
        return fr;
    }

    public PType getType() {
        return type;
    }

    @Override
    public String convertToJott() {
        return type.label;
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
    public boolean validateTree(SymbolTable table) {
        return false;
    }
}
