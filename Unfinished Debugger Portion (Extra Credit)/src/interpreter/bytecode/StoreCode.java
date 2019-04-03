package interpreter.bytecode;

import java.util.Vector;

import interpreter.VirtualMachine;

/**
 *  ByteCode of syntax: STORE n <id> - pop the top of the stack;
 *  store value into the offset n from the start of the frame;
 *  <id> is used as a comment, it’s the variable name where the 
 *  data is stored
 **/
public class StoreCode extends Bytecode {

	private String n, id;
	
	public void execute(VirtualMachine vm) {	
		
		int temp = vm.popRunStack();
		vm.changeRunStackValue((int)Integer.parseInt(n), temp);
		vm.checkEmptyTopFrame();
		vm.incrementPC();
		
		if (vm.getDumpState()) {
			System.out.println("STORE : " +n+ " "+id + "   "+id+" = "+n);
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
