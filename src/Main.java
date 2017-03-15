''/*

import utils.FileManager;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        FileManager fileManager = new FileManager();
        StringBuilder stringBuilder = new StringBuilder();
        String inFile = Paths.get("").toAbsolutePath().toString()+"/src/"+"input.txt";
		LexicalAnalyzer lex = new LexicalAnalyzer(new StringReader(inFile.toString()));
		lex.yylex();

        LexicalAnalyzer scanner = new LexicalAnalyzer(new FileReader(inFile));
    }
}*/

public class Main {
	public static void main(String[] args) throws Exception {
		Parser parser = new Parser();
		parser.parse();
	}	
}