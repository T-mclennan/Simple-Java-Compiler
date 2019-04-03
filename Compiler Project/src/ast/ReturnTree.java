package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class ReturnTree extends AST {
    private Symbol symbol;
    
    public ReturnTree() {
    }

    public ReturnTree(Token tok) {
        this.symbol = tok.getSymbol();
    }
    
    public Object accept(ASTVisitor v) {
        return v.visitReturnTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    public String getType() {
    	return "Return";
    }
}

