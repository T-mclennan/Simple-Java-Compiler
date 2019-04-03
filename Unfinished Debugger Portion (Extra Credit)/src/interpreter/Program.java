package interpreter;

import java.util.*;
import interpreter.bytecode.*;

/** <pre>
 *  The Program Class serves as a Vector() based data structure for holding 
 *  ByteCode Objects. The resolveAddress() method uses a labelTable to edit 
 *  the contained Bytecode's labels to instead represent the numeric (Integer)
 *  address of the label. 
 *  </pre>
*/
public class Program {
    private Vector<Bytecode> p;
	HashMap<String,Integer> labelTable = new HashMap<>();
    
    public Program() {
    	p = new Vector<Bytecode>();
    }
    
    public void addCode(Bytecode newCode) {
    	p.add(newCode);
    }
    
    public Bytecode getCode(int index) {
    	return p.get(index);
    }
    
    public void resolveAddress() {
    	String address = "";
    	int index = 1;
    	for (Bytecode b : p) {
    		if (b.getClass().getName().equals("interpreter.bytecode.LabelCode")) {		
    			address = ((LabelCode)b).getLabel();
    			labelTable.put(address,index);		
    		}
    		index++;
    	 }
    	
    	 for (Bytecode b : p) {
    		 
    		if (b instanceof interpreter.bytecode.BranchingCode) {
    			index = 0;
     			address = ((BranchingCode)b).getLabel();
     			index = labelTable.get(address);
     			((BranchingCode)b).updateLabel(index);
     		}
     	 }
    	 
    }
}
