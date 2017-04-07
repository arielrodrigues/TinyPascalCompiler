package abstractSyntax.Exp;

import visitor.PascalVisitor;

public class NotExpression extends Expression {
	public Expression exp;

	public NotExpression(Expression exp) {
		super();
		this.exp = exp;
	}

	@Override
	public Object accept(PascalVisitor visitor) {
		return visitor.VisitNotExpression(this);
	}
}