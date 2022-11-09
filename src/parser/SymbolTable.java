package parser;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private final Map<String, Symbol> table;
    private final SymbolTable inheritedTable;

    public SymbolTable() {
        this(null);
    }

    /**
     * Creates a symbol table that checks the given table first during a lookup.
     *
     * @param inheritedTable The table to check first
     */
    public SymbolTable(SymbolTable inheritedTable) {
        this.table = new HashMap<>();
        this.inheritedTable = inheritedTable;
    }

    public void reset() {
        table.clear();
    }

    /**
     * Looks up a symbol by its name. First the {@link #inheritedTable} will be checked, if it was defined in the
     * constructor.
     *
     * @param name The name of the {@link Symbol}
     * @return The found symbol, or {@code null}
     */
    public Symbol lookup(String name) {
        if (inheritedTable != null) {
            var symbol = inheritedTable.lookup(name);
            if (symbol != null) {
                return symbol;
            }
        }

        return table.get(name);
    }

    public void insert(Symbol sym) {
        table.put(sym.getName(), sym);
    }

    public Map<String, Symbol> getTable() {
        return table;
    }
}
