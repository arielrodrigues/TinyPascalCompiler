package LexicalAnalyzer;

import java.util.HashMap;

public class Symbol {
    HashMap<String, Long> tokens;
    String token, lexema;
    Integer line, column;
    Object value;

    Symbol (String token, Integer line, Integer column) {
        this.token = token;
        this.line = line;
        this.column = column;
    }

    Symbol (String token, String lexema, Integer line, Integer column) {
        this.token = token;
        this.lexema = lexema;
        this.line = line;
        this.column = column;
    }

    Symbol (String token, String lexema, Object value, Integer line, Integer column) {
        this.token = token;
        this.lexema = lexema;
        this.value = value;
        this.line = line;
        this.column = column;
    }

    public void printTokens() {
        System.out.println("*****************************************");
        System.out.println("Tokens:");
        tokens.forEach((token, num) -> {
            System.out.print(token + ": " + num + ", ");
        });
        System.out.println("*****************************************");
    }

    public String toString() {
        if (value != null && lexema != null) return ('<' + token + ", " + lexema + ", " + value + '>');
        else if (lexema != null) return ('<' + token + ", " + lexema + '>');
        else return ('<' + token + '>');
    }
}