import abstractSyntax.Node.Program;
import syntacticAnalyzer.Parser;
import visitor.PascalVisitor;
import visitor.PrettyprintVisitor;

public class Main {
	public static void main(String[] args) throws Exception {
		Parser syntacticAnalyzer = new Parser();
		//syntacticAnalyzer.debug_parse();
		PascalVisitor prettyPrint = new PrettyprintVisitor((Program) syntacticAnalyzer.parse().value);
	}	
}