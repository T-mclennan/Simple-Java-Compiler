package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class IntTypeTree extends AST {
    private Symbol symbol;
    
    public IntTypeTree() {
    }

    public IntTypeTree(Token tok) {
        this.symbol = tok.getSymbol();
    }
    
    public Object accept(ASTVisitor v) {
        return v.visitIntTypeTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    public String getType() {
    	return "IntType";
    }
}

