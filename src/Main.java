import LexicalAnalyzer.*;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        String rootPath = Paths.get("").toAbsolutePath().toString();
        String subPath = "/pascalprograms/";
        String filename = rootPath + subPath + "Programa.pas";

        Symbol token;
        LexicalAnalyzer scanner = new LexicalAnalyzer(new FileReader(filename));

        while ((token = scanner.yylex()) != null) System.out.println(token.toString());
    }
}
