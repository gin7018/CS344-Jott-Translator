package parser.nodes;

import parser.exceptions.ContextUnawareSyntaxException;
import parser.exceptions.SemanticException;
import parser.SymbolTable;
import parser.nodes.function.Function_Def;
import parser.nodes.function.Function_List;
import parser.nodes.primitive.PType;
import utils.Token;
import utils.TokenType;

import java.util.ArrayList;

public class Program implements JottTree {

    private Function_List functionList;

    private Program() {}

    public static Program createProgram(ArrayList<Token> tokens) {
        var prg = new Program();
        prg.functionList = Function_List.createFunction_List(tokens);
        tokens.remove(0);
        tokens.remove(0);
        if (tokens.isEmpty() || !tokens.remove(0).getTokenType().equals(TokenType.EOF)){
            throw new ContextUnawareSyntaxException("No end of program flag found: $$");
        }
        return prg;
    }

    @Override
    public String convertToJott() {
        return functionList.convertToJott();
    }

    @Override
    public String convertToJava() {
        return "import java.util.Scanner;\n" + functionList.convertToJava();
    }

    public String convertToJava(String path){
        String result = path.substring(0, path.indexOf("."));
        return "import java.util.Scanner;\n"+
        "public class "+result + "{\n"+ 
        "public static void print(Object o){ \n System.out.println(o);"+
        "\n}\npublic static String concat(String s1,String s2){"+
        "\nreturn s1 +s2;\n}\n"+
        "public static String input(String msg,int buffer){"+
        "System.out.println(msg);\nScanner reader = new Scanner(System.in);"+
        "\nString out = reader.nextLine();\nreader.close();\nreturn out;\n}\n"+
        "public static int length(String string){\nreturn string.length();\n}\n"+
        functionList.convertToJava()+"\n}";
    }

    @Override
    public String convertToC() {
        String includes = "#include <stdio.h>\n" +
                "#include <string.h>\n" +
                "#include <stdlib.h>\n\n";
        return includes + functionList.convertToC();
    }

    @Override
    public String convertToPython() {
        return functionList.convertToPython();
    }

    @Override
    public void validateTree(SymbolTable table, Function_Def unused) {
        functionList.validateTree(null, null);
    }

    /**
     * Validates the tree, returning if the validation is successful. All semantic exceptions are caught and logged.
     *
     * @return If the tree is valid
     */
    public boolean validateTree() {
        try {
            validateTree(null, null);
        } catch (SemanticException e) {
//            e.printStackTrace();
            e.report();
            return false;
        }

        return true;
    }

    @Override
    public PType getPrimitiveType() {
        return null;
    }
}
