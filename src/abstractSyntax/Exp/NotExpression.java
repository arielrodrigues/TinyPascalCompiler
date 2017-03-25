package abstractSyntax.Exp;

import abstractSyntax.Exp.Expression;

public class NotExpression extends Expression {
	public Expression exp;

	public NotExpression(Expression exp) {
		super();
		this.exp = exp;
	}
}