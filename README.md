# Software Engineering Class Project (Java)

The core code was provided for the lexer, parser, constrainer, codegen, compiler.
Assignments for the class were to extend and modify this code using OO principles and design patterns. 

The interpreter, virtual machine, and bytecode language were implemented myself. 

The debugger portion is an extra credit assignment, and I look to finish it in the future. 

After the text input has been tokenized and parsed for contextual meaning, objects are composed to represent these tokens based on the grammar of the language. The Interpreter takes in a stream of these Objects, generates the corresponding Bytecode (akin to assembly instructions), and executes them in the Virtual Machine runtime environment.

I built a simple Java compiler in Java as a project in an OO design class. A boilerplate template was provided for many of the core components (lexer, parser, constrainer, codegen, and compiler). I extended and connected these pieces and implemented the interpreter, virtual machine, debugger, and a custom bytecode library to fit specifications. The interpreter serves to take tokenized input, generate a bytecode instruction based on the grammar of the language, and run those bytecodes in a virtual runtime environment (VM). This project heavily leveraged Object-Oriented practices and common Design Patterns such as the Decorator, Singleton, Visitor, and Factory patterns.
