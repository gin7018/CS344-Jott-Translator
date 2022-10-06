package parser.nodes.function;

import javax.management.RuntimeErrorException;

import parser.nodes.JottTree;
import parser.nodes.primitive.PType;

public class FunctionParameters {

    private final JottTree id;
    private final PType type;

    public FunctionParameters(JottTree id, PType type) {
        if(type.equals(PType.VOID)||type.equals(PType.KEYWORD)||type.equals(PType.ID)){
            throw new RuntimeException("Ptype  cannot be "+type.label);
        }
        this.id = id;
        this.type = type;
    }

    public JottTree getId() {
        return id;
    }

    public PType getType() {
        return type;
    }

    @Override
    public String toString() {
        return id.convertToJott() + ":" + type.name();
    }
}
