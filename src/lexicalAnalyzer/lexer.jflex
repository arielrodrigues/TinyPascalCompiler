package lexicalAnalyzer;

import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.ComplexSymbolFactory;
import java.io.IOException;
import java_cup.runtime.Symbol;
import java.lang.*;
import syntacticAnalyzer.sym;
import java.io.FileInputStream;

%%

%class Lexer
%implements sym
%public
%unicode
%line
%column
%cup
%char
%{
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
    public Symbol symbol(String nome, int code, char lexem){
      return symbolFactory.newSymbol(nome, code,
              new Location(yyline+1, yycolumn +1, yychar),
              new Location(yyline+1,yycolumn+yylength(), yychar+yylength()), lexem);
    }
    protected void emit_warning(String message){
        System.out.println("lexicalAnalyzer warning: " + message + " at : 2 "+
    			(yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
%}

/* Basic */
LineTerminator = [\n|\r|\r\n]
Comment =  (\(\*|\{)([^\*\}]|\*+[^\)\}])*(\*+\)|\})
WhiteSpace = {LineTerminator}|[\t|\f| ]
Ignore = {WhiteSpace}|{Comment}
Letter = [a-z|A-Z]
Digit = [0-9]

/* Arithmetic operators */
Plus = "+"
Minus = "-"
Multiply = "*"
Divide = "/"
Div = [Dd][Ii][Vv]
Mod = [Mm][Oo][Dd]
Shl = [Ss][Hh][Ll]
Shr = [Ss][Hh][Rr]

/* Relational operators */
LT = "<"
LE = "<="
GT = ">"
GE = ">="
Equal = "="
Diff = "<>"

/* Logic operators */
And = [Aa][Nn][Dd]
Or = [Oo][Rr]
Not = [Nn][Oo][Tt]

/* Symbols */
Dot = "."
DoubleDot = ".."
Comma = ","
Colon = ":"
Semicolon = ";"
Caret = "^"
Assign = ":="
LPar = "("
RPar = ")"
LBra = "["
RBra = "]"
SingleQuote = \'
DoubleQuote = \"
NQUOTE = [^']

/* Types */
Chr = "'"{NQUOTE}"'"
True = [Tt][Rr][Uu][Ee]
False = [Ff][Aa][Ll][Ss][Ee]
NumInt = 0|[1-9][0-9]*
NumReal = {Digit}+(\.{Digit}+)?([E|e](\+|-)?{Digit}+)?
Str = "'"{NQUOTE}+"'"

/* Type definition */
Boolean = [Bb][Oo][Oo][Ll][Ee][Aa][Nn]
Char = [Cc][Hh][Aa][Rr]
Integer = [Ii][Nn][Tt][Ee][Gg][Ee][Rr]
Real = [Rr][Ee][Aa][Ll]
String = [Ss][Tt][Rr][Ii][Nn][Gg]

/* Reserved words */
Array = [Aa][Rr][Rr][Aa][Yy]
Asm = [Aa][Ss][Mm]
Begin = [Bb][Ee][Gg][Ii][Nn]
Case = [Cc][Aa][Ss][Ee]
Const = [Cc][Oo][Nn][Ss][Tt]
Constructor = [Cc][Oo][Nn][Ss][Tt][Rr][Uu][Cc][Tt][Oo][Rr]
Destructor = [Dd][Ee][Ss][Tt][Rr][Uu][Cc][Tt][Oo][Rr]
Do = [Dd][Oo]
Downto = [Dd][Oo][Ww][Nn][Tt][Oo]
Else = [Ee][Ll][Ss][Ee]
End = [Ee][Nn][Dd]
File = [Ff][Ii][Ll][Ee]
For = [Ff][Oo][Rr]
Foward = [Ff][Oo][Ww][Aa][Rr][Dd]
Function = [Ff][Uu][Nn][Cc][Tt][Ii][Oo][Nn]
Goto = [Gg][Oo][Tt][Oo]
If = [Ii][Ff]
Implementation = [Ii][Mm][Pp][Ll][Ee][Mm][Ee][Nn][Tt][Aa][Tt][Ii][Oo][Nn]
In = [Ii][Nn]
Inline = [Ii][Nn][Ll][Ii][Nn][Ee]
Interface = [Ii][Nn][Tt][Ee][Rr][Ff][Aa][Cc][Ee]
Label = [Ll][Aa][Bb][Ee][Ll]
Nil = [Nn][Ii][Ll]
Object = [Oo][Bb][Jj][Ee][Cc][Tt]
Of = [Oo][Ff]
Packed = [Pp][Aa][Cc][Kk][Ee][Dd]
Procedure = [Pp][Rr][Oo][Cc][Ee][Dd][Uu][Rr][Ee]
Program = [Pp][Rr][Oo][Gg][Rr][Aa][Mm]
Record = [Rr][Ee][Cc][Oo][Rr][Dd]
Repeat = [Rr][Ee][Pp][Ee][Aa][Tt]
Set = [Ss][Ee][Tt]
Then = [Tt][Hh][Ee][Nn]
To = [Tt][Oo]
Type = [Tt][Yy][Pp][Ee]
Unit = [Uu][Nn][Ii][Tt]
Until = [Uu][Nn][Tt][Ii][Ll]
Uses = [Uu][Ss][Ee][Ss]
Var = [Vv][Aa][Rr]
While = [Ww][Hh][Ii][Ll][Ee]
With = [Ww][Ii][Tt][Hh]
Xor = [Xx][Oo][Rr]

/* Id */
Id = {Letter}({Letter}|{Digit})*

/* eof */
%eofval{
    return symbol("EOF", sym.EOF);
%eofval}
%state CODESEG
%%

<YYINITIAL> {
    {Ignore}                     { }
    {Dot}			             { return symbol("DOT", DOT); }
    {DoubleDot}					 { return symbol("DOUBLEDOT", DOUBLEDOT); }
    {Comma}		        	     { return symbol("COMMA", COMMA); }
    {Colon}			             { return symbol("COLON", COLON); }
    {Semicolon}		             { return symbol("SEMICOLON", SEMICOLON); }
    {SingleQuote}                { return symbol("SINGLEQUOTE", SINGLEQUOTE); }
    {DoubleQuote}                { return symbol("DOUBLEQUOTE", DOUBLEQUOTE); }
    {Caret}						 { return symbol("CARET", CARET); }
    {Assign}                     { return symbol("ASSIGN", ASSIGN); }
    {Plus}                       { return symbol("PLUS", PLUS); }
    {Minus}			             { return symbol("MINUS", MINUS); }
    {Multiply}      		     { return symbol("TIMES", TIMES); }
    {Divide}        		     { return symbol("DIVIDE", DIVIDE); }
    {LPar}	        		     { return symbol("LPAR", LPAR); }
    {RPar}	        		     { return symbol("RPAR", RPAR); }
    {LBra}		        	     { return symbol("LBRA", LBRA); }
    {RBra}      			     { return symbol("RBRA", RBRA); }
    {LT}        			     { return symbol("LT", LT); }
    {LE}	        		     { return symbol("LE", LE); }
    {GT}        			     { return symbol("GT", GT); }
    {GE}        			     { return symbol("GE", GE); }
    {Equal}		        	     { return symbol("EQUAL", EQUAL); }
    {Diff}			             { return symbol("DIFF", DIFF); }

    {NumInt}                     { return symbol("NUMINT", NUMINT, Integer.parseInt(yytext())); }
    {NumReal}					 { return symbol("NUMREAL", NUMREAL, Double.parseDouble(yytext())); }
    {Real}						 { return symbol("REAL", REAL); }
    {Boolean}                    { return symbol("BOOLEAN", BOOLEAN); }
    {Integer}                    { return symbol("INTEGER", INTEGER); }
    {Chr}						 { return symbol("LITERALCHARACTER", LITERALCHARACTER, yytext().charAt(1)); }
    {Str}              			 { return symbol("STRINGCHARACTER", STRINGCHARACTER, yytext()); }

    {And}						 { return symbol("AND", AND); }
    {Array}						 { return symbol("ARRAY", ARRAY); }
    {Asm}						 { return symbol("ASM", ASM); }
    {Begin}						 { return symbol("BEGIN", BEGIN); }
    {Case}						 { return symbol("CASE", CASE); }
    {Char}                       { return symbol("CHAR", CHAR); }
    {Const}						 { return symbol("CONST", CONST); }
    {Constructor}				 { return symbol("CONSTRUCTOR", CONSTRUCTOR); }
    {Destructor}				 { return symbol("DESTRUCTOR", DESTRUCTOR); }
    {Div}						 { return symbol("DIV", DIV); }
    {Do}						 { return symbol("DO", DO); }
    {Downto}					 { return symbol("DOWNTO", DOWNTO); }
    {Else}						 { return symbol("ELSE", ELSE); }
    {End}						 { return symbol("END", END); }
    {False}                      { return symbol("FALSE", FALSE); }
    {File}						 { return symbol("FILE", FILE); }
    {For}						 { return symbol("FOR", FOR); }
    {Foward}					 { return symbol("FOWARD", FOWARD); }
    {Function}					 { return symbol("FUNCTION", FUNCTION); }
    {Goto}						 { return symbol("GOTO", GOTO); }
    {If}                         { return symbol("IF", IF); }
    {Implementation}			 { return symbol("IMPLEMENTATION", IMPLEMENTATION); }
    {In}						 { return symbol("IN", IN); }
    {Inline}					 { return symbol("INLINE", INLINE); }
    {Interface}					 { return symbol("INTERFACE", INTERFACE); }
    {Label}						 { return symbol("LABEL", LABEL); }
    {Mod}						 { return symbol("MOD", MOD); }
    {Nil}						 { return symbol("NIL", NIL); }
    {Not}						 { return symbol("NOT", NOT); }
    {Object}					 { return symbol("OBJECT", OBJECT); }
    {Of}						 { return symbol("OF", OF); }
    {Or}                         { return symbol("OR", OR); }
    {Packed}					 { return symbol("PACKED", PACKED); }
    {Procedure}					 { return symbol("PROCEDURE", PROCEDURE); }
    {Program}					 { return symbol("PROGRAM", PROGRAM); }
    {Record}					 { return symbol("RECORD", RECORD); }
    {Repeat}					 { return symbol("REPEAT", REPEAT); }
    {Set}						 { return symbol("SET", SET); }
    {Shl}						 { return symbol("SHL", SHL); }
    {Shr}						 { return symbol("SHR", SHR); }
    {String}					 { return symbol("STRING", STRING); }
    {Then}                       { return symbol("THEN", THEN); }
    {To}						 { return symbol("TO", TO); }
    {True}                       { return symbol("TRUE", TRUE); }
    {Type}						 { return symbol("TYPE", TYPE); }
    {Unit}						 { return symbol("UNIT", UNIT); }
    {Until}						 { return symbol("UNTIL", UNTIL); }
    {Uses}						 { return symbol("USES", USES); }
    {Var}						 { return symbol("VAR", VAR); }
    {While}						 { return symbol("WHILE", WHILE); }
    {With}						 { return symbol("WITH", WITH); }
    {Xor}						 { return symbol("XOR", XOR); }

    {Id}                         { return symbol("ID", ID, yytext()); }
}

// error warning
.|\n						 { emit_warning("Caracter não reconhecido '" + yytext() + "' -- ignorado"); }
