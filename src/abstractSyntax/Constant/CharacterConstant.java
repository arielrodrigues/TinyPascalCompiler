package abstractSyntax.Constant;

import visitor.PascalVisitor;

public class CharacterConstant extends Constant {
	public char value;

	public CharacterConstant(char value) {
		super();
		this.value = value;
	}

	public void accept(PascalVisitor visitor) {
		visitor.VisitCharacterConstant(this);
	}
}