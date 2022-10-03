package parser.nodes.function;

import parser.nodes.JottTree;
import parser.nodes.primitive.PType;

public class FunctionParameters {

    private JottTree id;
    private PType type;

    public FunctionParameters(JottTree id, PType type) {
        this.id = id;
        this.type = type;
    }

    public JottTree getId() {
        return id;
    }

    public PType getType() {
        return type;
    }
}
