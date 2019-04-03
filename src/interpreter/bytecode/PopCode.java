package interpreter.bytecode;

import java.util.Vector;

import interpreter.VirtualMachine;

/**
 *  POP n: Pop top n levels of runtime stack
 **/
public class PopCode extends Bytecode {
	private String n;
	
	public void execute(VirtualMachine vm) {
		
		int arg = Integer.parseInt(n);
		for (int i =0; i < arg; i++) {
			vm.popRunStack();
		}
		vm.popLocalVars(arg);
		vm.incrementPC();
		vm.checkEmptyTopFrame();
		
		if (vm.getDumpState()) {
			System.out.println("POP : " +n);
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
