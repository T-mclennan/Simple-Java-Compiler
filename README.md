# Simple Java Compiler (Java)

I built a simple Java compiler in Java as a project in an OO design class.

A boilerplate template was provided for many of the core components (lexer, parser, constrainer, codegen, and compiler). I extended and connected these pieces and implemented the interpreter, virtual machine, debugger, and a custom bytecode library to fit specifications. 

## Lexer: 
The reads the text file given, and breaks it into token strings. These strings are typecast from a symbol table of possible gramatic elements and encapsulated into a token object with it's symbol, content, and metadata. A stream of these tokens are then passed into the parser for gramatic interpretation. 


## Parser: 
We used a recursive descent parser in this assignment, which recursively composes nested Abstract Syntax Trees (AST's) to represent the token stream, acccording to the given grammar of the language. This is a flexible and dynamic process, and builds tree heirarchies based on the syntax of the identifiers and operators. The tree structure is a sound fit for both binary and unary operators, and nesting them allows for composition of complicated lexigraphical expressions. 


## Constrainer: 
The constrainer inputs in the AST's from the parser, and uses a recursive visitor to move through the expression trees and do type and error checking, decorating the AST's to constrain them as needed. 

## Interpreter: 
The interpreter takes in the stream of decorated AST's, runs them through the Compiler to generate corresponding Bytecode tokens, (A Bytecode is equivalent to an assembly operation found in MIPS). It then sets up a virtual runtime environment (the Virtual Machine) and runs the given Bytecodes.

## Compiler: 
The Compiler generates Bytecode tokens that are then run on Virtual Machine. 
 
 
## Virtual Machine: 
Composed of two Stacks, one for the actual stack frame and one for the frame pointers to manage function returns. Block scoping is done using a unique data structure. 


## Debugger: 
The Debugger is a wrapper that gives a command line interface to the workings of the interpreter. Instead of executing all the Bytecodes immediately and returning the result, the debugger allows for the User to step through each action, check the state of the variables and environment, add breakpoints, change values, etc. It is equivalent to a command line IDE. This was an extra credit assignment, I got most of the way through constructing it and hope to finish it soon. 
