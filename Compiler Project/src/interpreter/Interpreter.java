package interpreter;

import java.io.*;
import interpreter.*;



/**
 * <pre>
 * 
 *  Tristan Mclennan :: CS413 
 *  Interpreter Milestone 2: 
 *  
 *  @param sourceFile the String describing the user's source file 
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

	public static void main(String args[]) { 
	
		Program p = null;
		String codeFile = "factorialll.x.cod";
		
		if (args.length == 1) {
			codeFile = args[0];
		} else if (args.length > 1) {
			System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
			System.exit(1);
		}

		try {	

			//Initialize and use ByteCodeLoader to populate Program p:
			ByteCodeLoader bcl = new ByteCodeLoader(codeFile);	
			p = bcl.loadCodes();
			bcl.close();
			VirtualMachine vm = new VirtualMachine(p);
			vm.executeProgram(); 
			
		} catch (Exception e) {
			System.out.println("**** " + e);
		}
	} 
}