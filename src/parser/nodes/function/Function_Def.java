package parser.nodes.function;

import parser.Symbol;
import parser.SymbolTable;
import parser.exceptions.SemanticException;
import parser.nodes.JottTree;
import parser.nodes.primitive.Id;
import parser.nodes.primitive.PType;
import parser.nodes.stmt.Body;
import utils.StringUtility;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

import static parser.nodes.NodeUtility.popAndExpect;

public class  Function_Def implements JottTree{
    private Id id;
    private Function_Def_Params fdParams;
    private Function_Return functionReturn;
    private Body body;
    private final SymbolTable table;

    private Function_Def(SymbolTable globalSymbolTable) {
        table = new SymbolTable(globalSymbolTable);
    }

    public static Function_Def createFunction_Def(ArrayList<Token> tokens, SymbolTable globalSymbolTable) {
        var fd = new Function_Def(globalSymbolTable);
        fd.id = Id.CreateId(tokens);
        popAndExpect(tokens, TokenType.L_BRACKET);
        fd.fdParams = Function_Def_Params.createFunction_Def_Params(tokens);
        popAndExpect(tokens, TokenType.R_BRACKET);
        popAndExpect(tokens, TokenType.COLON);
        fd.functionReturn = Function_Return.createFunction_Return(tokens);
        popAndExpect(tokens, TokenType.L_BRACE);
        fd.body = Body.createBody(tokens, fd.table);
        popAndExpect(tokens, TokenType.R_BRACE);

        for (FunctionParameters parameter : fd.fdParams.getParameters()) {
            fd.table.insert(new Symbol(parameter.getId().getName(), parameter.getType()));
        }

        return fd;
    }

    public Id getId() {
        return id;
    }

    public Function_Def_Params getFdParams() {
        return fdParams;
    }

    public PType getReturnType() {
        return functionReturn.getType();
    }

    @Override
    public String convertToJott() {
        return id.convertToJott() +
                "[" + fdParams.convertToJott() + "]:" +
                functionReturn.convertToJott() + "{\n" +
                body.convertToJott() + "\n}";
    }

    @Override
    public String convertToJava() {
        var returnString = functionReturn.convertToJava();
        String mainArgs ="";
        if (id.getName().equals("main")) {
            returnString = " void";
            mainArgs ="String[] args";
            
        }

        return "public static " + returnString + " " +
                id.convertToJava() + "(" +mainArgs+  fdParams.convertToJava() + ") {\n" +
                body.convertToJava() + "\n}\n";
    }

    @Override
    public String convertToC() {
        var returnString = functionReturn.convertToC();
        if (id.getName().equals("main")) {
            returnString = "int";
        }

        return returnString + " " +
                id.convertToC() + "(" + fdParams.convertToC() + ") {\n" +
                body.convertToC() + "\n}\n";
    }

    @Override
    public String convertToPython() {
        return StringUtility.removeBlankLines(id.convertToPython() + "(" + fdParams.convertToPython() + "):\n" +
                body.convertToPython().indent(4)) + "\n";
    }

    @Override
    public void validateTree(SymbolTable globalTable, Function_Def function) {
        // check if there is a function with a similar name
        if (globalTable.lookup(id.getName()) == null) {
            throw new SemanticException("Function not defined", null);
        }

        if (getReturnType() != PType.VOID) {
            if (!body.hasReturn()) {
                throw new SemanticException("Function has no return", null);
            }
        }

        // check if the body is valid
        //table.insert(new Symbol(functionReturn.toString(), "", ""));
        body.validateTree(table, this);
    }

    @Override
    public PType getPrimitiveType() {
        return null;
    }
}
