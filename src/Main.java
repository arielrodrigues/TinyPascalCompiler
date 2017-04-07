import abstractSyntax.Node.Program;
import syntacticAnalyzer.Parser;
import visitor.PascalVisitor;
import visitor.PrettyPrint;

public class Main {
	public static void main(String[] args) throws Exception {
		Parser syntacticAnalyzer = new Parser();
		//syntacticAnalyzer.debug_parse();
		PascalVisitor prettyPrint = new PrettyPrint((Program) syntacticAnalyzer.parse().value);
	}	
}