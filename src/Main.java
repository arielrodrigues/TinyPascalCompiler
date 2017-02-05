import lexicalanalyzer.*;

import java.io.IOException;
import java.io.StringReader;

public class Main {

    public static void main(String[] args) throws IOException {
        String expressao = "if 2+3 := 5 then 5";
        LexicalAnalyzer lex = new LexicalAnalyzer(new StringReader(expressao));
        lex.yylex();
    }
}
