package	LexicalAnalyzer;
%%

%{

private void imprimir(String descricao, String lexema) {
    System.out.print(lexema + " - " + descricao);
}

private void printTeste00(String lexema) {
    System.out.print('<'+lexema+'>');
}

private void printTeste01(String token, String lexema) {
    System.out.print('<'+token+", "+lexema+'>');
}

private void printTeste02(String token, String lexema) {
    System.out.print('<'+token+", "+lexema+", "+Integer.parseInt(lexema)+'>');
}

%}


%class LexicalAnalyzer
%type void


BRANCO = [\n| |\t|\r]
ID = [_|a-z|A-Z][a-z|A-Z|0-9|_]*
SOMA = "+"
ATRIBUICAO = ":="
INTEIRO = 0|[1-9][0-9]*

%%

"if"                         { printTeste01("ID", yytext()); }
"then"                       { printTeste01("ID", yytext()); }
{BRANCO}                     { }
{ID}                         { printTeste01(yytext(), yytext()); }
{SOMA}                       { printTeste00("PLUS"); }
{ATRIBUICAO}                 { printTeste00("ASSIGN"); }
{INTEIRO}                    { printTeste02("NUMINT", yytext()); }

. { throw new RuntimeException("Caractere inválido " + yytext()); }