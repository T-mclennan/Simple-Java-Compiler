package interpreter.bytecode;

import java.util.Vector;
import interpreter.VirtualMachine;

/**
 *  HALT ByteCode serves to halt the execution of the Program. 
*/
public class HaltCode extends Bytecode {

	
	public void execute(VirtualMachine vm) {
		
		if (vm.getDumpState()) {
			System.out.println("HALT");
		}
		
		if (vm.vmDebugMode()) {
			vm.haltDebugger();
		}

		vm.setToNotRunning();	
	}
	
	public void init(Vector<String> s) {
	}
}
