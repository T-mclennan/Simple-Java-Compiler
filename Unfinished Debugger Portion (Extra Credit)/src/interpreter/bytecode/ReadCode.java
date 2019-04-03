package interpreter.bytecode;



import java.util.Vector;
import interpreter.VirtualMachine;
import java.io.BufferedReader;
import java.io.InputStreamReader;



/**
 *  READ ; Read an integer; prompt the user for input; put the
value just read on top of the stack
 **/
public class ReadCode extends Bytecode {

private int input;

	public void execute(VirtualMachine vm) {
		
		try {
		     BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
			 System.out.println("Please enter an integer as input: ");
		     String input = in.readLine();
			vm.pushRunStack(Integer.parseInt(input));
		} catch( java.io.IOException ex ) {
		}



		vm.checkEmptyTopFrame();
		
		if (vm.getDumpState()) {
			System.out.println("READ : " +input);
			vm.dumpRunStack();
		}
		
		vm.incrementPC();
	}
	
	public void init(Vector<String> s) {
	}
}
