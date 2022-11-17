package parser.nodes.stmt;

import parser.SymbolTable;
import parser.nodes.JottTree;
import parser.nodes.function.Function_Def;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

public class Body implements JottTree {

    private Body_Stmt body_Stmt;
    private Body body;
    private Return_Stmt return_Stmt;

    public static Body createBody(ArrayList<Token> tokens, SymbolTable table) {
        var body = new Body();

        if(tokens.get(0).getToken().equals("return")){
            body.return_Stmt = Return_Stmt.createReturn_Stmt(tokens);
        }
        else if(tokens.get(0).getTokenType()==TokenType.R_BRACE){
            body.return_Stmt=null;
        }
        else {
            body.body_Stmt = Body_Stmt.createBody_Stmt(tokens, table);
            body.body = Body.createBody(tokens, table);
        }
        return body;

    }

//    public PType getReturnType(SymbolTable symbolTable) {
//        if (return_Stmt != null) {
//            return return_Stmt.getType(symbolTable);
//        }
//
//        if (body != null) {
//            return body.getReturnType(symbolTable);
//        }
//
//        if (body_Stmt != null) {
//            return body_Stmt.getReturnType(symbolTable);
//        }
//        return null;
//    }

    @Override
    public String convertToJott() {
        if(this.body_Stmt!= null &&this.body != null){
            return this.body_Stmt.convertToJott() + this.body.convertToJott();
        }else if(this.body_Stmt != null){
            return this.body_Stmt.convertToJott();
        }
        else if(this.return_Stmt != null){
            return this.return_Stmt.convertToJott();
        }
        else return "";
    }

    @Override
    public String convertToJava() {
        if(this.body_Stmt!= null &&this.body != null){
            return this.body_Stmt.convertToJava() + this.body.convertToJava();
        }else if(this.body_Stmt != null){
            return this.body_Stmt.convertToJava();
        }
        else if(this.return_Stmt != null){
            return this.return_Stmt.convertToJava();
        }
        else return "";
    }

    @Override
    public String convertToC() {
        if(this.body_Stmt!= null &&this.body != null){
            return this.body_Stmt.convertToC() + this.body.convertToC();
        }else if(this.body_Stmt != null){
            return this.body_Stmt.convertToC();
        }
        else if(this.return_Stmt != null){
            return this.return_Stmt.convertToC();
        }
        else return "";
    }

    @Override
    public String convertToPython() {
        if(this.body_Stmt!= null &&this.body != null){
            return this.body_Stmt.convertToPython() + this.body.convertToPython();
        }else if(this.body_Stmt != null){
            return this.body_Stmt.convertToPython();
        }
        else if(this.return_Stmt != null){
            return this.return_Stmt.convertToPython();
        }
        else return "";
    }
    

    @Override
    public void validateTree(SymbolTable table, Function_Def function) {
        if (return_Stmt != null) {
            return_Stmt.validateTree(table, function);
        }

        if (body_Stmt != null) {
            if (body != null) {
                body_Stmt.validateTree(table, function);
                body.validateTree(table, function);
            }

            body_Stmt.validateTree(table, function);
        }

        if (body != null) {
            body.validateTree(table, function);
        }
    }

    @Override
    public PType getPrimitiveType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasReturn() {
        if (return_Stmt != null) {
            return true;
        }

        if (body_Stmt != null && body_Stmt.hasReturn()) {
            return true;
        }

        if (body != null && body.hasReturn()) {
            return true;
        }

        return false;
    }
}
