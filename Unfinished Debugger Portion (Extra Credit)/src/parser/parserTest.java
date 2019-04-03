package parser;

import visitor.*;
import ast.*;

/******************************************************************************
 * Tristan Mclennan : CSC 413: 10/17/18
 * Parser test class: Reads in a given text file and runs the parser, output
 * is performed with the print visitor class. Due to not knowing which type of
 * ASTree would be the root, I added a 'getType()' method to ASTree Class which can  
 * be combined with the getSymbol() method to set the root dynamically. 
 ******************************************************************************/
public class parserTest {
  
	   public static void main(String args[]) throws Exception {
		  
		   PrintVisitor myVisitor = new PrintVisitor(); 
		   try {
			   Parser myParser = new Parser("parseTest.txt");
			   AST t = myParser.execute();
			   System.out.println("\nPRINT: \n");
			   myVisitor.print(t.getType()+ ": " + t.getSymbol(), t);   
		   } catch (Exception e) {
			   throw e;
		   }
     
	   }
}
