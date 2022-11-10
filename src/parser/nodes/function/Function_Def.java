package parser.nodes.function;

import parser.SymbolTable;
import parser.nodes.JottTree;
import parser.nodes.primitive.Id;
import parser.nodes.primitive.PType;
import parser.nodes.stmt.Body;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

import static parser.nodes.NodeUtility.popAndExpect;

public class  Function_Def implements JottTree{
    private Id id;
    private Function_Def_Params fdParams;
    private Function_Return functionReturn;
    private JottTree body;
    private static SymbolTable table;

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
        fd.body = Body.createBody(tokens, table);
        popAndExpect(tokens, TokenType.R_BRACE);

        return fd;
    }

    public Id getId() {
        return id;
    }

    public Function_Def_Params getFdParams() {
        return fdParams;
    }

    public Function_Return getFunctionReturn() {
        return functionReturn;
    }

    @Override
    public String convertToJott() {
        return id.convertToJott() +
                "[" + fdParams.convertToJott() + "]:" +
                functionReturn.convertToJott() + "{" +
                body.convertToJott() + "}";
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
        Function_Def.table.getTable().forEach((k,v) -> {
            table.insert(v);
        });
        // check if there is a function with a similar name
        if (table.lookup(id.getName()) == null) {
            return false;
        }

        // check if the body has a return statement that matches the expected return
        if (functionReturn == null && ((Body) body).getReturn_Stmt() != null) {
            // returning from a void function is invalid
            return false;
        }
        else if (functionReturn.getType() != PType.VOID && ((Body) body).getReturn_Stmt() == null) {
            // missing a return statement or return is VOID
            return false;
        }
        else if (functionReturn != null && ((Body) body).getReturn_Stmt() != null) {
            // if this is a returning function check if the types are matching
            boolean typesMatch = ((Function_Return) functionReturn).getType().equals(((Body) body)
                    .getReturn_Stmt().getType(table));
            if (!typesMatch) {
                return false;
            }
        }

        // check if the body is valid
        return body.validateTree(table);
    }

    @Override
    public PType getPrimitiveType() {
        return null;
    }
}
