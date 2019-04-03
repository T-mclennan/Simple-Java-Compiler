package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class AddOpTree extends AST {
    private Symbol symbol;
    private int line;
    
    public int getLine() {
    	return line;
    }
    
    public AddOpTree(Token tok) {
        this.symbol = tok.getSymbol();
        this.line =tok.getLineno();
        
    }

    public Object accept(ASTVisitor v) {
        return v.visitAddOpTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String getType() {
    	return "AddOp";
    }
    
}
