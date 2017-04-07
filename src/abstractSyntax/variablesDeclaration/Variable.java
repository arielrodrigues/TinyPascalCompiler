package abstractSyntax.variablesDeclaration;

import visitor.PascalVisitor;

public class Variable extends VariableAccess {
	public String name;

	public Variable(String name) {
		super();
		this.name = name;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitVariable(this);
	}
}