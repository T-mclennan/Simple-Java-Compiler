package factory;

import interpreter.bytecode.*;

public class ConcreteByteCodeFactory extends AbstractFactory {
	
private Bytecode myCode;


	@Override
	public Bytecode createObject(String codeClass) {
    	//System.out.println(codeClass);
    	
        try {
    	       myCode = (Bytecode)(Class.forName("interpreter.bytecode."+codeClass).getDeclaredConstructor().newInstance());
    	      // myCode.execute();     
	        } catch (NoSuchMethodException e) {
	         	System.out.println(e);
	        } catch (ClassNotFoundException e) {
	        	System.out.println(e);
	        } catch (Exception e) {
	        	System.out.println(e);
	        }
        
        return myCode;
	}

}