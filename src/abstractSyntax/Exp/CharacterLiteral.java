package abstractSyntax.Exp;

import abstractSyntax.Constant.Constant;
import visitor.PascalVisitor;

public class CharacterLiteral extends Expression {
	public char value;

	public CharacterLiteral(char value) {
		super();
		this.value = value;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitCharacterLiteral(this);
	}
}