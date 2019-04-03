package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class CallTree extends AST {
    private Symbol symbol;
    
    public CallTree() {
    }

    public CallTree(Token tok) {
        this.symbol = tok.getSymbol();
    }
    
    public Object accept(ASTVisitor v) {
        return v.visitCallTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    public String getType() {
    	return "Call";
    }
}

