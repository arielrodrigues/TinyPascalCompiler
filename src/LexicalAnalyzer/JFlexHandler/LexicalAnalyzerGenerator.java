package LexicalAnalyzer.JFlexHandler;

import java.io.File;
import java.nio.file.Paths;

public class LexicalAnalyzerGenerator {
    public static void main(String[] args)	{
    	String filename = Paths.get("").toAbsolutePath().toString() + "/src/" + "pascal.lex";
        File sourceCode = new File(filename);
        jflex.Main.generate(sourceCode);

    }
}