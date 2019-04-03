package interpreter.bytecode;

import java.util.Vector;
import java.util.Stack;
import interpreter.VirtualMachine;

/**
 *  ARGS n ;
 *  Used prior to calling a function: n = #args
 **/
public class ArgsCode extends Bytecode {

	private String n;
	private Stack<Integer> argStack = new Stack<Integer>();
	
	public void execute(VirtualMachine vm) {	
		
		int argNumber = (int)Integer.parseInt(n);
		argStack.clear();

		//Pops off the top n Args and loads them into a temporary stack:
		for (int i = 0; i < argNumber; i++) {
			argStack.push(vm.popRunStack());
			if (vm.runStackSize() == 0) {
				vm.clearFrameStack();
			}
		}
		
		vm.newFrameAtRunStack(vm.runStackSize());
		
		//Pushes the Args back onto the stack in a new frame:
		for (int i = 0; i < argNumber; i++) {
			vm.pushRunStack(argStack.pop());
		}
	
	    vm.checkEmptyTopFrame();
		vm.incrementPC();
		if (vm.getDumpState()) {
			System.out.println("ARGS : " +n);
			vm.dumpRunStack();
		}
		
	}
	
	public void init(Vector<String> s) {
		n = s.get(0);
	}
	
	public String getArg() {
		return n;
	}
}
