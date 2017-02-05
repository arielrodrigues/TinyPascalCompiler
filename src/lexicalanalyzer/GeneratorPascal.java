package lexicalanalyzer;

import java.io.File;
import java.nio.file.Paths;

public class GeneratorPascal {
    public static void main(String[] args)	{
        String rootPath = Paths.get("").toAbsolutePath().toString();
        String subPath = "/src/lexicalanalyzer/";
        String filename = rootPath + subPath + "pascal.lex";
        File sourceCode = new File(filename);
        jflex.Main.generate(sourceCode);
    }
}
