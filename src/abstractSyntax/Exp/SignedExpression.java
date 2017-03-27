package abstractSyntax.Exp;

import abstractSyntax.Operator.Sign;
import visitor.PasVisitor;

public class SignedExpression extends Expression {
	public Sign sign;
	public Expression exp;
	
	public SignedExpression(Sign sign, Expression exp) {
		super();
		this.sign = sign;
		this.exp = exp;
	}

	@Override
	public void accept(PasVisitor visitor) {
		sign.accept(visitor);
		exp.accept(visitor);
	}
}