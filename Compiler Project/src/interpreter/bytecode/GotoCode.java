package interpreter.bytecode;

import java.util.Vector;
import interpreter.VirtualMachine;

/**
 *  ByteCode of syntax: GOTO <label>
 **/

public class GotoCode extends BranchingCode{

	private String label;
	int address;
	
	public void execute(VirtualMachine vm) {
		if (vm.getDumpState()) {
			System.out.println("GOTO : " +label);
			vm.dumpRunStack();
		}
		
		vm.changePC(address);
	}
	
	public void init(Vector<String> s) {
		label = s.get(0);
	}
	
	public String getLabel() {
		return label;
	}
	
	public void updateLabel(Integer n) {
		address = n;
	}
}
