package factory;

public abstract class AbstractFactory {
	public Object createObject(String s) {
		return null;
	}    
    public String toString(){
      // System.out.println("This is an abstract creator");
       return "AbstractFactory";
    }


}