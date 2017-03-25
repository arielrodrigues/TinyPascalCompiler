package abstractSyntax.Exp;

import abstractSyntax.Exp.Expression;

public class charLiteral extends Expression {
	public char value;

	public charLiteral(char value) {
		super();
		this.value = value;
	}
}