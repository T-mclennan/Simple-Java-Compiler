package interpreter;

import java.util.Stack;
import interpreter.bytecode.*;
import interpreter.Program;

public class VirtualMachine {

	private int pc = 0;
	RunTimeStack runStack;
	boolean isRunning = false;
	Stack<Integer> returnAddrs;
	Program program;
	private boolean dumpState = true;
	private boolean emptyTopFrame = false;
	
	public VirtualMachine(Program inputProg) {
		program = inputProg;
		runStack = new RunTimeStack();
	}
	
	public void executeProgram() {
		pc = 0;
		dumpState = false;
		this.newFrameAtRunStack(0);
		
		returnAddrs = new Stack();
		isRunning = true;
		while (isRunning) {
			Bytecode code = program.getCode(pc);
			code.execute(this);
		}
	}
	
	//**
	/* RUN STACK METHODS: These are helper methods for managing the Run Stack.
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
}
