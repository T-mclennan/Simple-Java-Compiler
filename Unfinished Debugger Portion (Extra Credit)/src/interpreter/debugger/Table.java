package interpreter.debugger;

import java.util.Set;

/**
 * <
 * pre> Binder objects group 3 fields 1. a value 2. the next link in the chain
 * of symbols in the current scope 3. the next link of a previous Binder for the
 * same identifier in a previous scope
 * </pre>
 */
class Binder {

    private int value;
    private String prevtop;   // prior symbol in same scope
    private Binder tail;      // prior binder for same symbol
    // restore this when closing scope
    private boolean watchSet;

    Binder(int v, String p, Binder t) {
        value = v;
        prevtop = p;
        tail = t;
        watchSet = false;
    }

    int getValue() {
        return value;
    }
    
    Binder setValue(int n) {
    	value = n;
    	return this;
    }

    String getPrevtop() {
        return prevtop;
    }

    Binder getTail() {
        return tail;
    }

    void setWatch(boolean b) {
        watchSet = b;
    }

    boolean isWatchSet() {
        return watchSet;
    }
}

/**
 * <
 * pre> This is the same class as in the constrainer with one exception - we do
 * not know when blocks start but we do know when they end when we see a POP
 * bytecode; endscope is modified to remove the last i symbols entered in the
 * table where i is an arg to endscope
 *
 * The Table class is similar to java.util.Dictionary, except that each key must
 * be a Symbol and there is a scope mechanism.
 *
 * Consider the following sequence of events for table t: t.put(Symbol("a"),5)
 * t.beginScope() t.put(Symbol("b"),7) t.put(Symbol("a"),9)
 *
 * symbols will have the key/value pairs for Symbols "a" and "b" as:
 *
 * Symbol("a") -> Binder(9, Symbol("b") , Binder(5, null, null) ) (the second
 * field has a reference to the prior Symbol added in this scope; the third
 * field refers to the Binder for the Symbol("a") included in the prior scope)
 * Binder has 2 linked lists - the second field contains list of symbols added
 * to the current scope; the third field contains the list of Binders for the
 * Symbols with the same string id - in this case, "a"
 *
 * Symbol("b") -> Binder(7, null, null) (the second field is null since there
 * are no other symbols to link in this scope; the third field is null since
 * there is no Symbol("b") in prior scopes)
 *
 * top has a reference to Symbol("a") which was the last symbol added to current
 * scope
 *
 * Note: What happens if a symbol is defined twice in the same scope??
 * </pre>
 */
public class Table {

    private java.util.HashMap<String, Binder> symbols = new java.util.HashMap<String, Binder>();
    private String top;    // reference to last symbol added to
    // current scope; this essentially is the
    // start of a linked list of symbols in scope
    private Binder marks;  // scope mark; essentially we have a stack of
    // marks - push for new scope; pop when closing
    // scope

    /*
    public static void main(String args[]) {
        String s = "a",
                s1 = "b",
                s2 = "c";

        Table t = new Table();
        System.out.println("Testing Symbol table");
        t.beginScope();
        System.out.println("Testing beginScope()");
        t.put(s, 0);
        System.out.println("Entering s = " + s + " into the symbol table");
        System.out.println("Value of s is " + t.get(s));
        t.beginScope();
        System.out.println("Entering nested block");
        t.put(s1, 1);
        t.put(s2, 2);
        System.out.println("Value of s2 in nested block is " + t.get(s2));
        t.put(s, 10);
        System.out.println("Leaving nested scope");
        t.endScope(2);
        t.put(s2, 22);
        System.out.println("Value of s2 in containing block is " + t.get(s2));
        System.out.println("Testing endScope()");
        t.endScope(1);
        System.out.println("Test completed");
    } */

    public Table() {
    }

    /**
     * Gets the object associated with the specified symbol in the Table.
     */
    public int get(String key) {
        Binder e = symbols.get(key);
        return e.getValue();
    }

    /**
     * Puts the specified value into the Table, bound to the specified
     * Symbol.<br> Maintain the list of symbols in the current scope (top);<br>
     * Add to list of symbols in prior scope with the same string identifier
     */
    public void put(String key, int value) {
        symbols.put(key, new Binder(value, top, symbols.get(key)));
        top = key;
    }

    /**
     * Remembers the current state of the Table; push new mark on mark stack
     */
    public void beginScope() {
        marks = new Binder(0, null, marks);
        top = null;
    }

    /**
     * Restores the table to what it was at the most recent beginScope that has
     * not already been ended. Remove the last n items put into the table
     */
    public void endScope(int n) {
        for (int i = 0; i < n; i++) {
            Binder e = symbols.get(top);
            if (e.getTail() != null) {
                symbols.put(top, e.getTail());
            } else {
                symbols.remove(top);
            }
            top = e.getPrevtop();
        }
    }

    /**
     * @return a set of the Table's symbols.
     */
    public java.util.Set<String> keys() {
        return symbols.keySet();
    }

    public void addWatchFor(String var) {
        Binder e = symbols.get(var);
        if (e == null) {
            return;
        }
        e.setWatch(true);
    }
    
    //Changes the value stored in the Key's Binder to the specified value:
    public void setVariable(String key, int n) {
        symbols.put(key, symbols.get(key).setValue(n));
    }

    public boolean isVarWatched(String var) {
        Binder e = symbols.get(var);
        if (e == null) {
            return false;
        }
        return e.isWatchSet();
    }
    
    public void displayVariables() {
    	
    	System.out.println("**** CURRENT LOCAL VARIABLES ****\n");
    	Set<String> keyset = symbols.keySet();
    	for (String name: keyset){

            int value = symbols.get(name).getValue();  
            System.out.println(name + " " + value);  
    	} 
    }
}
