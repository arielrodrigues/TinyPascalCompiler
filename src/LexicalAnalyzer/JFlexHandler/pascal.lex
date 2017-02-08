package	LexicalAnalyzer;
%%

%public
%class LexicalAnalyzer


%{

private String symbol(String lexema) {
    System.out.print('<' + lexema + '>');
    return('<' + lexema + '>');
}

private String symbol(String token, String lexema) {
    System.out.print('<' + token + ", " + lexema + '>');
    return('<' + token + ", " + lexema + '>');
}

private String symbol(String token, String lexema, Object value) {
    System.out.print('<' + token + ", " + lexema + ", " + value + '>');
    return('<' + token + ", " + lexema + ", " + value + '>');
}

%}


LineTerminator = [\n|\r|\r\n]
WhiteSpace = {LineTerminator}|[\t|\f| ]
ID = [_|a-z|A-Z][a-z|A-Z|0-9|_]*
Plus = "+"
Assign = ":="
NumInt = 0|[1-9][0-9]*

%%

"if"                         { symbol("ID", yytext()); }
"then"                       { symbol("ID", yytext()); }
{WhiteSpace}                 { }
{ID}                         { symbol(yytext(), yytext()); }
{Plus}                       { symbol("PLUS"); }
{Assign}                     { symbol("ASSIGN"); }
{NumInt}                     { symbol("NUMINT", yytext(), Integer.parseInt(yytext())); }

. { throw new RuntimeException("Caractere inválido " + yytext()); }