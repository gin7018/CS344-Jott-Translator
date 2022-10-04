package parser.nodes.primitive;

import utils.Token;

public enum PType {
    INT,
    BOOL,
    DBL,
    ID,
    KEYWORD,
    STRING,
    VOID;

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


