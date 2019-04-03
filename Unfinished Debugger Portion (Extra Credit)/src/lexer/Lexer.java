package lexer;


/**
 *  The Lexer class is responsible for scanning the source file
 *  which is a stream of characters and returning a stream of 
 *  tokens; each token object will contain the string (or access
 *  to the string) that describes the token along with an
 *  indication of its location in the source program to be used
 *  for error reporting; we are tracking line numbers; white spaces
 *  are space, tab, newlines
*/
public class Lexer {
    private boolean atEOF = false;
    private char ch;     // next character to process
    private SourceReader source;
    
    // positions in line of current token
    private int startPosition, endPosition, lineNumber; 

    public Lexer(String sourceFile) throws Exception {
        new TokenType();  // init token table
        source = new SourceReader(sourceFile);
        ch = source.read();
    }

   /* public static void main(String args[]) {
        Token tok;
        String p = "";
        
        //Command Line Option:
        //check for the presence of a single command line arguments:
        if(args.length != 1) {
        	  System.err.println("Invalid text file, exactly one argument required:");
        	  System.exit(1);
        	} 
        
        try {
        	
        	Lexer lex = new Lexer("simple.x");
        	
            while (true) {
                tok = lex.nextToken();
                if ((tok.getKind() == Tokens.Identifier) ||
                    (tok.getKind() == Tokens.INTeger) ||
                    (tok.getKind() == Tokens.Float)) {
                   p = tok.toString();
                } else 
                   p = TokenType.tokens.get(tok.getKind()) + " "; 
                
                System.out.println(String.format("%-8s Left: %s Right: %s Line: %s", 
                		p, tok.getLeftPosition(), tok.getRightPosition(),tok.getLineno()));

            }
        } catch (Exception e) {}
    } */

 
/**
 *  newIdTokens are either ids or reserved words; new id's will be inserted
 *  in the symbol table with an indication that they are id's
 *  @param id is the String just scanned - it's either an id or reserved word
 *  @param startPosition is the column in the source file where the token begins
 *  @param endPosition is the column in the source file where the token ends
 *  @return the Token; either an id or one for the reserved words
*/
    public Token newIdToken(String id,int startPosition,int endPosition,int lineNumber) {
        return new Token(startPosition,endPosition,lineNumber,Symbol.symbol(id,Tokens.Identifier));
    }

/**
 *  number tokens are inserted in the symbol table; we don't convert the 
 *  numeric strings to numbers until we load the bytecodes for interpreting;
 *  this ensures that any machine numeric dependencies are deferred
 *  until we actually run the program; i.e. the numeric constraints of the
 *  hardware used to compile the source program are not used
 *  @param number is the int String just scanned
 *  @param startPosition is the column in the source file where the int begins
 *  @param endPosition is the column in the source file where the int ends
 *  @return the int or float Token
*/
    public Token newNumberToken(String number,int startPosition,int endPosition,int lineNumber) {
        return new Token(startPosition,endPosition,lineNumber,
            Symbol.symbol(number,Tokens.INTeger));
    }
    
    public Token newFloatToken(String number,int startPosition,int endPosition,int lineNumber) {
        return new Token(startPosition,endPosition,lineNumber,
            Symbol.symbol(number,Tokens.Float));
    }

/**
 *  build the token for operators (+ -) or separators (parens, braces)
 *  filter out comments which begin with two slashes
 *  @param s is the String representing the token
 *  @param startPosition is the column in the source file where the token begins
 *  @param endPosition is the column in the source file where the token ends
 *  @return the Token just found
*/
    public Token makeToken(String s,int startPosition,int endPosition,int lineNumber) {
        if (s.equals("--")) {  // filter comment
            try {
               int oldLine = source.getLineno();
               do {
                   ch = source.read();
               } while (oldLine == source.getLineno());
            } catch (Exception e) {
                    atEOF = true;
            }
            return nextToken();
        }
        Symbol sym = Symbol.symbol(s,Tokens.BogusToken); // be sure it's a valid token
        if (sym == null) {
             System.out.println("******** illegal character: " + s);
             atEOF = true;
             return nextToken();
        }
        return new Token(startPosition,endPosition,lineNumber,sym);
        }

/**
 *  @return the next Token found in the source file
*/
    public Token nextToken() { // ch is always the next char to process
        if (atEOF) {
            if (source != null) {
                source.close();
                source = null;
            }
            return null;
        }
        try {
            while (Character.isWhitespace(ch)) {  // scan past whitespace
                ch = source.read();
            }
        } catch (Exception e) {
            atEOF = true;
            return nextToken();
        }
        startPosition = source.getPosition();
        endPosition = startPosition - 1;
        lineNumber = source.getLineno();

        if (Character.isJavaIdentifierStart(ch)) {
            // return tokens for ids and reserved words, checking for appropriate REGEX pattern:
            String id = "";
            try {
                do {
                    endPosition++;
                    id += ch;
                    ch = source.read();
                } while (Character.isJavaIdentifierPart(ch));
            } catch (Exception e) {
                atEOF = true;
            }
            if (!id.matches("[A-Za-z][A-Za-z0-9]*")) {
                System.out.println("******** illegal identifier: " + id);
                atEOF = true;
                return nextToken();
           } else
            return newIdToken(id,startPosition,endPosition,lineNumber);
        }
        if (Character.isDigit(ch)) {
            // return int and float tokens:
            String number = "";
            boolean isFloat = false;
            try {
                do {
                	//flags the presence of a possible float, avoids having multiple '.' in one number:
                    if ((ch == '.') && source.mightBeFloat()) {
                    	isFloat = true;
                    }
                    endPosition++;
                    number += ch;
                    ch = source.read();
                    //This proceeds if ch is an integer, or the first decimal after the leading number:
                } while (Character.isDigit(ch) || ((ch == '.') && (!isFloat) && (endPosition >= startPosition)));

            } catch (Exception e) {
                atEOF = true;
            }
            if (isFloat) {
            	return newFloatToken(number,startPosition,endPosition,lineNumber);
            } else {
            return newNumberToken(number,startPosition,endPosition,lineNumber);
            
            }
        }
        
        // At this point the only tokens to check for are one or two
        // characters; we must also check for comments that begin with
        // 2 --'s
        String charOld = "" + ch;
        String op = charOld;
        Symbol sym;
        try {
            endPosition++;
            ch = source.read();
            op += ch;
            // check if valid 2 char operator; if it's not in the symbol
            // table then don't insert it since we really have a one char
            // token
            sym = Symbol.symbol(op, Tokens.BogusToken); 
            if (sym == null) {  // it must be a one char token
                return makeToken(charOld,startPosition,endPosition,lineNumber);
            }
            endPosition++;
            ch = source.read();
            return makeToken(op,startPosition,endPosition,lineNumber);
        } catch (Exception e) {}
        atEOF = true;
        if (startPosition == endPosition) {
            op = charOld;
        }
        return makeToken(op,startPosition,endPosition,lineNumber);
    }
    
}