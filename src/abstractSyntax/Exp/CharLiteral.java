package abstractSyntax.Exp;

import visitor.PascalVisitor;

public class CharLiteral extends Expression {
	public char value;

	public CharLiteral(char value) {
		super();
		this.value = value;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitCharLiteral(this);
	}
}