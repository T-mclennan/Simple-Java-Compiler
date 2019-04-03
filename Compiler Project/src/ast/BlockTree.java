package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class BlockTree extends AST {
    private Symbol symbol;
    
    public BlockTree() {
    }

    public BlockTree(Token tok) {
        this.symbol = tok.getSymbol();
    }
    
    public Object accept(ASTVisitor v) {
        return v.visitBlockTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    public String getType() {
    	return "Block:";
    }
    
}

