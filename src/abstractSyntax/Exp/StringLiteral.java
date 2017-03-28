package abstractSyntax.Exp;

import abstractSyntax.Exp.Expression;

public class StringLiteral extends Expression {
	public String value;

	public StringLiteral(String value) {
		super();
		this.value = value;
	}
}