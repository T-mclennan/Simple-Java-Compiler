package interpreter;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Scanner;
import interpreter.Codetable;
import interpreter.bytecode.*;
import factory.*;

/** <pre>
 *  The ByteCodeLoader class is used to convert a text file of sequential
 *  ByteCode instructions into Program data structure that contains the 
 *  corresponding ByteCode Objects. This prepares the Program object to be 
 *  passed to the Virtual Machine for subsequent execution. 
 *  </pre>
*/
public class ByteCodeLoader {

    private Scanner source;
    private Vector<String> inputVector = new Vector<String>();
    private Program p = new Program();
    private StringTokenizer st; 
	private Codetable byteCodeTable = new Codetable();
	private ConcreteByteCodeFactory byteFactory;

	
	
	/**
	 *  Construct a new ByteCodeLoader:
	 *  Prints users PWD, initializes CodeTable, uses LoadLines method
	 *  @param sourceFile the String describing the user's source file
	 *  @exception IOException is thrown if there is an I/O problem
	*/
	    public ByteCodeLoader(String sourceFile) throws IOException {
	        try {
	     	    //Initializes CodeTable, InputVector, Factory:
		    	this.loadLines(sourceFile);
		    	byteCodeTable.init();
	    		byteFactory = new ConcreteByteCodeFactory();
	    			    		
	        } catch (Exception e) {
	        	System.out.println(e);
	        }
	    }

	 /**
	  * Takes each line of input file from buffer, and saves it into a vector of strings 
	  * inputVector for further processing:
	  */
	    public void loadLines(String sourceFile) throws IOException {
	    	
	      String line;
	    	try {
	    		//Sets up Scanner named source for file input:
	        	String sep = System.getProperty("file.separator");
	        	File file = new File("src" + sep + "interpreter" + sep + sourceFile);
	        	source = new Scanner(file);
	        	
	        	//This breaks each line of sourcefile into a string, saves in inputVector:
		    	while (source.hasNextLine()) {
		    		line = source.nextLine();
		    		inputVector.add(line);
		    	}
	    	} catch (IllegalStateException e) {
	    		System.out.println(e);
	    	} catch (Exception e) {
	    		System.out.println(e);
	    	}
	    }
	    
	  /**
	   * Loads the ByteCodes into instance of Program class: 
	   * @return Program object containing a vector of ByteCodes:
	   */
	    public Program loadCodes() {
	    	
	    	 String code, codeClass;
	    	 Vector<String> Args;
	        
	        
	        for (String line : inputVector) {
	        	
	            //Tokens the ByteCode out of the input line, finds corresponding string name,
	            //and passes it to the Factory for construction:
	  	        st = new StringTokenizer(line);
	        	code = st.nextToken();
	    		codeClass = byteCodeTable.get(code);
	        	Bytecode myCode = byteFactory.createObject(codeClass); 
		        
	        	//Saves remaining tokens as arguments into a Vector() Args:
	            Args = new Vector<String>();
		        while (st.hasMoreTokens()) {
		        	Args.add(st.nextToken());
		        } 
		        
		        myCode.init(Args);
	        	
		        if (myCode != null) {
		        	p.addCode(myCode);
		        }
	        }
	        
	        p.resolveAddress();
	        return p;	        
	   } //End loadCodes()
	  
	    public void printAll() {
	    	for (String line : inputVector) {
	    		System.out.println(line);
	    	}
	    }
	    public void close() {
	        try {
	            source.close();
	        } catch (Exception e) {}
	    } 
}
