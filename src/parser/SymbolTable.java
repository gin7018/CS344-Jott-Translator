package parser;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private static Map<String, Symbol> table;

    private SymbolTable() {}

    public static SymbolTable allocate() {
        table = new HashMap<>();
        return new SymbolTable();
    }

    public void reset() {
        table.clear();
    }

    public Symbol lookup(String name) {
        return table.get(name);
    }

    public void insert(Symbol sym) {
        table.put(sym.getName(), sym);
    }



}
