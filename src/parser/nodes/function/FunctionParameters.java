package parser.nodes.function;

import javax.management.RuntimeErrorException;

import parser.SyntaxException;
import parser.nodes.JottTree;
import parser.nodes.primitive.Id;
import parser.nodes.primitive.PType;

public class FunctionParameters {

    private final Id id;
    private final PType type;

    public FunctionParameters(Id id, PType type) {
        if(type.equals(PType.VOID)||type.equals(PType.KEYWORD)||type.equals(PType.ID)){
            throw new SyntaxException("Ptype cannot be "+type.label, id.getToken());
        }
        this.id = id;
        this.type = type;
    }

    public Id getId() {
        return id;
    }

    public PType getType() {
        return type;
    }

    @Override
    public String toString() {
        return id.convertToJott() + ":" + type.getLabel();
    }
}
