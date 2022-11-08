package parser;

import parser.nodes.function.FunctionParameters;
import parser.nodes.primitive.PType;

import java.util.List;

public class Symbol {
    private final String name;
    private final PType type;
    private final List<FunctionParameters> attributeLst;

    public Symbol(String name, PType type) {
        this(name, type, null);
    }

    public Symbol(String name, PType type, List<FunctionParameters> attributes) {
        this.name = name;
        this.type = type;
        this.attributeLst = attributes;
    }

    public String getName() {
        return name;
    }

    public PType getType() {
        return type;
    }

    /**
     * Gets the list of function parameters of the symbol. If this symbol does not represent a
     * {@link parser.nodes.function.Function_List}, this returns null.
     *
     * @return The function parameters
     */
    public List<FunctionParameters> getAttributeLst() {
        return attributeLst;
    }
}
