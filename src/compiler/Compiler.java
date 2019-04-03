package compiler;

import java.io.File;
import java.util.*;
import ast.*;
import parser.Parser;
import constrain.Constrainer;
import codegen.*;

/**
 *  The Compiler class contains the main program for compiling
 *  a source program to bytecodes
*/
public class Compiler {

/**
 * The Compiler class reads and compiles a source program
*/
	
	String sourceFile;
	private Vector<String> sourceLines = new Vector<String>();
	
    public Compiler(String sourceFile) {
    	this.sourceFile = sourceFile;
    }
    
    public void compileProgram() {
        try {
        	
            Parser parser = new Parser(sourceFile);
            AST t = parser.execute();
            Constrainer con = new Constrainer(t,parser);
            con.execute();
            Codegen generator = new Codegen(t);
            @SuppressWarnings("unused")
			Program program = generator.execute();

        }catch (Exception e) {
            System.out.println("********exception*******"+e.toString());
         };
    }
    
    /*
     * getSourceLines opens the source file, saves each line as a String, 
     * populates these into a Vector, and returns that Vector. 
     * @return: Vector of Strings
     */
    
    public Vector<String> getSourceLines() {
    	
    	try {
    		
    	
    		String sep = System.getProperty("file.separator");
    		String line;
    		File file = new File("src" + sep + "interpreter" + sep + sourceFile);
    		Scanner source = new Scanner(file);
    		int index =1;
    		
    	
	    	while (source.hasNextLine()) {
	    		line = index++ + ". " +source.nextLine();
	    		sourceLines.add(line);
	    	}
        	source.close();
        	
        } catch (Exception e) {
        	System.out.println(e);
        }
    	
    	return sourceLines;
    
    }
    public static void main(String args[]) {
        if (args.length == 0) {
           // System.out.println("***Incorrect usage, try: java compiler.Compiler <file>");
           // System.exit(1);
        }
       // (new Compiler(args[0])).compileProgram();
        
    
    }
}
