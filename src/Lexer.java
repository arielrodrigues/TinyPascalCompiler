/* The following code was generated by JFlex 1.3.5 on 12/03/17 21:50 */

import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.ComplexSymbolFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java_cup.runtime.Symbol;
import java.lang.*;
import java.io.FileInputStream;


/**
 * This class is a scanner generated by
 * <a href="http://www.jflex.de/">JFlex</a> 1.3.5
 * on 12/03/17 21:50 from the specification file
 * <tt>file:/home/ariel/EclipseProjects/marotagem/lexer.jflex</tt>
 */
public class Lexer implements sym, java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  final public static int YYEOF = -1;

  /** initial size of the lookahead buffer */
  final private static int YY_BUFFERSIZE = 16384;

  /** lexical states */
  final public static int CODESEG = 0;
  final public static int YYINITIAL = 0;

  /**
   * Translates characters to character classes
   */
  final private static String yycmap_packed =
          "\11\0\1\1\1\1\1\0\1\1\1\1\22\0\1\31\2\30\1\32"+
                  "\3\30\1\27\1\17\1\20\1\15\1\13\1\6\1\14\1\5\1\16"+
                  "\1\25\11\4\1\7\1\10\1\23\1\12\1\24\2\30\1\3\1\3"+
                  "\1\3\1\3\1\26\1\3\1\3\1\3\1\3\1\3\1\3\1\3"+
                  "\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3"+
                  "\1\3\1\3\1\3\1\3\1\21\1\30\1\22\1\11\2\30\1\3"+
                  "\1\3\1\3\1\3\1\26\1\3\1\3\1\3\1\3\1\3\1\3"+
                  "\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3"+
                  "\1\3\1\3\1\3\1\3\1\3\1\33\1\2\1\34\202\30\uff00\0";

  /**
   * Translates characters to character classes
   */
  final private static char [] yycmap = yy_unpack_cmap(yycmap_packed);

  /**
   * Translates a state to a row index in the transition table
   */
  final private static int yy_rowMap [] = {
          0,    29,    29,    58,    58,    87,   116,    29,   145,    29,
          29,    29,    29,    29,    29,    29,   174,    29,    29,    29,
          203,   232,   261,   290,   319,   348,   377,    29,    29,   319,
          29,    29,    29,   261,   406,   406,   435,    29,   464,   464,
          493,   522,   522,   551,   580,   319,   609,   638
  };

  /**
   * The packed transition table of the DFA (part 0)
   */
  final private static String yy_packed0 =
          "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
                  "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21"+
                  "\1\22\1\23\1\24\1\25\1\26\1\27\1\5\1\30"+
                  "\1\2\1\3\1\2\1\31\1\2\37\0\3\5\20\0"+
                  "\2\5\10\0\1\32\1\0\1\6\1\33\17\0\1\6"+
                  "\1\32\13\0\1\34\41\0\1\35\37\0\1\36\31\0"+
                  "\1\37\11\0\1\40\22\0\1\41\24\0\1\32\1\0"+
                  "\1\42\1\33\17\0\1\42\1\32\10\0\25\43\1\44"+
                  "\5\43\15\36\1\45\16\36\1\46\4\0\1\47\6\0"+
                  "\2\50\10\0\1\47\13\0\1\51\20\0\1\51\11\0"+
                  "\25\52\1\53\5\52\15\36\1\54\2\36\1\46\13\36"+
                  "\5\0\1\47\20\0\1\47\11\0\1\32\1\0\1\51"+
                  "\20\0\1\51\1\32\10\0\25\52\1\55\5\52\15\36"+
                  "\1\54\2\36\1\56\13\36\1\46\2\0\25\52\1\55"+
                  "\2\52\1\57\2\52\2\0\2\52\1\60\20\52\1\55"+
                  "\1\52\1\55\5\52\2\0\2\52\1\60\20\52\1\60"+
                  "\1\52\1\55\2\52\1\57\2\52";

  /**
   * The transition table of the DFA
   */
  final private static int yytrans [] = yy_unpack();


  /* error codes */
  final private static int YY_UNKNOWN_ERROR = 0;
  final private static int YY_ILLEGAL_STATE = 1;
  final private static int YY_NO_MATCH = 2;
  final private static int YY_PUSHBACK_2BIG = 3;

  /* error messages for the codes above */
  final private static String YY_ERROR_MSG[] = {
          "Unkown internal scanner error",
          "Internal error: unknown state",
          "Error: could not match input",
          "Error: pushback value was too large"
  };

  /**
   * YY_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private final static byte YY_ATTRIBUTE[] = {
          0,  9,  9,  1,  1,  1,  1,  9,  1,  9,  9,  9,  9,  9,  9,  9,
          1,  9,  9,  9,  1,  1,  1,  1,  1,  0,  0,  9,  9,  0,  9,  9,
          9,  1,  0,  1,  0,  9,  1,  0,  1,  0,  1,  0,  1,  1,  0,  1
  };

  /** the input device */
  private java.io.Reader yy_reader;

  /** the current state of the DFA */
  private int yy_state;

  /** the current lexical state */
  private int yy_lexical_state = YYINITIAL;

  /** this buffer contains the current text to be matched and is
   the source of the yytext() string */
  private char yy_buffer[] = new char[YY_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int yy_markedPos;

  /** the textposition at the last state to be included in yytext */
  private int yy_pushbackPos;

  /** the current text position in the buffer */
  private int yy_currentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int yy_startRead;

  /** endRead marks the last character in the buffer, that has been read
   from input */
  private int yy_endRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the
   * matched text
   */
  private int yycolumn;

  /**
   * yy_atBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean yy_atBOL = true;

  /** yy_atEOF == true <=> the scanner is at the EOF */
  private boolean yy_atEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean yy_eof_done;

  /* user code: */
  public Lexer(ComplexSymbolFactory sf, FileInputStream is) {
    this(is);
    symbolFactory = sf;
  }
  public Lexer(ComplexSymbolFactory sf, java.io.Reader reader) {
    this(reader);
    symbolFactory = sf;
  }

  private StringBuffer sb;
  private ComplexSymbolFactory symbolFactory;
  private int csline,cscolumn;

  private Symbol symbol(String name, int code) {
    return symbolFactory.newSymbol(name, code,
            new Location(yyline+1, yycolumn+1, yychar),
            new Location(yyline+1, yycolumn+yylength(), yychar+yylength()));
  }
  public Symbol symbol(String nome, int code, Integer lexem){
    return symbolFactory.newSymbol(nome, code,
            new Location(yyline+1, yycolumn +1, yychar),
            new Location(yyline+1,yycolumn+yylength(), yychar+yylength()), lexem);
  }
  public Symbol symbol(String nome, int code, Double lexem){
    return symbolFactory.newSymbol(nome, code,
            new Location(yyline+1, yycolumn +1, yychar),
            new Location(yyline+1,yycolumn+yylength(), yychar+yylength()), lexem);
  }
  public Symbol symbol(String nome, int code, String lexem){
    return symbolFactory.newSymbol(nome, code,
            new Location(yyline+1, yycolumn +1, yychar),
            new Location(yyline+1,yycolumn+yylength(), yychar+yylength()), lexem);
  }

  protected void emit_warning(String message){
    System.out.println("scanner warning: " + message + " at : 2 "+
            (yyline+1) + " " + (yycolumn+1) + " " + yychar);
  }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexer(java.io.Reader in) {
    this.yy_reader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /**
   * Unpacks the split, compressed DFA transition table.
   *
   * @return the unpacked transition table
   */
  private static int [] yy_unpack() {
    int [] trans = new int[667];
    int offset = 0;
    offset = yy_unpack(yy_packed0, offset, trans);
    return trans;
  }

  /**
   * Unpacks the compressed DFA transition table.
   *
   * @param packed   the packed transition table
   * @return         the index of the last entry
   */
  private static int yy_unpack(String packed, int offset, int [] trans) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do trans[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] yy_unpack_cmap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 180) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception IOException  if any I/O-Error occurs
   */
  private boolean yy_refill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (yy_startRead > 0) {
      System.arraycopy(yy_buffer, yy_startRead,
              yy_buffer, 0,
              yy_endRead-yy_startRead);

      /* translate stored positions */
      yy_endRead-= yy_startRead;
      yy_currentPos-= yy_startRead;
      yy_markedPos-= yy_startRead;
      yy_pushbackPos-= yy_startRead;
      yy_startRead = 0;
    }

    /* is the buffer big enough? */
    if (yy_currentPos >= yy_buffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[yy_currentPos*2];
      System.arraycopy(yy_buffer, 0, newBuffer, 0, yy_buffer.length);
      yy_buffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = yy_reader.read(yy_buffer, yy_endRead,
            yy_buffer.length-yy_endRead);

    if (numRead < 0) {
      return true;
    }
    else {
      yy_endRead+= numRead;
      return false;
    }
  }


  /**
   * Closes the input stream.
   */
  final public void yyclose() throws java.io.IOException {
    yy_atEOF = true;            /* indicate end of file */
    yy_endRead = yy_startRead;  /* invalidate buffer    */

    if (yy_reader != null)
      yy_reader.close();
  }


  /**
   * Closes the current stream, and resets the
   * scanner to read from a new input stream.
   *
   * All internal variables are reset, the old input stream
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>YY_INITIAL</tt>.
   *
   * @param reader   the new input stream
   */
  final public void yyreset(java.io.Reader reader) throws java.io.IOException {
    yyclose();
    yy_reader = reader;
    yy_atBOL  = true;
    yy_atEOF  = false;
    yy_endRead = yy_startRead = 0;
    yy_currentPos = yy_markedPos = yy_pushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    yy_lexical_state = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  final public int yystate() {
    return yy_lexical_state;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  final public void yybegin(int newState) {
    yy_lexical_state = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  final public String yytext() {
    return new String( yy_buffer, yy_startRead, yy_markedPos-yy_startRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  final public char yycharat(int pos) {
    return yy_buffer[yy_startRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  final public int yylength() {
    return yy_markedPos-yy_startRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void yy_ScanError(int errorCode) {
    String message;
    try {
      message = YY_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = YY_ERROR_MSG[YY_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  private void yypushback(int number)  {
    if ( number > yylength() )
      yy_ScanError(YY_PUSHBACK_2BIG);

    yy_markedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void yy_do_eof() throws java.io.IOException {
    if (!yy_eof_done) {
      yy_eof_done = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int yy_input;
    int yy_action;

    // cached fields:
    int yy_currentPos_l;
    int yy_startRead_l;
    int yy_markedPos_l;
    int yy_endRead_l = yy_endRead;
    char [] yy_buffer_l = yy_buffer;
    char [] yycmap_l = yycmap;

    int [] yytrans_l = yytrans;
    int [] yy_rowMap_l = yy_rowMap;
    byte [] yy_attr_l = YY_ATTRIBUTE;

    while (true) {
      yy_markedPos_l = yy_markedPos;

      yychar+= yy_markedPos_l-yy_startRead;

      boolean yy_r = false;
      for (yy_currentPos_l = yy_startRead; yy_currentPos_l < yy_markedPos_l;
           yy_currentPos_l++) {
        switch (yy_buffer_l[yy_currentPos_l]) {
          case '\u000B':
          case '\u000C':
          case '\u0085':
          case '\u2028':
          case '\u2029':
            yyline++;
            yycolumn = 0;
            yy_r = false;
            break;
          case '\r':
            yyline++;
            yycolumn = 0;
            yy_r = true;
            break;
          case '\n':
            if (yy_r)
              yy_r = false;
            else {
              yyline++;
              yycolumn = 0;
            }
            break;
          default:
            yy_r = false;
            yycolumn++;
        }
      }

      if (yy_r) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean yy_peek;
        if (yy_markedPos_l < yy_endRead_l)
          yy_peek = yy_buffer_l[yy_markedPos_l] == '\n';
        else if (yy_atEOF)
          yy_peek = false;
        else {
          boolean eof = yy_refill();
          yy_markedPos_l = yy_markedPos;
          yy_buffer_l = yy_buffer;
          if (eof)
            yy_peek = false;
          else
            yy_peek = yy_buffer_l[yy_markedPos_l] == '\n';
        }
        if (yy_peek) yyline--;
      }
      yy_action = -1;

      yy_startRead_l = yy_currentPos_l = yy_currentPos =
              yy_startRead = yy_markedPos_l;

      yy_state = yy_lexical_state;


      yy_forAction: {
        while (true) {

          if (yy_currentPos_l < yy_endRead_l)
            yy_input = yy_buffer_l[yy_currentPos_l++];
          else if (yy_atEOF) {
            yy_input = YYEOF;
            break yy_forAction;
          }
          else {
            // store back cached positions
            yy_currentPos  = yy_currentPos_l;
            yy_markedPos   = yy_markedPos_l;
            boolean eof = yy_refill();
            // get translated positions and possibly new buffer
            yy_currentPos_l  = yy_currentPos;
            yy_markedPos_l   = yy_markedPos;
            yy_buffer_l      = yy_buffer;
            yy_endRead_l     = yy_endRead;
            if (eof) {
              yy_input = YYEOF;
              break yy_forAction;
            }
            else {
              yy_input = yy_buffer_l[yy_currentPos_l++];
            }
          }
          int yy_next = yytrans_l[ yy_rowMap_l[yy_state] + yycmap_l[yy_input] ];
          if (yy_next == -1) break yy_forAction;
          yy_state = yy_next;

          int yy_attributes = yy_attr_l[yy_state];
          if ( (yy_attributes & 1) == 1 ) {
            yy_action = yy_state;
            yy_markedPos_l = yy_currentPos_l;
            if ( (yy_attributes & 8) == 8 ) break yy_forAction;
          }

        }
      }

      // store back cached position
      yy_markedPos = yy_markedPos_l;

      switch (yy_action) {

        case 32:
        {  return symbol("GE", GE);  }
        case 49: break;
        case 30:
        {  return symbol("LE", LE);  }
        case 50: break;
        case 21:
        {  return symbol("GT", GT);  }
        case 51: break;
        case 20:
        {  return symbol("LT", LT);  }
        case 52: break;
        case 4:
        {  return symbol("ID", ID);  }
        case 53: break;
        case 1:
        case 23:
        case 24:
        {  emit_warning("Caracter não reconhecido " + yytext() + " -- ignorado");  }
        case 54: break;
        case 33:
        case 38:
        case 40:
        {  return symbol("NUMREAL", NUMREAL, Double.parseDouble(yytext()));  }
        case 55: break;
        case 5:
        case 22:
        {  return symbol("NUMINT", NUMINT, Integer.parseInt(yytext()));  }
        case 56: break;
        case 44:
        case 47:
        {  return symbol("STR", STR);  }
        case 57: break;
        case 35:
        case 42:
        {  return symbol("CHR", CHR);  }
        case 58: break;
        case 6:
        {  return symbol("DOT", DOT);  }
        case 59: break;
        case 14:
        {  return symbol("MULTIPLY", MULTIPLY);  }
        case 60: break;
        case 28:
        {  return symbol("ASSIGN", ASSIGN);  }
        case 61: break;
        case 31:
        {  return symbol("DIFF", DIFF);  }
        case 62: break;
        case 19:
        {  return symbol("RBRA", RBRA);  }
        case 63: break;
        case 12:
        {  return symbol("PLUS", PLUS);  }
        case 64: break;
        case 16:
        {  return symbol("LPAR", LPAR);  }
        case 65: break;
        case 17:
        {  return symbol("RPAR", RPAR);  }
        case 66: break;
        case 18:
        {  return symbol("LBRA", LBRA);  }
        case 67: break;
        case 2:
        case 3:
        {   }
        case 68: break;
        case 37:
        case 45:
        {  return symbol("COMMENT", COMMENT);  }
        case 69: break;
        case 13:
        {  return symbol("MINUS", MINUS);  }
        case 70: break;
        case 27:
        {  return symbol("DOUBLEDOT", DOUBLEDOT);  }
        case 71: break;
        case 15:
        {  return symbol("DIVIDE", DIVIDE);  }
        case 72: break;
        case 11:
        {  return symbol("EQUAL", EQUAL);  }
        case 73: break;
        case 9:
        {  return symbol("SEMICOLON", SEMICOLON);  }
        case 74: break;
        case 8:
        {  return symbol("COLON", COLON);  }
        case 75: break;
        case 7:
        {  return symbol("COMMA", COMMA);  }
        case 76: break;
        case 10:
        {  return symbol("CARET", CARET);  }
        case 77: break;
        default:
          if (yy_input == YYEOF && yy_startRead == yy_currentPos) {
            yy_atEOF = true;
            yy_do_eof();
            {     return symbol("EOF", sym.EOF);
            }
          }
          else {
            yy_ScanError(YY_NO_MATCH);
          }
      }
    }
  }


}
