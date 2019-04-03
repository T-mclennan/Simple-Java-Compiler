package interpreter.debugger;

import java.util.*;
import interpreter.*;

public class UI {
	
	private Vector<String> sourceLines; 
	//private Vector<Boolean> breakpoints;\
	private boolean[] breakpoints;
	private Program p;
	private String sourceName;
	private VirtualMachine debugVM;
	private boolean running = false;
	Scanner scan;

/*
 * Construct a UI for debugger VM Class:
 * @param codeLines: a Vector of Strings (source code) 
 * @param codeFileName: a String representing the name of source file
 * @param inputProgram: a Program structure that holds ByteCodes
 */
	public UI (Vector<String> codeLines, String codeFileName, Program inputProgram) {
		sourceName = codeFileName;
		sourceLines = codeLines;
		p = inputProgram;
	}
	
	public void initialize() {
		debugVM = new VirtualMachine(this, p);
		debugVM.setupVM();
		scan = new Scanner(System.in);
		
		breakpoints = new boolean[sourceLines.size()];
		for (int i =0; i<sourceLines.size(); i++) {
			breakpoints[i] = false;
		}
	}
	
	public boolean breakpointHere(int index) {
		return breakpoints[index];
	}
	
	private void addBreakpoint(int index) {
		breakpoints[index] = true;
		String temp = sourceLines.elementAt(index);
		sourceLines.setElementAt("*" + temp, index);
		System.out.println("Breakpoints added at " + index);
	}
	
	private void removeBreakpoint(int index) {
		breakpoints[index] = false;
		if (sourceLines.elementAt(index) == "*") {
			sourceLines.removeElementAt(index);
		}
		
		System.out.println("Breakpoints removed at " + index);
	}
	
	private void listBreakpoints() {
		for (int i = 0; i < sourceLines.size(); i++) {
			if (breakpoints[i])
		    	System.out.print(" "+(i+1) + " ");
		}
		System.out.print("\n");
	}
	
	public void dumpOn() {
		debugVM.dumpOn();
		System.out.println("Dumping turned on.");
	}
	
	public void dumpOff() {
		debugVM.dumpOff();
		System.out.println("Dumping turned off.");
	}
	
	public void haltDebugger() {
		running = false;
	}
	
	public void displaySource() {
		System.out.print("\n");
    	for (String line : sourceLines) {
        	System.out.println(line);
    	}
	}
	
	public void setVariable() {
		System.out.print("Which variable to change? ");
		String id = scan.next();

		System.out.print("What value should "+ id+" have? ");
		int value = scan.nextInt();
		
		debugVM.setVariable(id, value);
		System.out.println("Set "+id+" to "+ value+ " .");
	}
	
	/*
	 * reads in one or more integers and sets breakpoints for each:
	 */
	private void breakpointPrompt() {
		int index = 0;
		try {
			System.out.print("Where would you like to add breakpoints? ");
			String inputLine = scan.nextLine();
			StringTokenizer st = new StringTokenizer(inputLine);
			while (st.hasMoreTokens()) {
				index = Integer.parseInt(st.nextToken());
				addBreakpoint(index-1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	/*
	 * reads in one or more integers and removes breakpoints for each:
	 */
	private void breakpointRemovePrompt() {
		int index = 0;
		try {
			System.out.print("Where would you like to remove breakpoints? ");
			String inputLine = scan.nextLine();
			StringTokenizer st = new StringTokenizer(inputLine);
			while (st.hasMoreTokens()) {
				index = Integer.parseInt(st.nextToken());
				removeBreakpoint(index-1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void clearBreakpoints() {
		for (int i= 0; i < sourceLines.size(); i++) {
			breakpoints[i] = false;
		}
	}
	
	private void helpPrompt() {
		
		System.out.println("******** DEBUGGER COMMAND LIST: *********\n");
		System.out.println("  Input:          |        Function:          ");
		System.out.println("______________________________________________\n");
		System.out.println("Continue          :   Continues execution of until breakpoint\n");
		System.out.println("Set Breakpoint    :   Sets breakpoints at given index\n");
		System.out.println("Remove Breakpoint :   Removes breakpoints at index\n");
		System.out.println("List Breakpoints  :   Lists current breakpoints\n");
		System.out.println("Clear Breakpoints :   Clears all breakpoints\n");
		System.out.println("Step Forward      :   Executes the next instruction\n");
		System.out.println("Display Source    :   Displays current source code\n");
		System.out.println("Display Variables :   Displays local variables\n");
		System.out.println("Change  Variable  :   Change value of local variable\n");
		System.out.println("Current Function  :   Displays current function\n");
		System.out.println("Dump on/off       :   Turns on / off Bytcode Dumping\n");
		System.out.println("Quit              :   Halts execution and exits program\n");
		
	}
	
	private void executeNext() {
		debugVM.stepForward();
	}
	
	public void continueExec() {
		debugVM.continueExecution();
	}

	/*
	 * Run() creates the main loop in which the Debugger takes user input and requests
	 * actions from the VM. Exit on user input "q" or executing a HALT code. 
	 */
	public void run() {
		initialize();
		running = true;
		String input = null;
		
    	System.out.println("\n**** Debugging " + sourceName + ": ****\n");
    	displaySource();
    	System.out.println("\n");
		System.out.println("Type ? for help. \n");
		
    	while (running) {
    		
    		try {
    	    	System.out.print("\n");
        		System.out.print(">> ");
   		      input = scan.nextLine();
   		     
   		    } catch(Exception ex ) {
   		    	System.out.println(ex);
   		    }
    		
    		switch (input.toLowerCase())  {
  
    			case "continue"          : continueExec();
    								    break;
    							       
    			case "add breakpoint"    : 					    
    			case "set breakpoint"    : 		
    			case "set breakpoints"   : breakpointPrompt();
				                        break;
				
    			case "remove breakpoint" : 
    			case "remove breakpoints": breakpointRemovePrompt();
    								    break;
    								    
    			case "list breakpoints"  : listBreakpoints();
										break;  

    			case "clear breakpoints" : clearBreakpoints();
			    						break;	
			    						
    			case "local variables"   : 						
    			case "display local variables": 
    			case "variables"         :	
    			case "display variables" : debugVM.printLocalVars();
				   						break;
				   						
    			case "display source"    : displaySource();
    									break;
    			
    			case "set variable"      :
    			case "change variable"   : setVariable();
										break;
										
    			case "step"              :
    			case "step forward"      : executeNext();
				   						break;  
				
    			case "dump on"           :
    			case "dumping on"        : dumpOn();
										break;	
				
    			case "dump off"          : 
    			case "dumping off"       : dumpOff();
										break;	
				   						
    			case "?"                 : helpPrompt();
    									break;
    									
    			case "quit"              : running = false;
		        						break;						
    								   
    			default : break;
    			
    			
    			
    		}
    	}
		scan.close();
	}

}
