package interpreter.bytecode;

import java.util.Vector;
import interpreter.VirtualMachine;

/**
 *  ByteCode of syntax: DUMP <state>
 **/

public class DumpCode extends Bytecode{

	private String state;
	
	public void execute(VirtualMachine vm) {
		if (state.compareTo("ON") == 0) {
			vm.dumpOn();
		} else if (state.compareTo("OFF") == 0) {
			vm.dumpOff();
		}
		
		vm.incrementPC();
	}
	
	public void init(Vector<String> s) {
		state = s.get(0);
	}
}