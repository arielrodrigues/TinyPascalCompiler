package LexicalAnalyzer.JFlexHandler;

import java.io.File;
import java.nio.file.Paths;

public class LexicalAnalyzerGenerator {
    public static void main(String[] args)	{
        String rootPath = Paths.get("").toAbsolutePath().toString();
        String subPath = "/src/LexicalAnalyzer/";
        String filename = rootPath + subPath + "pascal.lex";
        File sourceCode = new File(filename);
        jflex.Main.generate(sourceCode);
    }
}
