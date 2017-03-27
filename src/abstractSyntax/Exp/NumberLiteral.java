package abstractSyntax.Exp;

import visitor.PasVisitor;

public class NumberLiteral extends Expression {
	public int value;

	public NumberLiteral(int value) {
		super();
		this.value = value;
	}

	@Override
	public void accept(PasVisitor visitor) {
		// nothing to do here
	}
}