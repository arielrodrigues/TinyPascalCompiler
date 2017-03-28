package abstractSyntax.Exp;

import visitor.PascalVisitor;

public class StringLiteral extends Expression {
	public String value;

	public StringLiteral(String value) {
		super();
		this.value = value;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		// nothing to do here
	}
}