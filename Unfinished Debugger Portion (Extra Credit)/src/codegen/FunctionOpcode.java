package codegen;

/**
 * FunctionOpcode class records bytecodes that are functions, taking in start and end
 * positions in the sourceCode:
 * e.g. LABEL xyz
*/
public class FunctionOpcode extends Code {

	String label;
	int start, end;
    
/**
 *  @param code is the bytecode being created
 *  @param label is the string representation of the label of interest
*/
    public FunctionOpcode(Codes.ByteCodes code, String label, int s, int e) {
        super(code);
        this.label = label;
        start = s;
        end = e;
    }
    
    public void print() {
        System.out.println(toString());
    }
    
    public String toString() {
        return super.toString() + " " + label + " " + start + " " + end;
    }
}
