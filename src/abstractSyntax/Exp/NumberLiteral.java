package abstractSyntax.Exp;

import visitor.PascalVisitor;

public class NumberLiteral extends Expression {
	public int value;

	public NumberLiteral(int value) {
		super();
		this.value = value;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		// nothing to do here
	}
}