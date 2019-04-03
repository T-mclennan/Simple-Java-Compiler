package interpreter.bytecode;

import java.util.Vector;

import interpreter.VirtualMachine;

/**
 *  FALSEBRANCH <label> - pop the top of the stack; 
 *  if it's false (0) then branch to <label> else
 *  execute the next ByteCode:
 **/
public class FalseBranchCode extends BranchingCode{
	
	private String label;
	int address;
	
	public void execute(VirtualMachine vm) {

		//Pops top of Runtime Stack: if equal to 0 then branch, else continue.
		if (vm.popRunStack() == 0) {
			vm.changePC(address);
		} else {vm.incrementPC();}
		
		if (vm.getDumpState()) {
			System.out.println("FALSEBRANCH : " +label);
			vm.dumpRunStack();
		}
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
