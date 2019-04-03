package interpreter;

import java.util.Vector;
import java.util.Stack;

/** <pre>
 *  The RunTimeStack class maintains the stack of active frames; when we call a
function we'll push a new frame on the stack; when we return from a function
we'll pop the top frame
 *  </pre>
*/
public class RunTimeStack {

	Stack<Integer> framePointers;
	Vector<Integer> runStack;
	private boolean topFrameEmpty = false;
	
	//Default Constructor:
	public RunTimeStack() {
		framePointers = new Stack<Integer>();
		runStack = new Vector<Integer>();
	}
	
	//Dump the RunTimeStack information for debugging:
	public void dump() {
		
		if (!runStack.isEmpty()) {
			
		
		Integer[] array = new Integer[framePointers.size()];
		framePointers.toArray(array);
		int lastFrame = framePointers.size()-1;
		int frameNum = 0;	
		int frameEnd = runStack.size()-1;	
		
			System.out.print("[");
		
		   if ((frameNum < lastFrame) && (!topFrameEmpty)) {
			   frameEnd = (int)array[frameNum+1]-1;
		   } 
		
		for (int i = 0; i< runStack.size(); i++) {
			System.out.print(runStack.get(i));
			if (i == frameEnd || i == runStack.size()) {
				   System.out.print("]");
				   
				   if (frameNum < lastFrame) {
					   frameNum++;
					   if (!topFrameEmpty) {System.out.print("[");}
					   if (frameNum < lastFrame) {
						   frameEnd = (int)array[frameNum+1]-1;
					   } else if (frameNum == lastFrame) {
						   frameEnd = runStack.size()-1;
					   }
				   }
			}
		}
		
		System.out.println("\n");
		} else {System.out.println();}
		
	}
	
	//Returns the top item on the runtime stack:
	public int peek() { 
		
		return (int)runStack.lastElement();
	}
	
	
	//pops the top item from the runtime stack, returns the item:
	public int pop() {
	  int temp = (int)runStack.lastElement();
	  runStack.removeElementAt(runStack.size()-1);
	  return temp;
	}
	
	//push item i this item on the runtime stack:
	public int push(int i) {
		runStack.add(i);
		return i;
	}
	
	//Starts new frame: 
	//@param: offset 
	//indicates the number of slots down from the top of RunTimeStack for
	//starting the new frame
	public void newFrameAt(int offset) {
		framePointers.push(offset);
	}
	
	
	//	We pop the top frame when we return from a function; before popping, the
	//function's return value is at the top of the stack so we'll save the value, pop the
	//top frame and then push the return value:
	public void popFrame() {
		int stackIndex = framePointers.peek();
		
		if (stackIndex != 0) {
			stackIndex = framePointers.pop();
			while (stackIndex <= (runStack.size()-1)) {
				runStack.removeElementAt(runStack.size()-1);
			}
		} else {	runStack.clear(); }
	}
	
	
	public void clearFrameStack() {
		framePointers.clear();
	}
	
	//Used to store into the current frame, with an integer offset:
	public int store(int offset) {
		return offset;
	}
	
	//Returns size of Run Stack:
	public int rstackSize() {
		return runStack.size();
	}
	
	//Returns size of Frame Pointer Stack:
	public int fstackSize() {
		return framePointers.size();
	}
	
	//Used to load literals onto the stack - e.g. for lit 5 we'll call push with 5:
	public int load(int offset) {
		return offset;
	}
	//Changes value at the index + frame offset:
	public void changeStackAtIndex(int index, int value) {
		int offset = framePointers.peek();
		runStack.setElementAt(value, (index+offset));
	}
	
	//Returns value at the index given + frame offset:
	public int getStackValueAt(int index) {
		int offset = framePointers.peek();
		return runStack.get(index+offset);
	}
	
	//This checks to see if the top frame is empty:
	//If the top frame begins after the top of the stack, 
	//then we have an empty frame on the top. 
	public void emptyTopFrame() {
		if (framePointers.peek() > (runStack.size()-1)) {
			topFrameEmpty = true;
		} else {topFrameEmpty = false;}

	}
	
	public boolean isTopFrameEmpty() {
		return topFrameEmpty;
	}
	
	public int topOfStack() {
		return runStack.lastElement();
	}
	
	
}
