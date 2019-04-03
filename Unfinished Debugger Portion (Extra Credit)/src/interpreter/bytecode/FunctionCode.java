package interpreter.bytecode;

import java.util.Vector;

import interpreter.VirtualMachine;

/**
 *  FunctionCode is a ByteCode of syntax: FUNCTION <id> start end; 
 *  <id> is the name of the function, and start and end are the line where 
 *  the function begins and ends respectively.
 **/
public class FunctionCode extends Bytecode {

	private String id, start, end;
	
	public void execute(VirtualMachine vm) {
		
		vm.executeFunc(id, Integer.parseInt(start), Integer.parseInt(end));
		vm.incrementPC();
		if (vm.getDumpState()) {
			System.out.println("FUNCTION : " +id+ "  "+start + "  "+end);
			vm.dumpRunStack();
		}
	}
	
	public void init(Vector<String> s) {
		id = s.get(0);
		start = s.get(1);
		end = s.get(2);
	}
	
	public String getName() {
		return id;
	}
	
	public String getStart() {
		return start;
	}
	
	public String getEnd() {
		return end;
	}
}
