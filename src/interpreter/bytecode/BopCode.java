package interpreter.bytecode;

import java.util.Vector;

import interpreter.VirtualMachine;

/**
 *  BOP <binary op> - pop top 2 levels of the stack and perform 
 *  indicated operation – operations are + - / * == != <= > >= < 
 *  | &. | and & are logical operators, not bit operators. lower 
 *  level is the first operand: e.g. <second-level> + <top-level>
 **/

public class BopCode extends Bytecode {
	private String op;
	
	public void execute(VirtualMachine vm) {
		
		int arg1 = vm.popRunStack();
		int arg2 = vm.popRunStack();
		int result = -1;
		switch (op) {
			
		 case "+" :  {
			 result = arg2 + arg1;
			 break;
		}
		 
		 case "-" :  {
			 result = arg2 - arg1;
			 break;
		}
		 
		 case "/" :  {
			 result = arg2 / arg1;
			 break;
		}
		 
		 case "*" :  {
			 result = arg2 * arg1;
			 break;
		}
		 case "==" :  {
			 if (arg2 == arg1) {
				 result = 1;
			 }else {
				 result = 0;
			 }
			 break;
		}
		 case "<=" :  {
			 if (arg2 <= arg1) {
				 result = 1;
			 }else {
				 result = 0;
			 }
			 break;
		}
		 case ">=" :  {
			 if (arg2 >= arg1) {
				 result = 1;
			 }else {
				 result = 0;
			 }
			 break;
		}
		 
		 case ">" :  {
			 if (arg2 > arg1) {
				 result = 1;
			 }else {
				 result = 0;
			 }
			 break;
		}
		 case "<" :  {
			 if (arg2 < arg1) {
				 result = 1;
			 }else {
				 result = 0;
			 }
			 break;
		}
		 case "&" :  {
			 if (arg2 ==1 &&  arg1 ==1) {
				 result = 1;
			 }else {
				 result = 0;
			 }
			 break;
		}
		 case "|" :  {
			 if (arg2 ==1 ||  arg1 ==1) {
				 result = 1;
			 }else {
				 result = 0;
			 }
			 break;
		}
		
			default: break;
			
		}
		vm.pushRunStack(result);
		vm.incrementPC();
		vm.checkEmptyTopFrame();
		if (vm.getDumpState()) {
			System.out.println("BOP : " +op);
			vm.dumpRunStack();
		}
	}
	
	public void init(Vector<String> s) {
		op = s.get(0);
	}
	
	public String getArg() {
		return op;
	}
}
