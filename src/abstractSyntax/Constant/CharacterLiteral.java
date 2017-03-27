package abstractSyntax.Constant;

import visitor.PrettyprintVisitor;

public class CharacterLiteral extends Constant {
	public char value;

	public CharacterLiteral(char value) {
		super();
		this.value = value;
	}

	@Override
	public void prettyPrint(PrettyprintVisitor prettyprintVisitor) {
		System.out.print(value);
	}
}