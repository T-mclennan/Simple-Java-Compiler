package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class BoolTypeTree extends AST {
    private Symbol symbol;
    private int line;
    
    public int getLine() {
    	return line;
    }
    
    public BoolTypeTree() {
    }

    public BoolTypeTree(Token tok) {
        this.symbol = tok.getSymbol();
        this.line =tok.getLineno();
    }
    
    public Object accept(ASTVisitor v) {
        return v.visitBoolTypeTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    public String getType() {
    	return "Bool";
    }
    
}

