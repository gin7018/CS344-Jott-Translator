package parser;

import parser.nodes.function.FunctionParameters;

import java.util.List;

public class Symbol {
    private String name;
    private String type;
    private List<FunctionParameters> attributeLst;

    public Symbol(String name, String type, List<FunctionParameters> attribute) {
        this.name = name;
        this.type = type;
        this.attributeLst = attribute;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<FunctionParameters> getAttributeLst() {
        return attributeLst;
    }
}
