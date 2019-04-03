package factory;

import java.util.EnumSet;

import ast.*;
import lexer.Symbol;
import lexer.Token;
import lexer.Tokens;

public class ConcreteASTFactory extends AbstractFactory {

   private EnumSet<Tokens> relationalOps
      = EnumSet.of(Tokens.Equal, Tokens.NotEqual, Tokens.Less, Tokens.LessEqual);
   private EnumSet<Tokens> addingOps
      = EnumSet.of(Tokens.Plus, Tokens.Minus, Tokens.Or);
   private EnumSet<Tokens> multiplyingOps
      = EnumSet.of(Tokens.Multiply, Tokens.Divide, Tokens.And);

	@Override
	public AST createObject(String s) {
		
		Symbol sym = Symbol.symbol(s,Tokens.BogusToken);
		Token t = new Token(0,0,0,sym);
		 Tokens kind = t.getKind();
	             if (addingOps.contains(kind)) { 
	              return new AddOpTree(t);
	      } else if (multiplyingOps.contains(kind)) { 
	              return new MultOpTree(t);
	      } else if (relationalOps.contains(kind)) { 
                  return new RelOpTree(t);              
          } else if (kind.equals(Tokens.Int)) {
	              return new IntTree(t);
	      } else if (kind.equals(Tokens.Program)){
                  return new ProgramTree(t);
          } else if (kind.equals(Tokens.INTeger)) {
                  return new IntTypeTree(t);
	      } else if (kind.equals(Tokens.BOOLean)){
                  return new BoolTypeTree(t);
          } else if (kind.equals(Tokens.If)) {
                  return new IfTree(t);
          } else if (kind.equals(Tokens.While)){
                  return new WhileTree(t);
          } else if (kind.equals(Tokens.Return)) {
                  return new ReturnTree(t);
	      } else if (kind.equals(Tokens.LeftBrace)){
                  return new BlockTree(t);
          }  
	         else return null;
	      }
}