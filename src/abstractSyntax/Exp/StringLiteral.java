package abstractSyntax.Exp;

import visitor.PascalVisitor;

public class StringLiteral extends Expression {
	public String value;

	public StringLiteral(String value) {
		super();
		this.value = value;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitStringLiteral(this);
	}
}