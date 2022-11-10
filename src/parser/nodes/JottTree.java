package parser.nodes;

import parser.SymbolTable;
import parser.nodes.function.Function_Def;
import parser.nodes.primitive.PType;

/**
 * Interface for all Jott parse tree nodes
 *
 * @author Scott C Johnson
 */
public interface JottTree {

    /**
     * Will output a string of this tree in Jott
     *
     * @return a string representing the Jott code of this tree
     */
    public String convertToJott();

    /**
     * Will output a string of this tree in Java
     *
     * @return a string representing the Java code of this tree
     */
    public String convertToJava();

    /**
     * Will output a string of this tree in C
     *
     * @return a string representing the C code of this tree
     */
    public String convertToC();

    /**
     * Will output a string of this tree in Python
     *
     * @return a string representing the Python code of this tree
     */
    public String convertToPython();

    /**
     * This will validate that the tree follows the semantic rules of Jott
     * Errors validating will be reported to System.err
     *
     * @param table    The symbol table, with the scope of the function
     * @param function The function the scope is in
     * @return true if valid Jott code; false otherwise
     */
    public boolean validateTree(SymbolTable table, Function_Def function);

    public PType getPrimitiveType();

    /**
     * Checks if the current node returns unconditionally. If a node is an epsilon, this should return {@code false}.
     *
     * @return If the node returns unconditionally
     */
    default boolean hasReturn() {
        return false;
    }
}
