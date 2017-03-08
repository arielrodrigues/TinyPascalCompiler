package LexicalAnalyzer;

public class Symbol {
    String nome, lexema;
    Integer line, column;
    Object value;

    Symbol(String nome, int yyline, int yycolumn) {
        this.nome = nome;
        this.line = yyline;
        this.column = yycolumn;
    }

    Symbol(String nome, String lexema, int yyline, int yycolumn) {
        this.nome = nome;
        this.lexema = lexema;
        this.line = yyline;
        this.column = yycolumn;
    }

    Symbol(String nome, String lexema, Object value, int yyline, int yycolumn) {
        this.nome = nome;
        this.lexema = lexema;
        this.value = value;
        this.line = yyline;
        this.column = yycolumn;
    }

    public String toString() {
        if (value != null && lexema != null) return ('<' + nome + ", " + lexema + ", " + value + '>');
        else if (lexema != null) return ('<' + nome + ", " + lexema + '>');
        else return ('<' + nome + '>');
    }
}