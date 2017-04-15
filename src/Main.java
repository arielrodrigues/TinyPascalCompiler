import abstractSyntax.Node.Program;
import syntacticAnalyzer.Parser;
import visitor.PascalVisitor;
import visitor.PrettyPrint;
import visitor.TypeChecker;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		Parser syntacticAnalyzer = new Parser();
		//syntacticAnalyzer.debug_parse();
		Program pasProgram = (Program) syntacticAnalyzer.parse().value;
		PascalVisitor prettyPrint = new PrettyPrint(pasProgram);
		TypeChecker typeChecker = new TypeChecker(pasProgram);
	}	
}