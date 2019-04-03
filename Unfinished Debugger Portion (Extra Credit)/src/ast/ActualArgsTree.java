package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class ActualArgsTree extends AST {
    private Symbol symbol;
    private int line;
    
    public int getLine() {
    	return line;
    }
    
    
    public ActualArgsTree() {
    }
    
    public ActualArgsTree(Token tok) {
        this.symbol = tok.getSymbol();
        this.line =tok.getLineno();
    }

    public Object accept(ASTVisitor v) {
        return v.visitActualArgsTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    public String getType() {
    	return "ActualArgsTree";
    }
}

