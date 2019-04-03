package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class AssignTree extends AST {
    private Symbol symbol;
    private int line;
    
    public int getLine() {
    	return line;
    }
    
    public AssignTree() {
    }

    public AssignTree(Token tok) {
        this.symbol = tok.getSymbol();
        this.line =tok.getLineno();
    }
    
    public Object accept(ASTVisitor v) {
        return v.visitAssignTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    public String getType() {
    	return "AssignTree";
    }
    
}

