package interpreter.bytecode;

import java.util.Vector;
import interpreter.VirtualMachine;

/**
 *  LitCode is a ByteCode of syntax: LIT n - load the literal value n
 *  LIT 0 i – this form of the Lit was generated to load 0 on the stack 
 *  in order to initialize the variable i to 0 and reserve space on the
 *   runtime stack for i.
 **/
public class LitCode extends Bytecode {

	private String n, id;
	
	/**
	 * Pushes value n onto the RunTimeStack of given VM:
	 * @param Virtual Machine 
	 */
	public void execute(VirtualMachine vm) {	

		
		vm.pushRunStack(Integer.parseInt(n));
		vm.incrementPC();
		vm.checkEmptyTopFrame();
		if (id != null) {
			vm.addLocalVar(id, Integer.parseInt(n));
		}

		
		if (vm.getDumpState()) {
			System.out.print("LIT : " +n);
			if (id != null) {
				System.out.println(" "+id + "   int "+id);
			} else {System.out.println(" "); }
			vm.dumpRunStack();
		}
		
		
	}
	
	public void init(Vector<String> s) {
		id = null;
		n = s.get(0);
		if (s.size() > 1) {
			id = s.get(1);
		}
	}
	
	public String getNum() {
		return n;
	}	
	public String getId() {
		return id;
	}
}
