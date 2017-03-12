package	LexicalAnalyzer;
%%

%public
%class LexicalAnalyzer
%type Symbol
%line
%column


%{

private Symbol newSymbol(String nome) {
    return new Symbol(nome, yyline+1, yycolumn);
}

private Symbol newSymbol(String nome, String lexema) {
    return new Symbol(nome, lexema, yyline+1, yycolumn);
}

private Symbol newSymbol(String nome, String lexema, Object value) {
    return new Symbol(nome, lexema, value, yyline+1, yycolumn);
}

%}

// Basic
LineTerminator = [\n|\r|\r\n]
WhiteSpace = {LineTerminator}|[\t|\f| ]
Letter = [a-z|A-Z]
Digit = [0-9]

// Symbols
Dot = "."
DoubleDot = ".."
Comma = ","
Colon = ":"
Semicolon = ";"
Caret = "^"
Assign = ":="
Plus = "+"
Minus = "-"
Multiply = "*"
Divide = "/"
LPar = "("
RPar = ")"
LBra = "["
RBra = "]"
LT = "<"
LE = "<="
GT = ">"
GE = ">="
Equal = "="
Diff = "<>"
Sign = [+|-]


// Types
Id = {Letter}({Letter}|{Digit})*
NumInt = 0|[1-9][0-9]*
NumReal = {Digit}+(\.{Digit}+)?([E|e](\+|-)?{Digit}+)?
Chr = \'[ -ÿ]\'|\'\'
Str = (\'[ -ÿ][ -ÿ]+\')(#{NumInt})*
Comment =  (\(\*|\{)([^\*\}]|\*+[^\)\}])*(\*+\)|\})

// Reserved words
And = [Aa][Nn][Dd]
Array = [Aa][Rr][Rr][Aa][Yy]
Asm = [Aa][Ss][Mm]
Begin = [Bb][Ee][Gg][Ii][Nn]
Boolean = [Bb][Oo][Oo][Ll][Ee][Aa][Nn]
Case = [Cc][Aa][Ss][Ee]
Char = [Cc][Hh][Aa][Rr]
Const = [Cc][Oo][Nn][Ss][Tt]
Constructor = [Cc][Oo][Nn][Ss][Tt][Rr][Uu][Cc][Tt][Oo][Rr]
Destructor = [Dd][Ee][Ss][Tt][Rr][Uu][Cc][Tt][Oo][Rr]
Div = [Dd][Ii][Vv]
Do = [Dd][Oo]
Downto = [Dd][Oo][Ww][Nn][Tt][Oo]
Else = [Ee][Ll][Ss][Ee]
End = [Ee][Nn][Dd]
False = [Ff][Aa][Ll][Ss][Ee]
File = [Ff][Ii][Ll][Ee]
For = [Ff][Oo][Rr]
Foward = [Ff][Oo][Ww][Aa][Rr][Dd]
Function = [Ff][Uu][Nn][Cc][Tt][Ii][Oo][Nn]
Goto = [Gg][Oo][Tt][Oo]
If = [Ii][Ff]
Implementation = [Ii][Mm][Pp][Ll][Ee][Mm][Ee][Nn][Tt][Aa][Tt][Ii][Oo][Nn]
In = [Ii][Nn]
Inline = [Ii][Nn][Ll][Ii][Nn][Ee]
Integer = [Ii][Nn][Tt][Ee][Gg][Ee][Rr]
Interface = [Ii][Nn][Tt][Ee][Rr][Ff][Aa][Cc][Ee]
Label = [Ll][Aa][Bb][Ee][Ll]
Mod = [Mm][Oo][Dd]
Nil = [Nn][Ii][Ll]
Not = [Nn][Oo][Tt]
Object = [Oo][Bb][Jj][Ee][Cc][Tt]
Of = [Oo][Ff]
Or = [Oo][Rr]
Packed = [Pp][Aa][Cc][Kk][Ee][Dd]
Procedure = [Pp][Rr][Oo][Cc][Ee][Dd][Uu][Rr][Ee]
Program = [Pp][Rr][Oo][Gg][Rr][Aa][Mm]
Record = [Rr][Ee][Cc][Oo][Rr][Dd]
Repeat = [Rr][Ee][Pp][Ee][Aa][Tt]
Set = [Ss][Ee][Tt]
Shl = [Ss][Hh][Ll]
Shr = [Ss][Hh][Rr]
String = [Ss][Tt][Rr][Ii][Nn][Gg]
Then = [Tt][Hh][Ee][Nn]
To = [Tt][Oo]
True = [Tt][Rr][Uu][Ee]
Type = [Tt][Yy][Pp][Ee]
Unit = [Uu][Nn][Ii][Tt]
Until = [Uu][Nn][Tt][Ii][Ll]
Uses = [Uu][Ss][Ee][Ss]
Var = [Vv][Aa][Rr]
While = [Ww][Hh][Ii][Ll][Ee]
With = [Ww][Ii][Tt][Hh]
Xor = [Xx][Oo][Rr]


%%

{WhiteSpace}                 { }
{Dot}			             { return newSymbol("DOT"); }
{DoubleDot}					 { return newSymbol("DOUBLEDOT"); }
{Comma}		        	     { return newSymbol("COMMA"); }
{Colon}			             { return newSymbol("COLON"); }
{Semicolon}		             { return newSymbol("SEMICOLON"); }
{Caret}						 { return newSymbol("CARET"); }
{Assign}                     { return newSymbol("ASSIGN"); }
{Plus}                       { return newSymbol("PLUS"); }
{Minus}			             { return newSymbol("MINUS"); }
{Multiply}      		     { return newSymbol("MULTIPLY"); }
{Divide}        		     { return newSymbol("DIVIDE"); }
{LPar}	        		     { return newSymbol("LPAR"); }
{RPar}	        		     { return newSymbol("RPAR"); }
{LBra}		        	     { return newSymbol("LBRA"); }
{RBra}      			     { return newSymbol("RBRA"); }
{LT}        			     { return newSymbol("LT"); }
{LE}	        		     { return newSymbol("LE"); }
{GT}        			     { return newSymbol("GT"); }
{GE}        			     { return newSymbol("GE"); }
{Equal}		        	     { return newSymbol("EQUAL"); }
{Diff}			             { return newSymbol("DIFF"); }

{And}						 { return newSymbol("AND"); }
{Array}						 { return newSymbol("Array"); }
{Asm}						 { return newSymbol("ASM"); }
{Begin}						 { return newSymbol("BEGIN"); }
{Boolean}                    { return newSymbol("BOOLEAN"); }
{Case}						 { return newSymbol("CASE"); }
{Char}                       { return newSymbol("CHAR"); }
{Const}						 { return newSymbol("CONST"); }
{Constructor}				 { return newSymbol("CONSTRUCTOR"); }
{Destructor}				 { return newSymbol("DESTRUCTOR"); }
{Div}						 { return newSymbol("DIV"); }
{Do}						 { return newSymbol("DO"); }
{Downto}					 { return newSymbol("DOWNTO"); }
{Else}						 { return newSymbol("ELSE"); }
{End}						 { return newSymbol("END"); }
{False}                      { return newSymbol("FALSE"); }
{File}						 { return newSymbol("FILE"); }
{For}						 { return newSymbol("FOR"); }
{Foward}					 { return newSymbol("FOWARD"); }
{Function}					 { return newSymbol("FUNCTION"); }
{Goto}						 { return newSymbol("GOTO"); }
{If}                         { return newSymbol("IF"); }
{Implementation}			 { return newSymbol("IMPLEMENTATION"); }
{In}						 { return newSymbol("IN"); }
{Inline}					 { return newSymbol("INLINE"); }
{Interface}					 { return newSymbol("INTERFACE"); }
{Integer}                    { return newSymbol("INTEGER"); }
{Label}						 { return newSymbol("LABEL"); }
{Mod}						 { return newSymbol("MOD"); }
{Nil}						 { return newSymbol("NIL"); }
{Not}						 { return newSymbol("NOT"); }
{Object}					 { return newSymbol("OBJECT"); }
{Of}						 { return newSymbol("OF"); }
{Or}                         { return newSymbol("OR"); }
{Packed}					 { return newSymbol("PACKED"); }
{Procedure}					 { return newSymbol("PROCEDURE"); }
{Program}					 { return newSymbol("PROGRAM"); }
{Record}					 { return newSymbol("RECORD"); }
{Repeat}					 { return newSymbol("REPEAT"); }
{Set}						 { return newSymbol("SET"); }
{Shl}						 { return newSymbol("SHL"); }
{Shr}						 { return newSymbol("SHR"); }
{String}					 { return newSymbol("STRING"); }
{Then}                       { return newSymbol("THEN"); }
{To}						 { return newSymbol("TO"); }
{True}                       { return newSymbol("TRUE"); }
{Type}						 { return newSymbol("TYPE"); }
{Unit}						 { return newSymbol("UNIT"); }
{Until}						 { return newSymbol("UNTIL"); }
{Uses}						 { return newSymbol("USES"); }
{Var}						 { return newSymbol("VAR"); }
{While}						 { return newSymbol("WHILE"); }
{With}						 { return newSymbol("WITH"); }
{Xor}						 { return newSymbol("XOR"); }

{Id}                         { return newSymbol("ID", yytext()); }
{NumInt}                     { return newSymbol("NUMINT", yytext(), Integer.parseInt(yytext())); }
{NumReal}					 { return newSymbol("NUMREAL", yytext(), Double.parseDouble(yytext())); }
{Chr}						 { return newSymbol("CHR", yytext()); }
{Str}   					 { return newSymbol("STR", yytext()); }
{Comment}					 { return newSymbol("COMMENT", yytext()); }
{Sign}						 { return newSymbol("SIGN"); }


. { throw new RuntimeException("Caracter não reconhecido " + yytext() +
 ". Linha: " + yyline+1 + ", Coluna: " + yycolumn); }