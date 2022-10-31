package parser;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {

    private static List<Symbol> table;

    private SymbolTable() {}

    public static SymbolTable allocate() {
        table = new ArrayList<>();
        return new SymbolTable();
    }

    public void reset() {
        table.clear();
    }

    public Symbol lookup(String name) {
        Symbol sym = null;
        for (Symbol symbol : table) {
            if (symbol.getName().equals(name)) {
                sym = symbol;
            }
        }
        return sym;
    }

    public void insert(Symbol sym) {
        table.add(sym);
    }



}
