package abstractSyntax.Exp;

import abstractSyntax.Exp.Expression;
import abstractSyntax.Sign;

public class SignedExpression extends Expression {
	public Sign sign;
	public Expression exp;
	
	public SignedExpression(Sign sign, Expression exp) {
		super();
		this.sign = sign;
		this.exp = exp;
	}
}