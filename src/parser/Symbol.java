package parser;

import parser.nodes.function.FunctionParameters;

import java.util.List;

public class Symbol {
    private String name;
    private String type;
    private String attribute;
    private List<FunctionParameters> attributeLst;

    public Symbol(String name, String type, String attribute) {
        this.name = name;
        this.type = type;
        this.attribute = attribute;
    }

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

    public String getAttribute() {
        return attribute;
    }
}
