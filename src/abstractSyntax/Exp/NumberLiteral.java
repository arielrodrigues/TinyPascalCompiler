package abstractSyntax.Exp;

import visitor.PascalVisitor;

public class NumberLiteral extends Expression {
	public int value;

	public NumberLiteral(int value) {
		super();
		this.value = value;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitNumberLiteral(this);
	}
}