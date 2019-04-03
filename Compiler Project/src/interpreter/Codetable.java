package interpreter;

import java.util.*;

/** <pre>
 *  Codetable class creates a lookup table that aids in converting the String
 *  Bytecode command (ie. HALT) into it's corresponding Class (HaltCode). 
 *  </pre>
*/

public class Codetable {

	
	HashMap<String,String> codeMap = new HashMap<>();
	
		public String get(String code) {
			String classCode = codeMap.get(code);
			return classCode;
		}
		
		public void init() {
             codeMap.put("ARGS", "ArgsCode");
             codeMap.put("BOP", "BopCode");
             codeMap.put("FALSEBRANCH", "FalseBranchCode");
             codeMap.put("GOTO", "GotoCode");
             codeMap.put("HALT", "HaltCode");
             codeMap.put("LABEL", "LabelCode");
             codeMap.put("LIT", "LitCode");
             codeMap.put("LOAD", "LoadCode");
             codeMap.put("POP", "PopCode");
             codeMap.put("READ", "ReadCode");
             codeMap.put("RETURN", "ReturnCode");
             codeMap.put("STORE", "StoreCode");
             codeMap.put("WRITE", "WriteCode");
             codeMap.put("CALL", "CallCode");
             codeMap.put("DUMP", "DumpCode");
			 
             if (codeMap.isEmpty())  
             { 
                 System.out.println("map is empty"); 
             } 
               
             else
             { 
            	 System.out.println("*** Contents of CodeTable: ***");
                 for (Map.Entry<String,String> entry : codeMap.entrySet())  
                     System.out.println("Key = " + entry.getKey() + 
                                      ", Value = " + entry.getValue());
             }
             System.out.println("\n");
		}
}
