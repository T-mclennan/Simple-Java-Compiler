package interpreter.bytecode;

import java.util.Vector;

import interpreter.VirtualMachine;

/**
 *  LableCode is a ByteCode of syntax: LABEL <label>; 
 *  target for branches; (see FALSEBRANCH, GOTO)
 **/
public class LabelCode extends Bytecode {

	
	private String label;
	
	public void execute(VirtualMachine vm) {
		
		if (vm.getDumpState()) {
			System.out.println("LABEL : " +label);
			vm.dumpRunStack();
		}
		
		vm.incrementPC();
	}
	
	public void init(Vector<String> s) {
		label = s.get(0);
	}
	
	public String getLabel() {
		return label;
	}
}
