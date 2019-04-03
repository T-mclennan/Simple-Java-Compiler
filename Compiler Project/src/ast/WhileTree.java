package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class WhileTree extends AST {
    private Symbol symbol;
    
    public WhileTree() {
    }

    public WhileTree(Token tok) {
        this.symbol = tok.getSymbol();
    }
    
    public Object accept(ASTVisitor v) {
        return v.visitWhileTree(this);
    }
    
    public Symbol getSymbol() {
        return symbol;
    }
    
    public String getType() {
    	return "While";
    }
}

