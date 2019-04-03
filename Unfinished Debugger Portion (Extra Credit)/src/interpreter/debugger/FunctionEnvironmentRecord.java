package interpreter.debugger;

public class FunctionEnvironmentRecord {
	
	private String name;
	private int startLine = -1;
	private int endLine = -1;
	private int currentLine = -1;
	private int varsThisBlock = 0;
	
	Table symTable = new Table();
	
	
	public FunctionEnvironmentRecord(int start, int end, String functionName, int current) {
		startLine = start;
		endLine = end;
		name = functionName;
		currentLine = current;
        symTable.beginScope();
	}
	
	public void startBlock() {
		varsThisBlock = 0;
        symTable.beginScope();
	}
	
	public void endBlock() {
		symTable.endScope(varsThisBlock);
	}
	
	public void removeVariable(int n) {
		symTable.endScope(n);
		varsThisBlock -=n;
	}
	
	public void addVariable(String id, int n) {
        symTable.put(id, n);
        varsThisBlock++;
	}
	
	public void setVariable(String key, int n) {
		symTable.setVariable(key, n);
	}
	
	
	public void printVariables() {
		symTable.displayVariables();
	}
	
	public int getStart() {
		return startLine;
	}
	
	public int getEnd() {
		return endLine;
	}
	
	public int getCurrent() {
		return currentLine;
	}
	
	public String getName() {
		return name;
	}
}
