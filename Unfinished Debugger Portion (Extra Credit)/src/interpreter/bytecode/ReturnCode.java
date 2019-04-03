package interpreter.bytecode;

import java.util.Vector;
import interpreter.VirtualMachine;

/**
 *  RETURN <funcname> ; Return from the current function; 
 *  <funcname> is used as a comment to indicate the current 
 *  function
 **/
public class ReturnCode extends Bytecode {

	private String funcname = " ";
	private String label = " ";
	
	public void execute(VirtualMachine vm) {

		int returnValue = vm.topOfRunStack();
		vm.popFrameRunStack();
		
		//Check for GRATIS NON-VALUE: VOID function:
	
		/*if (returnValue != 0) {
			vm.pushRunStack(returnValue);
		} */
		
		vm.pushRunStack(returnValue);
		vm.checkEmptyTopFrame();
		
		if (vm.getDumpState()) {
			System.out.print("RETURN   ");
			if (label != " ") {
				System.out.println(label+"     "+funcname+"("+vm.peekRunStack()+")");
			} else {System.out.println();}
								
			vm.dumpRunStack();
		}
		vm.returnFunc();
		vm.changePC(vm.getReturnAddress());
	}
	
	//Takes in the argument as a label and function name:
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
}
