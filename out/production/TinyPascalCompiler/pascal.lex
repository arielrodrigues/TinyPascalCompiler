package	LexicalAnalyzer;
%%

%public
%class LexicalAnalyzer
%line
%column


%{

private void newSymbol(String nome) {
    System.out.println(nome);
}

private void newSymbol(String nome, String lexema) {
    System.out.println(nome);
}

private void newSymbol(String nome, String lexema, Object value) {
    System.out.println(nome);
}

%}

/* Basic */
LineTerminator = [\n|\r|\r\n]
WhiteSpace = {LineTerminator}|[\t|\f| ]
Letter = [a-z|A-Z]
Digit = [0-9]

/* Symbols */
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
SingleQuote = \'
DoubleQuote = \"
NQUOTE = [^']

/* Types */
NumInt = 0|[1-9][0-9]*
NumReal = {Digit}+(\.{Digit}+)?([E|e](\+|-)?{Digit}+)?
Chr = (\'{NQUOTE}\')
Str = (\'{NQUOTE}+\')
Comment =  (\(\*|\{)([^\*\}]|\*+[^\)\}])*(\*+\)|\})

/* Reserved words */
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
Real = [Rr][Ee][Aa][Ll]
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

Id = {Letter}({Letter}|{Digit})*

%eofval{
     newSymbol("EOF");
%eofval}
%state CODESEG
%%

    <YYINITIAL> {
    /* Symbols */
    {WhiteSpace}                 { }
    {Dot}			             {  newSymbol("DOT"); }
    {DoubleDot}					 {  newSymbol("DOUBLEDOT"); }
    {Comma}		        	     {  newSymbol("COMMA"); }
    {Colon}			             {  newSymbol("COLON"); }
    {Semicolon}		             {  newSymbol("SEMICOLON"); }
    {SingleQuote}                {  newSymbol("SINGLEQUOTE"); }
    {DoubleQuote}                {  newSymbol("DOUBLEQUOTE"); }
    {Caret}						 {  newSymbol("CARET"); }
    {Assign}                     {  newSymbol("ASSIGN"); }
    {Plus}                       {  newSymbol("PLUS"); }
    {Minus}			             {  newSymbol("MINUS"); }
    {Multiply}      		     {  newSymbol("MULTIPLY"); }
    {Divide}        		     {  newSymbol("DIVIDE"); }
    {LPar}	        		     {  newSymbol("LPAR"); }
    {RPar}	        		     {  newSymbol("RPAR"); }
    {LBra}		        	     {  newSymbol("LBRA"); }
    {RBra}      			     {  newSymbol("RBRA"); }
    {LT}        			     {  newSymbol("LT"); }
    {LE}	        		     {  newSymbol("LE"); }
    {GT}        			     {  newSymbol("GT"); }
    {GE}        			     {  newSymbol("GE"); }
    {Equal}		        	     {  newSymbol("EQUAL"); }
    {Diff}			             {  newSymbol("DIFF"); }

    /* Types */
    {NumInt}                     {  newSymbol("NUMINT"nteger.parseInt(yytext())); }
    {NumReal}					 {  newSymbol("NUMREAL"ouble.parseDouble(yytext())); }
    {Real}						 {  newSymbol("REAL"); }
    {Boolean}                    {  newSymbol("BOOLEAN"); }
    {Integer}                    {  newSymbol("INTEGER"); }
    {Chr}						 {  newSymbol("CHR"); }
    {Str}              			 {  newSymbol("STRINGCHARACTER"); }
    {Comment}					 {  newSymbol("COMMENT"); }
    {Sign}						 {  newSymbol("SIGN"); }

    /* Reserved words */
    {And}						 {  newSymbol("AND"); }
    {Array}						 {  newSymbol("ARRAY"); }
    {Asm}						 {  newSymbol("ASM"); }
    {Begin}						 {  newSymbol("BEGIN"); }
    {Case}						 {  newSymbol("CASE"); }
    {Char}                       {  newSymbol("CHAR"); }
    {Const}						 {  newSymbol("CONST"); }
    {Constructor}				 {  newSymbol("CONSTRUCTOR"); }
    {Destructor}				 {  newSymbol("DESTRUCTOR"); }
    {Div}						 {  newSymbol("DIV"); }
    {Do}						 {  newSymbol("DO"); }
    {Downto}					 {  newSymbol("DOWNTO"); }
    {Else}						 {  newSymbol("ELSE"); }
    {End}						 {  newSymbol("END"); }
    {False}                      {  newSymbol("FALSE"); }
    {File}						 {  newSymbol("FILE"); }
    {For}						 {  newSymbol("FOR"); }
    {Foward}					 {  newSymbol("FOWARD"); }
    {Function}					 {  newSymbol("FUNCTION"); }
    {Goto}						 {  newSymbol("GOTO"); }
    {If}                         {  newSymbol("IF"); }
    {Implementation}			 {  newSymbol("IMPLEMENTATION"); }
    {In}						 {  newSymbol("IN"); }
    {Inline}					 {  newSymbol("INLINE"); }
    {Interface}					 {  newSymbol("INTERFACE"); }
    {Label}						 {  newSymbol("LABEL"); }
    {Mod}						 {  newSymbol("MOD"); }
    {Nil}						 {  newSymbol("NIL"); }
    {Not}						 {  newSymbol("NOT"); }
    {Object}					 {  newSymbol("OBJECT"); }
    {Of}						 {  newSymbol("OF"); }
    {Or}                         {  newSymbol("OR"); }
    {Packed}					 {  newSymbol("PACKED"); }
    {Procedure}					 {  newSymbol("PROCEDURE"); }
    {Program}					 {  newSymbol("PROGRAM"); }
    {Record}					 {  newSymbol("RECORD"); }
    {Repeat}					 {  newSymbol("REPEAT"); }
    {Set}						 {  newSymbol("SET"); }
    {Shl}						 {  newSymbol("SHL"); }
    {Shr}						 {  newSymbol("SHR"); }
    {String}					 {  newSymbol("STRING"); }
    {Then}                       {  newSymbol("THEN"); }
    {To}						 {  newSymbol("TO"); }
    {True}                       {  newSymbol("TRUE"); }
    {Type}						 {  newSymbol("TYPE"); }
    {Unit}						 {  newSymbol("UNIT"); }
    {Until}						 {  newSymbol("UNTIL"); }
    {Uses}						 {  newSymbol("USES"); }
    {Var}						 {  newSymbol("VAR"); }
    {While}						 {  newSymbol("WHILE"); }
    {With}						 {  newSymbol("WITH"); }
    {Xor}						 {  newSymbol("XOR"); }

    {Id}                         {  newSymbol("ID"); }
}

// error warning
.|\n						 { System.out.println("Caracter não reconhecido " + yytext() + " -- ignorado"); }
