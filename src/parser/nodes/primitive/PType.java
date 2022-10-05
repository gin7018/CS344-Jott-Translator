package parser.nodes.primitive;

import utils.Token;

public enum PType {
    INT("Integer"),
    BOOL("Boolean"),
    DBL("Double"),
    ID(""),
    KEYWORD(""),
    STRING("String"),
    VOID("Void");

    public final String label;
    PType(String name) {
        this.label = name;
    }

    public static PType getPrimitiveType(Token token) {
        return switch (token.getToken()) {
            case "Integer" -> INT;
            case "Boolean" -> BOOL;
            case "String" -> STRING;
            case "Void" -> VOID;
            default -> null;
        };
    }
}


