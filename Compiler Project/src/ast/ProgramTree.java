package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class ProgramTree extends AST {
    private Symbol symbol;
    
    public ProgramTree() {
    }

    public ProgramTree(Token tok) {
        this.symbol = tok.getSymbol();
    }
    
    public Object accept(ASTVisitor v) {
        return v.visitProgramTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    public String getType() {
    	return "Program";
    }
}

