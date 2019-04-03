package ast;

import visitor.*;
import lexer.Symbol;
import lexer.Token;

public class DeclTree extends AST {
    private Symbol symbol;
    
    public DeclTree() {
    }

    public DeclTree(Token tok) {
        this.symbol = tok.getSymbol();
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

