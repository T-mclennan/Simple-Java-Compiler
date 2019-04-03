package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class ProgramTree extends AST {
    private Symbol symbol;
    private int line;
    
    public int getLine() {
    	return line;
    }
    
    public ProgramTree() {
    }

    public ProgramTree(Token tok) {
        this.symbol = tok.getSymbol();
        this.line =tok.getLineno();
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

