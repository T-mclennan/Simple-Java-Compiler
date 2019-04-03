package interpreter.bytecode;

import java.util.Vector;
import interpreter.VirtualMachine;

/**
 *  FormalCode is a ByteCode of syntax: FORMAL <id> n 
 *  Represents a Formal Parameter argument 
 *  used for a function call. Adds value to Environment Stack.
 **/
public class FormalCode extends Bytecode {

	private String n, id;
	
	/**
	 * Pushes value n onto the RunTimeStack of given VM:
	 * @param Virtual Machine 
	 */
	public void execute(VirtualMachine vm) {	

		
		vm.pushRunStack(Integer.parseInt(n));
		vm.incrementPC();
		vm.checkEmptyTopFrame();
		vm.addLocalVar(id, Integer.parseInt(n));
		
		if (vm.getDumpState()) {
			System.out.print("FORMAL : " +id + " " + n);
			vm.dumpRunStack();
		}
		
		
	}
	
	public void init(Vector<String> s) {

		id = s.get(0);
     	 n = s.get(1);
	}
	
	public String getNum() {
		return n;
	}	
	public String getId() {
		return id;
	}
}
