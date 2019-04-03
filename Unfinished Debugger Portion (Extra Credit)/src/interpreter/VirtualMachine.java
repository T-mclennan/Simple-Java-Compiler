package interpreter;

import java.util.Stack;
import interpreter.bytecode.*;
import interpreter.Program;
import interpreter.debugger.*;

public class VirtualMachine {

	private int pc = 0;
	private int currentLine = 0;
	private boolean dumpState = false;
	private boolean isRunning = false;
	RunTimeStack runStack;
	Stack<Integer> returnAddrs;
	Stack<FunctionEnvironmentRecord> environmentStack = new Stack<FunctionEnvironmentRecord>();
	Program program;

	private UI debugUI = null;
	
	//Default constructor for VirtualMachine:
	public VirtualMachine(Program inputProg) {
		program = inputProg;
		runStack = new RunTimeStack();
	}
	
	//Debug Constructor for VirtualMachine:
	public VirtualMachine(@SuppressWarnings("exports") UI inputUI, Program inputProg) {
		program = inputProg;
		runStack = new RunTimeStack();
		debugUI = inputUI;
		
	}
	
	public void executeProgram() {
		this.setupVM();
		Bytecode code;

		//Executes ByteCodes until HALT or a Breakpoint:
		while (isRunning) {
			code = program.getCode(pc);
			code.execute(this);
		}
	}
	
	public void setupVM() {
		pc = 0;
		dumpState = false;
		this.newFrameAtRunStack(0);	
		returnAddrs = new Stack<Integer>();
		isRunning = true;
	}
	
	public void continueExecution() {
		Bytecode code;
		if (debugUI.breakpointHere(currentLine)) {
			code = program.getCode(pc);
			code.execute(this);
		} else {
			//Executes ByteCodes until HALT or a Breakpoint:
			while (isRunning && !debugUI.breakpointHere(currentLine)) {
				code = program.getCode(pc);
				code.execute(this);
			}
		}
	}
	
	public void stepForward() {
		Bytecode code = program.getCode(pc);
		code.execute(this);
	}
	
	//**
	/*  RUNSTACK / DEBUG METHODS: 
	 * 
	 * These are helper methods for managing the Run Stack and Debugger.
	 * These allow for encapsulation to be preserved within Virtual Machine:
	 * 
	 */
	public int popRunStack(){
		return runStack.pop();
	}
	
	public void pushRunStack(int i) {
		runStack.push(i);
	}
	
	public int peekRunStack() { 
		return runStack.peek();
	}
	
	public void newFrameAtRunStack(int offset) {
		runStack.newFrameAt(offset);
	}
	
	public void popFrameRunStack() {
		runStack.popFrame();
	}
	
	public int store(int offset) {
		return runStack.store(offset);
	}
	
	public int load(int offset) {
		return runStack.load(offset);
	}
	
	public void dumpRunStack() {
		runStack.dump();
	}
	
	public int frameStackSize() {
		return runStack.fstackSize();
	}
	
	public void changeRunStackValue(int index , int value) {
		runStack.changeStackAtIndex(index, value);
	}
	
	public int runStackSize() {
		return runStack.rstackSize();
	}
	
	public boolean getDumpState() {
		return dumpState;
	}
	
	public void dumpOn() {
		dumpState = true;
	}
	
	public void dumpOff() {
		dumpState = false;
	}
	
	public void changePC(int newPC) {
		pc = newPC;
	}
	
	public void incrementPC() {
		pc++;
	}
	
	public void setToNotRunning() {
		isRunning = false;
	}
	
	public int getRunStackAt(int index) {
		return (runStack.getStackValueAt(index));
	}
	
	public void checkEmptyTopFrame() {
		runStack.emptyTopFrame();
	}
	
	public boolean isTopFrameEmpty() {
		return runStack.isTopFrameEmpty();
	}
	
	public int topOfRunStack()	{	
	     return runStack.topOfStack();    
	}
	
	public void clearFrameStack() {
		runStack.clearFrameStack();
	}
	
	public void addReturnAddress() {
		returnAddrs.add(pc+1);
	}
	
	public int getReturnAddress() {
		return returnAddrs.pop();
	}
	
	public void haltDebugger() {
		debugUI.haltDebugger();
	}
	
	public void updateLine(int n) {
		currentLine = n;
	}
	
	public int getCurrentLine() {
		return currentLine;
	}
	
	//Returns true if Virtual Machine is in Debugger Mode:
	public boolean vmDebugMode() {
		return (debugUI != null);
	}
	
	public void executeFunc(String id,int start,int end) {
		FunctionEnvironmentRecord func = new FunctionEnvironmentRecord(start, end, id, currentLine);
		environmentStack.push(func);		
	}
	
	public void returnFunc() {
		if (!environmentStack.isEmpty()) {
			environmentStack.pop();
		}
	}
	
	public void addLocalVar(String id, int n) {
		if (!environmentStack.isEmpty()) {
			environmentStack.peek().addVariable(id, n);
		}
	} 
	
	public void popLocalVars(int n) {
		if (!environmentStack.isEmpty()) {
	     	environmentStack.peek().removeVariable(n);
		}
	}
	
	public void printLocalVars() {
		environmentStack.peek().printVariables();
	}
	
	public void setVariable(String id, int n) {
		environmentStack.peek().setVariable(id, n);
	}
}
