package abstractSyntax.variablesDeclaration;

import visitor.PascalVisitor;

public class Variable extends VariableAccess {
	public String name;

	public Variable(String name) {
		super();
		this.name = name;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		visitor.VisitVariable(this);
	}
}