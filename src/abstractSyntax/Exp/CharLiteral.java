package abstractSyntax.Exp;

import visitor.PascalVisitor;

public class CharLiteral extends Expression {
	public char value;

	public CharLiteral(char value) {
		super();
		this.value = value;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		visitor.VisitCharLiteral(this);
	}
}