import abstractSyntax.Node.Program;
import syntacticAnalyzer.Parser;
import visitor.*;

public class Main {
	public static void main(String[] args) throws Exception {
		Parser syntacticAnalyzer = new Parser();
		Program pasProgram = (Program) syntacticAnalyzer.parse().value;
		PascalVisitor prettyPrint = new PrettyPrint(pasProgram);
		TypeChecker typeChecker = new TypeChecker(pasProgram);
		if (typeChecker.errorLog.length() <= 0) {
			TreeTranslator translator = new TreeTranslator(pasProgram);
		} else System.out.println("Semantic erros found, no code generated.");
	}	
}