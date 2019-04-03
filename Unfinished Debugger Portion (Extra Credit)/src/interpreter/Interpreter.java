package interpreter;

import java.util.*;
import compiler.Compiler;
import interpreter.debugger.*;



/**
 * <pre>
 * 
 *  Tristan Mclennan :: CS413 
 *  Interpreter w/ Debugger: 
 *  
 *  @param sourceFile the String describing the user's source file 
 *  
 *  I ran out of time on this assignment, another 2 days work and I think I could have had it!
 *  
 *  I was working on getting the codeGen to properly produce the LINE ByteCode, and from 
 *  there I could do FUNCTION, and the rest seems straight forward. Thanks!
 *  
 *  Interpreter class is a driver program that serves to illustrate 
 *  the use of the ByteCodeLoader, Program Class, and ByteCode Classes. 
 *  It takes a command line input specifying the Source File (*.x.code), 
 *  and runs the ByteCode program on the Virtual Machine. 
 *  
 * </pre>
 */

public class Interpreter {

	private String codeFile;
	public static Vector<String> sourceLines = new Vector<String>();
	
	public Interpreter(String inputFile) {
		codeFile = inputFile;
	}	
	
	void run() {
		try {
			ByteCodeLoader bcl = new ByteCodeLoader(codeFile);
			Program program = bcl.loadCodes();
			VirtualMachine vm = new VirtualMachine(program);
			vm.executeProgram(); 
		} catch (Exception e) {
			System.out.println("**** " + e);
		}	
	} 
	
	public Vector<String> getSourceCode() {
		return sourceLines;
	}

	
	public static void main(String args[]) { 
	
		Program p = null;
		String codeFile = "simple.x";
		boolean debugOn = true;
		
		
		/**
		 * @param String from the command line.
		 * This checks for the debugger flag "-d", and calls the interpreter
		 * with the appropriate settings:
		 */
		if (args.length == 1) {
			codeFile = args[0] + ".x";
			
		} else if (args.length == 2 && args[0] == "-d") {
			codeFile = args[1] + ".x.cod";
			debugOn = true;
			
		} else if (args.length > 1) {
			System.out.println("***Incorrect usage, try: java interpreter.Interpreter <-d> <file>");
			System.exit(1);
			
		}

		try {	
			
			
        	Compiler myCompiler = new Compiler(codeFile);
        	myCompiler.compileProgram();
        	sourceLines = myCompiler.getSourceLines();
  
        	
			//Initialize and use ByteCodeLoader to populate Program p:
			ByteCodeLoader bcl = new ByteCodeLoader(codeFile + ".cod");	
			//bcl.printAll();
			p = bcl.loadCodes();
			bcl.close();
			
			if (debugOn) {
				UI debugUI = new UI(sourceLines, codeFile, p);
				debugUI.run();
			} else {
				VirtualMachine vm = new VirtualMachine(p);
				vm.executeProgram();
			}
			
		} catch (Exception e) {
			System.out.println("**** " + e);
		}
	} 
}