package abstractSyntax.Exp;

import visitor.PasVisitor;

public class CharLiteral extends Expression {
	public char value;

	public CharLiteral(char value) {
		super();
		this.value = value;
	}

	@Override
	public void accept(PasVisitor visitor) {
		// nothing to do here
	}
}