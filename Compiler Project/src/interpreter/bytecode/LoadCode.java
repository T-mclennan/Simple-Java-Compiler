package interpreter.bytecode;

import java.util.Vector;

import interpreter.VirtualMachine;

/**
 *  LoadCode is a ByteCode of syntax: LOAD n <id>; 
 *  push the value in the slot which is offset n from the start 
 *  of the frame onto the top of the stack; 
 *  <id> is used as a comment, it’s the variable name from which 
 *  the data is loaded
 **/
public class LoadCode extends Bytecode {

	private String n, id;
	
	public void execute(VirtualMachine vm) {
		int temp = vm.getRunStackAt(Integer.parseInt(n));
		vm.pushRunStack(temp);
		vm.checkEmptyTopFrame();
		
		vm.incrementPC();
		if (vm.getDumpState()) {
			System.out.println("LOAD : " +n+ " "+id + "   <int "+id+">");
			vm.dumpRunStack();
		}
	}
	
	public void init(Vector<String> s) {
		n = s.get(0);
		id = s.get(1);
	}
	
	public String getNum() {
		return n;
	}	
	public String getId() {
		return id;
	}
}
