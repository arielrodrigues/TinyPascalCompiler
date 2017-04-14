package abstractSyntax.variablesDeclaration;

import visitor.PascalVisitor;

public class IdExpression extends VariableAccess {
	public String name;

	public IdExpression(String name) {
		super();
		this.name = name;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitIdExpression(this);
	}
}