
package interpreter.bytecode;

import java.util.Vector;

import interpreter.VirtualMachine;

/**
 *  CallCode <label> - :
 **/
public class CallCode extends BranchingCode {
	private String label = " ";
	private int address;
	private String funcname= "";
	
	public void execute(VirtualMachine vm) {
		
		vm.addReturnAddress();
		vm.changePC(address);
	
		if (vm.getDumpState()) {
			
			System.out.print("CALL   "+label);
			if (!vm.isTopFrameEmpty()) {
				System.out.println("     "+funcname+"("+vm.peekRunStack()+")");
			} else {System.out.println();}
			
			//System.out.println("CALL   " +funcname+"     "+funcname+"("+
			//					vm.peekRunStack()+")");
			vm.dumpRunStack();
		}
	}
	
	//Takes in the argument and partitions into label and function name:
	public void init(Vector<String> s) {
		
		if (!s.isEmpty()) {
			label = s.get(0);
			int index = label.indexOf("<<");
			if (index > 0) {
				funcname = label.substring(0,(index));
			} else {funcname = label;}
		}
	}
	
	public String getLabel() {
		return label;
	}
	
	public void updateLabel(Integer n) {
		address = n;
	}
}
