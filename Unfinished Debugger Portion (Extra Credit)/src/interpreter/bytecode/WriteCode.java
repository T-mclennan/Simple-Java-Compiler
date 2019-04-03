package interpreter.bytecode;

import java.util.Vector;

import interpreter.VirtualMachine;

/**
 *  WRITE ByteCode writes the value on top of the stack to output.
 *  leaves the value on top of the stack:
 **/
public class WriteCode extends Bytecode {

	public void execute(VirtualMachine vm) {
		
		if (vm.getDumpState()) {
			System.out.println("WRITE ");
			vm.dumpRunStack();
		}
		System.out.println(vm.peekRunStack());
		vm.incrementPC();
	}
	
	public void init(Vector<String> s) {
	}
}
