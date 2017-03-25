package abstractSyntax.Exp;

import abstractSyntax.Exp.Expression;

public class NumberLiteral extends Expression {
	public int value;

	public NumberLiteral(int value) {
		super();
		this.value = value;
	}
}