package parser.nodes.function;

import parser.Symbol;
import parser.SymbolTable;
import parser.nodes.JottTree;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

public class Function_List implements JottTree {

    private ArrayList<Function_Def> functionDefs;
    private static SymbolTable tableOfFunctions;

    private Function_List() {
        tableOfFunctions = new SymbolTable();
    }

    public static Function_List createFunction_List(ArrayList<Token> tokens) {
        var fl = new Function_List();
        fl.functionDefs = new ArrayList<>();
        while (!tokens.isEmpty() && !tokens.get(0).getToken().equals("$$")&& !tokens.get(0).getTokenType().equals(TokenType.EOF)) {
            var fd = Function_Def.createFunction_Def(tokens, tableOfFunctions);
            fl.functionDefs.add(fd);

            ArrayList<FunctionParameters> params = fd.getFdParams().getParameters();
            Symbol function = new Symbol(fd.getId().getName(), fd.getReturnType(), params);
            tableOfFunctions.insert(function);
        }
        return fl;
    }

    @Override
    public String convertToJott() {
        StringBuilder result = new StringBuilder();
        for (Function_Def fDef: functionDefs) {
            result.append(fDef.convertToJott());
        }
        return result.toString();
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
    public boolean validateTree(SymbolTable table, Function_Def unused) {
        for (var functionDef: functionDefs) {
            if (!functionDef.validateTree(tableOfFunctions, functionDef)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public PType getPrimitiveType() {
        return null;
    }

}
