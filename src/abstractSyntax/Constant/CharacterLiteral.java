package abstractSyntax.Constant;

import visitor.PascalVisitor;

public class CharacterLiteral extends Constant {
	public char value;

	public CharacterLiteral(char value) {
		super();
		this.value = value;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		visitor.VisitCharacterLiteral(this);
	}
}