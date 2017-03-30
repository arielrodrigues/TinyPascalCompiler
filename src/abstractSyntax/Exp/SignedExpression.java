package abstractSyntax.Exp;

import abstractSyntax.Operator.Sign;
import visitor.PascalVisitor;

public class SignedExpression extends Expression {
	public Sign sign;
	public Expression exp;
	
	public SignedExpression(Sign sign, Expression exp) {
		super();
		this.sign = sign;
		this.exp = exp;
	}

	@Override
	public void accept(PascalVisitor visitor) {
		visitor.VisitSignedExpression(this);
	}
}