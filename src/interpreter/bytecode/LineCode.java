package interpreter.bytecode;

import java.util.Vector;
import interpreter.VirtualMachine;


public class LineCode extends Bytecode {

/**
 *  LineCode is a ByteCode of syntax: LINE n - outputs the number of the line 
 *  currently used from the SourceCode.
 **/

	private String n;
	
	/**
	 * @param Virtual Machine 
	 */
	public void execute(VirtualMachine vm) {	

		
		vm.incrementPC();
		
		if (vm.getDumpState()) {
			System.out.print("LINE : " +n);
			
			vm.dumpRunStack();
		}
	}
	
	public void init(Vector<String> s) {
		n = s.get(0);
	}
	
	public String getNum() {
		return n;
	}	

}