package ast;

import visitor.*;
import lexer.Symbol;
import lexer.Token;

public class DeclTree extends AST {
    private Symbol symbol;
    private int line;
    
    public int getLine() {
    	return line;
    }
    
    
    public DeclTree() {
    }

    public DeclTree(Token tok) {
        this.symbol = tok.getSymbol();
        this.line =tok.getLineno();
    }
    
    public Object accept(ASTVisitor v) {
        return v.visitDeclTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    public String getType() {
    	return "Decl";
    }
}

