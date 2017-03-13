/*
import LexicalAnalyzer.*;
import utils.FileManager;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        Symbol token;
        FileManager fileManager = new FileManager();
        StringBuilder stringBuilder = new StringBuilder();
        String inFile = Paths.get("").toAbsolutePath().toString()+"/pascalprograms/"+"common.pas";

        LexicalAnalyzer scanner = new LexicalAnalyzer(new FileReader(inFile));

        while ((token = scanner.yylex()) != null) stringBuilder.append(token.toString()).append("\n");

        fileManager.writeToFile("file.out", stringBuilder.toString());
    }
}
*/

public class Main {
	public static void main(String[] args) throws Exception {
		Parser parser = new Parser();
		parser.parse();
	}	
}