package abstractSyntax.variablesDeclaration;

import abstractSyntax.Exp.VariableAccess;

public class Variable extends VariableAccess {
	public String name;

	public Variable(String name) {
		super();
		this.name = name;
	}
}